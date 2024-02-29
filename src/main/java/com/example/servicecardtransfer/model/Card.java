package com.example.servicecardtransfer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Card {

    private String cardNumber;  // Номер карты

    private String validTill;  // Действительна?

    private String cvv;  //   Код на обратной стороне

    private Amount amount;    // Сумма

}
