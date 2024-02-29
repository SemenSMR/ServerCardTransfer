package com.example.servicecardtransfer.reposotiry;

import com.example.servicecardtransfer.model.Amount;
import com.example.servicecardtransfer.model.Card;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;



@Repository
public class CardMemoryRepository implements CardRepository {
    private final ConcurrentHashMap<String, Card> cardMap = new ConcurrentHashMap<>();

    public CardMemoryRepository() {
        initializeCardMap();
    }

    private void initializeCardMap() {
            Card card1 = Card.builder()
                    .cardNumber("5400000000000000")
                    .validTill("01/25")
                    .cvv("111")
                    .amount(Amount.builder()
                            .value(10000)
                            .currency("RUR")
                            .build())
                    .build();
            cardMap.put(card1.getCardNumber(), card1);
            Card card2 = Card.builder()
                    .cardNumber("5400000000000001")
                    .validTill("01/26")
                    .cvv("222")
                    .amount(Amount.builder()
                            .value(15000)
                            .currency("RUR")
                            .build())
                    .build();
            cardMap.put(card2.getCardNumber(), card2);
        }


    @Override
    public Card getCardByNumber(String cardNumber) {
        return cardMap.get(cardNumber);
    }

    @Override
    public void saveCard(Card card) {
        cardMap.put(card.getCardNumber(), card);
    }
}