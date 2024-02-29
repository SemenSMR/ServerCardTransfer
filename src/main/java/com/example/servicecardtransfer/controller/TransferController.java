package com.example.servicecardtransfer.controller;

import com.example.servicecardtransfer.model.ConfirmRequest;
import com.example.servicecardtransfer.model.TransferRequest;
import com.example.servicecardtransfer.model.TransferResponse;
import com.example.servicecardtransfer.service.TransferService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@CrossOrigin
public class TransferController {
    private final TransferService transferService;

    @PostMapping("/transfer") // Перевод денег
    public TransferRequest transfer(@Validated @RequestBody TransferRequest transferRequest) {
        return transferService.transferMoney(transferRequest);
    }

    @PostMapping("/confirmOperation") // Подтверждение операции
    public TransferResponse confirmOperation(@RequestBody ConfirmRequest confirmRequest) {
        return transferService.confirmOperation(confirmRequest);
    }
}
