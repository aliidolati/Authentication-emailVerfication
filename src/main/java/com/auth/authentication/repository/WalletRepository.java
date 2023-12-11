package com.example.authentication.repository;

import com.example.authentication.Model.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet , Long > {
}
