package com.example.servicecardtransfer.reposotiry;

import com.example.servicecardtransfer.model.Amount;
import com.example.servicecardtransfer.model.Card;
import com.example.servicecardtransfer.model.TransferOperation;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class OperationMemoryRepository implements OperationRepository {  //работа с операциями перевода денег в памяти
    private final AtomicInteger atomicCounter = new AtomicInteger(0);
    private final Map<Integer, TransferOperation> operationMap = new ConcurrentHashMap<>();

    @Override
    public int createOperation(Card cardFrom, Card cardTo, Amount amount) {
        int id = atomicCounter.incrementAndGet();
        operationMap.put(id, TransferOperation.builder()
                .id(id)
                .cardFrom(cardFrom)
                .cardTo(cardTo)
                .amount(amount)
                .code("0000")
                .build());
        return id;
    }

    @Override
    public TransferOperation getOperation(int id) {
        return operationMap.get(id);
    }

    @Override
    public void removeOperationId(int id) {
        operationMap.remove(id);
    }
}
