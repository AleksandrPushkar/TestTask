package com.example.testtask.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wallets")
public class WalletEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID uuid;

    private Integer balance;
}
