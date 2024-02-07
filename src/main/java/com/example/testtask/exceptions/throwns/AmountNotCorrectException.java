package com.example.testtask.exceptions.throwns;

public class AmountNotCorrectException extends RuntimeException{
    public AmountNotCorrectException() {
        super("Сумма не корректна!");
    }
}
