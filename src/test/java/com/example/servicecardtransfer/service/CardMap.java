package com.example.servicecardtransfer.service;

import com.example.servicecardtransfer.model.Amount;
import com.example.servicecardtransfer.model.ConfirmRequest;
import com.example.servicecardtransfer.model.TransferRequest;

public class CardMap {
    public static TransferRequest successTransferRequest() {
        return TransferRequest.builder()
                .cardFromNumber("5400000000000000")
                .cardToNumber("5400000000000001")
                .cardFromValidTill("01/25")
                .cardFromCVV("111")
                .amount(Amount.builder()
                        .value(5000)
                        .currency("RUR")
                        .build()).build();
    }

    public static ConfirmRequest successConfirmRequest() {
        return ConfirmRequest.builder()
                .code("0000")
                .operationId(1)
                .build();
    }

    public static TransferRequest badTransferRequest() {
        return TransferRequest.builder()
                .cardFromNumber("5400000000000000")
                .cardToNumber("5400000000000001")
                .cardFromValidTill("01/25")
                .cardFromCVV("111")
                .amount(Amount.builder()
                        .value(20000)
                        .currency("RUR")
                        .build()).build();
    }
    public static TransferRequest FailedCVVTransferRequest() {
        return TransferRequest.builder()
                .cardFromNumber("5400000000000000")
                .cardToNumber("5400000000000001")
                .cardFromValidTill("01/25")
                .cardFromCVV("123")
                .amount(Amount.builder()
                        .value(5000)
                        .currency("RUR")
                        .build()).build();
    }


}
