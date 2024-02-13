package com.example.testtask.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class WalletDto {
    private final UUID uuid;

    private final Integer balance;
}
