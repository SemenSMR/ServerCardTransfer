package com.example.servicecardtransfer.service;



import com.example.servicecardtransfer.exception.InputDataException;
import com.example.servicecardtransfer.exception.TransferException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TransferServiceTest {

    @Autowired
    TransferService service;

    @Test
    void successTransferMoney() {
        var test = service.transferMoney(CardMap.successTransferRequest());
        assertEquals(test.getOperationId(), 1);
        var test2 = service.confirmOperation(CardMap.successConfirmRequest());
        assertEquals(test2.getOperationId(),1);

    }

    @Test
    void BadTransferMoney(){
        assertThrows(TransferException.class,() -> service.transferMoney(CardMap.badTransferRequest()),
                "На карте отправителя недостаточно средств для перевода.");
    }
    @Test
    void failedConfirmOperation_1() {
        assertThrows(InputDataException.class,
                () -> service.confirmOperation(CardMap.successConfirmRequest()),
                "Операция не найдена.");
    }
    @Test
    void failedTransferMoney_2() {
        assertThrows(InputDataException.class,
                () -> service.transferMoney(CardMap.FailedCVVTransferRequest()),
                "CVV код карты отправителя введен неверно.");
    }

}