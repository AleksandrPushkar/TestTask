package com.example.testtask.repository;

import com.example.testtask.model.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepo extends JpaRepository<WalletEntity, Integer> {
}
