package com.example.OnlineWalletApplication;

import java.util.List;

public interface CollectWalletRepository {

    WalletDto addWallet(WalletDto walletDto);

    WalletDto updateWallet(WalletDto walletDto);

    WalletDto getWalletbyId(Integer id);

    WalletDto deleteWallet(Integer walletDto);

    List<WalletDto> getAll();
}

