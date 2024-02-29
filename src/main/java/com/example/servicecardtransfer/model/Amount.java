package com.example.servicecardtransfer.model;


import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Amount {
   // @Positive(message = "Amount value must be positive")
    private int value;  // Значение

    private String currency;  // валюта

    public Amount(int i) {
    }
}
