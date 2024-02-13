package com.example.testtask.exceptions;

import com.example.testtask.dto.ErrorResponse;
import com.example.testtask.exceptions.throwns.AmountNotCorrectException;
import com.example.testtask.exceptions.throwns.InsufficientFundsOnWalletException;
import com.example.testtask.exceptions.throwns.WalletNotFoundException;
import com.example.testtask.exceptions.throwns.WalletsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(AmountNotCorrectException.class)
    protected ResponseEntity<ErrorResponse> handleAmountNotCorrectException(AmountNotCorrectException ex) {
        ErrorResponse errorResponse = new ErrorResponse(false, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsufficientFundsOnWalletException.class)
    protected ResponseEntity<ErrorResponse> handleInsufficientFundsOnWalletException(
            InsufficientFundsOnWalletException ex) {
        ErrorResponse errorResponse = new ErrorResponse(false, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WalletNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleWalletNotFoundException(WalletNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(false, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WalletsNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleWalletsNotFoundException(WalletsNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(false, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
