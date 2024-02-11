package com.example.testtask.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class WalletOperationRequest {
    private final UUID walletUuid;
    private final String operationType;
    private final int amount;
}
