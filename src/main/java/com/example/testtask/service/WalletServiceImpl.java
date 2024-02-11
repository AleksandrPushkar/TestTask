package com.example.testtask.service;

import com.example.testtask.dto.WalletOperationRequest;
import com.example.testtask.dto.WalletOperationResponse;
import com.example.testtask.exceptions.throwns.AmountNotCorrectException;
import com.example.testtask.exceptions.throwns.InsufficientFundsOnWalletException;
import com.example.testtask.exceptions.throwns.WalletNotFoundException;
import com.example.testtask.model.WalletEntity;
import com.example.testtask.repository.WalletRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepo walletRepo;

    @Override
    public int getBalance(UUID walletId) {
        WalletEntity wallet = walletRepo.findByUuid(walletId);
        if (wallet == null) {
            throw new WalletNotFoundException();
        }
        return wallet.getBalance();
    }

    @Override
    @Transactional
    public WalletOperationResponse deposit(WalletOperationRequest operationRequest) {
        WalletEntity wallet = getWalletFromDbBeforeOperation(operationRequest);
        wallet.setBalance(wallet.getBalance() + operationRequest.getAmount());
        walletRepo.save(wallet);
        return new WalletOperationResponse(true, "");
    }

    @Override
    public WalletOperationResponse withdraw(WalletOperationRequest operationRequest) {
        WalletEntity wallet = getWalletFromDbBeforeOperation(operationRequest);
        if (operationRequest.getAmount() > wallet.getBalance()) {
            throw new InsufficientFundsOnWalletException();
        }
        wallet.setBalance(wallet.getBalance() - operationRequest.getAmount());
        walletRepo.save(wallet);
        return new WalletOperationResponse(true, "");
    }

    private WalletEntity getWalletFromDbBeforeOperation(WalletOperationRequest operationRequest) {
        if (operationRequest.getAmount() < 1) {
            throw new AmountNotCorrectException();
        }
        WalletEntity wallet = walletRepo.findByUuid(operationRequest.getWalletUuid());
        if (wallet == null) {
            throw new WalletNotFoundException();
        }
        return wallet;
    }
}
