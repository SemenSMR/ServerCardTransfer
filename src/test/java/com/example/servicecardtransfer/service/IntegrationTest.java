package com.example.servicecardtransfer.service;

import com.example.servicecardtransfer.model.Amount;
import com.example.servicecardtransfer.model.TransferRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {
    @LocalServerPort
    private int port;


    @Autowired
    private TestRestTemplate restTemplate;


    @Container
    private static final GenericContainer<?> appContainer = new GenericContainer<>("myapp:0.1")
            .withExposedPorts(5500);

    @Test
    public void testTransferMoney(){
        String baseUrl = "http://localhost:" + port + "/transfer";
        String sourceCardNumber = "5400000000000000";
        String targetCardNumber = "5400000000000001";
        String expirationDate = "01/25";
        String cvv = "111";
         Amount amount = new Amount(590);
        TransferRequest request = new TransferRequest(sourceCardNumber,targetCardNumber,expirationDate,cvv,amount);
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, request, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
