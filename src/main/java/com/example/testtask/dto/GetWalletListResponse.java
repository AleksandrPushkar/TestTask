package com.example.testtask.dto;

import lombok.Data;

import java.util.List;
@Data
public class GetWalletListResponse {
    private final List<WalletDto> detailed;
}
