package com.example.OnlineWalletApplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface WalletRepository extends JpaRepository<WalletDto,Integer> {


    @Query(
            value = "SELECT * FROM WALLET_DTO u",
            nativeQuery = true)
    List<WalletDto> getAllWallets();



}



