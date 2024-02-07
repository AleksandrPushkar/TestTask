package com.example.testtask.dto;

import lombok.Data;

@Data
public class WalletOperationResponse {
    private final boolean result;
    private final String error;
}
