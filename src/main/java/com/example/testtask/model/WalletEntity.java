package com.example.testtask.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "wallets")
public class WalletEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID uuid;

    private Integer balance;
}
