package com.example.testtask.repository;

import com.example.testtask.model.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletRepo extends JpaRepository<WalletEntity, Integer> {
    WalletEntity findByUuid(UUID uuid);
}
