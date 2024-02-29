package com.example.servicecardtransfer.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class TransferRequest {  // Запрос на передачу

    @NotBlank(message = "Номер карты отправителя обязателен")
    @Size(min = 16, max = 16, message = "Номер карты должен состоять из 16 символов")
    private String cardFromNumber;  //Номер карточки отправителя

    @NotBlank(message = "Номер карты получателя обязателен")
    @Size(min = 16, max = 16, message = "Номер карты должен состоять из 16 символов")
    private String cardToNumber; //Номер карточки получателя

    @NotBlank(message = "Дата истечения срока действия карты обязательна")
    @Size(min = 5, max = 5, message = "Неверный формат даты истечения срока действия карты. Формат должен быть ММ/ГГ")
    private String cardFromValidTill;  //Дата истечения срока действия

    @NotBlank(message = "CVC-код обязателен")
    @Size(min = 3, max = 3, message = "CVC-код должен состоять из 3 символов")
    private String cardFromCVV;

    @NotNull(message = "Сумма перевода обязательна")
    // @Positive(message = "Сумма перевода должна быть положительной")
    private Amount amount;
}