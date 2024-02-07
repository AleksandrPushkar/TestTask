package com.example.testtask.dto;

import lombok.*;

@Data
public class ErrorResponse {
    private final boolean result;
    private final String error;
}
