package com.example.servicecardtransfer.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TransferException extends RuntimeException {
    public TransferException(String message) {
        super(message);
    }
}
