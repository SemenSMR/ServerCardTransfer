package com.example.servicecardtransfer.service;

import com.example.servicecardtransfer.controller.TransferController;
import com.example.servicecardtransfer.exception.InputDataException;
import com.example.servicecardtransfer.model.Amount;
import com.example.servicecardtransfer.model.Logger;
import com.example.servicecardtransfer.model.TransferRequest;
import com.example.servicecardtransfer.reposotiry.CardMemoryRepository;
import com.example.servicecardtransfer.reposotiry.OperationMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ModulTest {
    @Mock
    private CardMemoryRepository cardRepository;

    @Mock
    private OperationMemoryRepository operationRepository;


    private TransferService transferService;

    @BeforeEach

    public void setup(){
        MockitoAnnotations.initMocks(this);
        transferService = new TransferService(cardRepository,operationRepository);

    }
    @Test

    public void testTransferMoney(){
        TransferRequest request = TransferRequest.builder()
                .cardFromNumber("1234567890123456")
                .cardToNumber("5400000000000001")
                .cardFromValidTill("01/25")
                .cardFromCVV("111")
                .amount(Amount.builder().value(5000).currency("RUB").build())
                .build();

        when(cardRepository.getCardByNumber(anyString())).thenReturn(null);

        assertThrows(NullPointerException.class,() -> transferService.transferMoney(request));
    }


}
