package com.example.testtask.exceptions.throwns;

public class InsufficientFundsOnWalletException extends RuntimeException {
    public InsufficientFundsOnWalletException() {
        super("Недостаточно средств!");
    }
}
