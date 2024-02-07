package com.example.testtask.dto;

import lombok.Data;

@Data
public class WalletOperationRequest {
    private final int walletId;
    private final String operationType;
    private final int amount;
}
