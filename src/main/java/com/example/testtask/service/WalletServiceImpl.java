package com.example.testtask.service;

import com.example.testtask.dto.GetWalletListResponse;
import com.example.testtask.dto.WalletDto;
import com.example.testtask.dto.WalletOperationRequest;
import com.example.testtask.dto.WalletOperationResponse;
import com.example.testtask.exceptions.throwns.AmountNotCorrectException;
import com.example.testtask.exceptions.throwns.InsufficientFundsOnWalletException;
import com.example.testtask.exceptions.throwns.WalletNotFoundException;
import com.example.testtask.exceptions.throwns.WalletsNotFoundException;
import com.example.testtask.model.WalletEntity;
import com.example.testtask.repository.WalletRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
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
    public WalletOperationResponse deposit(WalletOperationRequest operationRequest) {
        if (operationRequest.getAmount() < 1) {
            throw new AmountNotCorrectException();
        }
        synchronized (walletRepo) {
            WalletEntity wallet = getWalletFromDbBeforeOperation(operationRequest);
            wallet.setBalance(wallet.getBalance() + operationRequest.getAmount());
            walletRepo.save(wallet);
        }
        return new WalletOperationResponse(true, "");
    }

    @Override
    public WalletOperationResponse withdraw(WalletOperationRequest operationRequest) {
        if (operationRequest.getAmount() < 1) {
            throw new AmountNotCorrectException();
        }
        synchronized (walletRepo) {
            WalletEntity wallet = getWalletFromDbBeforeOperation(operationRequest);
            if (operationRequest.getAmount() > wallet.getBalance()) {
                throw new InsufficientFundsOnWalletException();
            }
            wallet.setBalance(wallet.getBalance() - operationRequest.getAmount());
            walletRepo.save(wallet);
        }
        return new WalletOperationResponse(true, "");
    }

    @Override
    public GetWalletListResponse getWalletList() {
        List<WalletEntity> entityList = walletRepo.findAll();
        if (entityList.isEmpty()) {
            throw new WalletsNotFoundException();
        }
        List<WalletDto> walletDtoList =transformationImWalletDto(entityList);
        return new GetWalletListResponse(walletDtoList);
    }

    private WalletEntity getWalletFromDbBeforeOperation(WalletOperationRequest operationRequest) {
        WalletEntity wallet = walletRepo.findByUuid(operationRequest.getWalletUuid());
        if (wallet == null) {
            throw new WalletNotFoundException();
        }
        return wallet;
    }

    private List<WalletDto> transformationImWalletDto(List<WalletEntity> entityList) {
        List<WalletDto> walletDtoList = new ArrayList<>();
        for (WalletEntity walletEntity : entityList) {
            WalletDto  walletDto = new WalletDto(walletEntity.getUuid(), walletEntity.getBalance());
            walletDtoList.add(walletDto);
        }
        return walletDtoList;
    }
}
