package com.example.testtask.service;

import com.example.testtask.dto.WalletOperationRequest;
import com.example.testtask.exceptions.throwns.AmountNotCorrectException;
import com.example.testtask.exceptions.throwns.InsufficientFundsOnWalletException;
import com.example.testtask.exceptions.throwns.WalletNotFoundException;
import com.example.testtask.model.WalletEntity;
import com.example.testtask.repository.WalletRepo;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {
    private WalletRepo walletRepo;

    @Override
    public void balance(int walletId) {
        WalletEntity wallet = walletRepo.findById(walletId).orElseThrow(WalletNotFoundException::new);
        //return wallet.getBalance();
    }

    @Override
    public void deposit(WalletOperationRequest operationResponse) {
        if (operationResponse.getAmount() < 1) {
            throw new AmountNotCorrectException();
        }
        WalletEntity wallet = walletRepo.findById(operationResponse.getWalletId()).orElseThrow(WalletNotFoundException::new);
        wallet.setBalance(wallet.getBalance() + operationResponse.getAmount());
        walletRepo.save(wallet);
    }

    @Override
    public void withdraw(WalletOperationRequest operationResponse) {
        if (operationResponse.getAmount() < 1) {
            throw new AmountNotCorrectException();
        }
        WalletEntity wallet = walletRepo.findById(operationResponse.getWalletId()).orElseThrow(WalletNotFoundException::new);
        if (operationResponse.getAmount() > wallet.getBalance()) {
            throw new InsufficientFundsOnWalletException();
        }
        wallet.setBalance(wallet.getBalance() - operationResponse.getAmount());
        walletRepo.save(wallet);
    }
}
