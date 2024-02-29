package com.example.servicecardtransfer.reposotiry;

import com.example.servicecardtransfer.model.Card;

public interface CardRepository {
    Card getCardByNumber(String cardNumber);
    void saveCard(Card card);
}
