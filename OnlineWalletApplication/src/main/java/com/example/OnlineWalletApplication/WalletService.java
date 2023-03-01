package com.example.OnlineWalletApplication;

import java.util.List;

public interface WalletService {

    WalletDto registerWallet(WalletDto newWallet);
    WalletDto getWalletById(Integer walletId)throws WalletException;
    WalletDto updateWallet(WalletDto wallet)throws WalletException;
    WalletDto deleteWalletById(Integer walletId) throws WalletException;
    List<WalletDto> getAllWallet();

    WalletDto addFundToWalletId(Integer walletId, Double amount) throws WalletException;

    Boolean fundTransfer(Integer fromWalletId,Integer toWalletId, Double amount) throws WalletException;

    Double fundWithdrawalById(Integer walletId,Double amount) throws WalletException;

}
