package com.example.servicecardtransfer.service;

import com.example.servicecardtransfer.exception.ConfirmationException;
import com.example.servicecardtransfer.exception.InputDataException;
import com.example.servicecardtransfer.exception.TransferException;
import com.example.servicecardtransfer.model.*;
import com.example.servicecardtransfer.reposotiry.CardMemoryRepository;
import com.example.servicecardtransfer.reposotiry.OperationMemoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.servicecardtransfer.util.Commision.COMMISSION_VALUE;

@AllArgsConstructor
@Service
public class TransferService {
    private final Logger logger = Logger.getInstance();
    private final CardMemoryRepository cardRepository;
    private final OperationMemoryRepository operationRepository;

    public TransferResponse transferMoney(TransferRequest request) {
        var cardFrom = cardRepository.getCardByNumber(request.getCardFromNumber()); // отправитель
        var cardTo = cardRepository.getCardByNumber(request.getCardToNumber()); // получатель

        validCard(cardFrom, cardTo, request, request.getCardFromNumber(), request.getCardFromCVV()); //Валидность

        var id = operationRepository.createOperation(cardFrom, cardTo, request.getAmount());
        return new TransferResponse(id);


    }


    public TransferResponse confirmOperation(ConfirmRequest request) {

        var operation = operationRepository.getOperation(request.getOperationId()); //хранит данные конкретной операции

        validOperation(operation, request.getCode()); //Валидность

        var cardFrom = operation.getCardFrom();
        var cardTo = operation.getCardTo();
        var amount = operation.getAmount();
        var commission = amount.getValue() * COMMISSION_VALUE;

        cardTo.setAmount(Amount.builder()
                .currency(amount.getCurrency())
                .value(cardTo.getAmount().getValue() + amount.getValue())
                .build());

        cardFrom.setAmount(Amount.builder()
                .currency(amount.getCurrency())
                .value((int) (cardFrom.getAmount().getValue() - amount.getValue() - commission))
                .build());

        cardRepository.saveCard(cardTo);
        cardRepository.saveCard(cardFrom);
        operationRepository.removeOperationId(operation.getId()); // Удаление записи

        writeSuccessToLog(cardFrom, cardTo, amount); // Запись в логи

        return new TransferResponse(operation.getId()); // Передача ответа


    }

    private void validCard(Card cardFrom, Card cardTo, TransferRequest request,
                                 String cardNumFromRequest, String cardNumToRequest) {
        if (cardFrom == null) {
            throwInputDataException(Card.builder().cardNumber(cardNumFromRequest).build(),
                    cardTo, request.getAmount(),
                    "Карта отправителя не найдена.");
            return;
        }
        if (cardTo == null) {
            throwInputDataException(cardFrom, Card.builder().cardNumber(cardNumToRequest).build(),
                    request.getAmount(),
                    "Карта получателя не найдена.");
            return;
        }
        if (cardNumFromRequest.equals(cardNumToRequest)) {
            throwInputDataException(cardFrom, cardTo, request.getAmount(),
                    "Номера карты отправителя и карты получателя не могут быть равны.");
        }
        if (!cardFrom.getValidTill().equals(request.getCardFromValidTill())) {   //
            throwInputDataException(cardFrom, cardTo, request.getAmount(),
                    "Срок действия карты отправителя введен неверно.");
        }
        if (!cardFrom.getCvv().equals(request.getCardFromCVV())) {
            throwInputDataException(cardFrom, cardTo, request.getAmount(),
                    "CVV код карты отправителя введен неверно.");
        }
        if (cardFrom.getAmount().getValue() < (request.getAmount().getValue() + (request.getAmount().getValue() * COMMISSION_VALUE))) {
            throwTransferException(cardFrom, cardTo, request.getAmount(),
                    "На карте отправителя недостаточно средств для перевода.");
        }
    }

    private void validOperation(TransferOperation operation, String code) {
        if (operation == null) {
            throwInputDataException("Операция не найдена.");
            return;
        }
        if (!operation.getCode().equals(code)) {
            throwConfirmationException(operation.getCardFrom(), operation.getCardFrom(), operation.getAmount(),
                    "Код подтверждения операции неверен.");
        }
    }

    private void throwConfirmationException(Card cardFrom, Card cardTo, Amount amount, String error) {
        writeFailToLog(cardFrom, cardTo, amount, error);
        throw new ConfirmationException(error);
    }

    private void throwTransferException(Card cardFrom, Card cardTo, Amount amount, String error) {
        writeFailToLog(cardFrom, cardTo, amount, error);
        throw new TransferException(error);
    }

    private void throwInputDataException(Card cardFrom, Card cardTo, Amount amount, String error) {
        writeFailToLog(cardFrom, cardTo, amount, error);
        throw new InputDataException(error);
    }

    private void throwInputDataException(String error) {
        writeFailToLog(error);
        throw new InputDataException(error);
    }


    private void writeFailToLog(Card cardFrom, Card cardTo, Amount amount, String error) {
        logger.write(String.format("[%s - %d RUB] --> [%s - %d RUB] [Сумма: %d RUB] [Комиссия: %d RUB] [ОТКЛОНЕН] [%s]",
                cardFrom.getCardNumber(), cardFrom.getAmount().getValue(),
                cardTo.getCardNumber(), cardTo.getAmount().getValue(),
                amount.getValue(),
                (int) (amount.getValue() * COMMISSION_VALUE),
                error));
    }

    private void writeFailToLog(String error) {
        logger.write(String.format("[ОТКЛОНЕН] [%s]",
                error));
    }

    private void writeSuccessToLog(Card cardFrom, Card cardTo, Amount amount) {
        logger.write(String.format("[%s - %d RUB] --> [%s - %d RUB] [Сумма: %d RUB] [Комиссия: %d RUB] [УСПЕШНО]",
                cardFrom.getCardNumber(), cardFrom.getAmount().getValue(),
                cardTo.getCardNumber(), cardTo.getAmount().getValue(),
                amount.getValue(),
                (int) (amount.getValue() * COMMISSION_VALUE)));
    }
}