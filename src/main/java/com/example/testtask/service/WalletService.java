package com.example.testtask.service;

import com.example.testtask.dto.WalletOperationRequest;
import org.springframework.stereotype.Service;

@Service
public interface WalletService {
    void balance(int walletId);

    void deposit(WalletOperationRequest operationResponse);
    void withdraw(WalletOperationRequest operationResponse);

}
