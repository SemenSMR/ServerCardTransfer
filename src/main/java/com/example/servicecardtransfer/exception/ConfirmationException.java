package com.example.servicecardtransfer.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ConfirmationException extends RuntimeException {
    public ConfirmationException(String message) {
        super(message);
    }
}

