package com.example.testtask.controllers;

import com.example.testtask.dto.GetWalletListResponse;
import com.example.testtask.dto.WalletOperationRequest;
import com.example.testtask.dto.WalletOperationResponse;
import com.example.testtask.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ApiController {
    private final WalletService walletService;
    @GetMapping("/wallets/{id}")
    public ResponseEntity<Integer> getBalance(@PathVariable UUID id) {
        return ResponseEntity.ok(walletService.getBalance(id));
    }
    @PostMapping("/wallet")
    public ResponseEntity<WalletOperationResponse> performOperation(@RequestBody WalletOperationRequest operationRequest) {
        String operationType = operationRequest.getOperationType();
        if(operationType.equals("DEPOSIT")) {
            return ResponseEntity.ok(walletService.deposit(operationRequest));
        } else {
            return ResponseEntity.ok( walletService.withdraw(operationRequest));
        }
    }
    @GetMapping("/wallets")
    public ResponseEntity<GetWalletListResponse> getWalletList() {
        return ResponseEntity.ok(walletService.getWalletList());
    }
}
