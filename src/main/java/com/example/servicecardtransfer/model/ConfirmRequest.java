package com.example.servicecardtransfer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConfirmRequest {  // Запрос подтверждения
    private int operationId;   // Идентификатор
    private String code;    // Секретный код
}
