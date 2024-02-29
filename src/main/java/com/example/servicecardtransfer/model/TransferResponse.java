package com.example.servicecardtransfer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TransferResponse extends TransferRequest { //Ответ на передачу
    private int operationId;
}
