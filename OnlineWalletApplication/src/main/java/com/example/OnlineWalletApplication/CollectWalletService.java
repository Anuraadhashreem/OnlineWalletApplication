package com.example.OnlineWalletApplication;

import java.util.List;

public interface CollectWalletService {

    WalletDto registerWallet(WalletDto walletDto) throws WalletException;

    WalletDto getWallet(Integer walletId) throws WalletException;

    WalletDto replaceWallet(WalletDto walletDto) throws WalletException;

    WalletDto removeWallet(Integer walletId) throws WalletException;

    WalletDto addFundToWalletId(Integer walletId, Double amount) throws WalletException;

    Boolean fundTransfer(Integer fromWalletId,Integer toWalletId, Double amount) throws WalletException;

    Double fundWithdrawalById(Integer walletId,Double amount) throws WalletException;

    List<WalletDto> getAllWallet();
}
