package com.example.servicecardtransfer.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder
public class TransferOperation {
    private int id;
    private Card cardFrom;  //Исходная карта, откуда
    private Card cardTo;  // Карта назначения , куда
    private Amount amount;  // Сумма
    private String code;  // Код

}
