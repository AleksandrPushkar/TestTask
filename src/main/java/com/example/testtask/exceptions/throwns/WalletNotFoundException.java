package com.example.testtask.exceptions.throwns;

public class WalletNotFoundException extends RuntimeException{
    public WalletNotFoundException() {
        super("Такой кошелёк не существует!");
    }
}
