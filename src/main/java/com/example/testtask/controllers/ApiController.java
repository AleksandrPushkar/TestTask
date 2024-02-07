package com.example.testtask.controllers;

import com.example.testtask.dto.WalletOperationRequest;
import com.example.testtask.dto.WalletOperationResponse;
import com.example.testtask.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ApiController {
    private final WalletService walletService;
    @PostMapping("/wallet")
    public ResponseEntity<IndexingResponse> performOperation(@RequestBody WalletOperationRequest operationRequest) {
        String operationType = operationRequest.getOperationType();
        WalletOperationResponse operationResponse = null;
        if(operationType.equals("DEPOSIT")) {
            walletService.deposit(operationResponse);
        }

        return ResponseEntity.ok(indexingService.startIndexing(indexPageURLRequest));
    }
}
