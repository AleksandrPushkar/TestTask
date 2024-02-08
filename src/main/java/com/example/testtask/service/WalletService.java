package com.example.testtask.service;

import com.example.testtask.dto.WalletOperationRequest;
import com.example.testtask.dto.WalletOperationResponse;
import org.springframework.stereotype.Service;

@Service
public interface WalletService {
    int getBalance(int walletId);

    WalletOperationResponse deposit(WalletOperationRequest operationRequest);
    WalletOperationResponse withdraw(WalletOperationRequest operationRequest);

}
