package com.example.servicecardtransfer.reposotiry;

import com.example.servicecardtransfer.model.Amount;
import com.example.servicecardtransfer.model.Card;
import com.example.servicecardtransfer.model.TransferOperation;
import jdk.dynalink.Operation;

public interface OperationRepository {
    int createOperation(Card cardFrom, Card cardTo, Amount amount);

    TransferOperation getOperation(int id);

    void removeOperationId(int id);
}

