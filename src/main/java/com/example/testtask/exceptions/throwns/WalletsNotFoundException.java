package com.example.testtask.exceptions.throwns;

public class WalletsNotFoundException extends RuntimeException{
    public WalletsNotFoundException() {
        super("Кошельки не найдены!");
    }
}
