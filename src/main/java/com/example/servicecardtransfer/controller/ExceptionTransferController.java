package com.example.servicecardtransfer.controller;

import com.example.servicecardtransfer.exception.ConfirmationException;
import com.example.servicecardtransfer.exception.InputDataException;
import com.example.servicecardtransfer.exception.TransferException;
import com.example.servicecardtransfer.model.ErrorResponse;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.atomic.AtomicInteger;

@RestControllerAdvice
public class ExceptionTransferController {
    private final AtomicInteger id = new AtomicInteger(0);

    @ExceptionHandler(InputDataException.class)
    public ResponseEntity<ErrorResponse> handleInputData(InputDataException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage(), id.incrementAndGet()));
    }

    @ExceptionHandler(TransferException.class)
    public ResponseEntity<ErrorResponse> handleTransferError(TransferException e) {

        return ResponseEntity.internalServerError().body(new ErrorResponse(e.getMessage(), id.incrementAndGet()));
    }

    @ExceptionHandler(ConfirmationException.class)
    public ResponseEntity<ErrorResponse> handleConfirmationError(ConfirmationException e) {
        return ResponseEntity.internalServerError().body(new ErrorResponse(e.getMessage(), id.incrementAndGet()));
    }
}
