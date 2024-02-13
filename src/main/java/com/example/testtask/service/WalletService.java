package com.example.testtask.service;

import com.example.testtask.dto.GetWalletListResponse;
import com.example.testtask.dto.WalletOperationRequest;
import com.example.testtask.dto.WalletOperationResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface WalletService {
    int getBalance(UUID walletId);

    WalletOperationResponse deposit(WalletOperationRequest operationRequest);
    WalletOperationResponse withdraw(WalletOperationRequest operationRequest);
    GetWalletListResponse getWalletList();

}
