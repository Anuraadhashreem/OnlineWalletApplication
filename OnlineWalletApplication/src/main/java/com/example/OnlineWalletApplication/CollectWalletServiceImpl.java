package com.example.OnlineWalletApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public class CollectWalletServiceImpl implements CollectWalletService {

   // @Autowired
    private CollectWalletRepository walletRepository;
    @Override
    public WalletDto registerWallet(WalletDto walletDto) throws WalletException{
          return walletRepository.addWallet(walletDto);
//
        //return walletRegistered;
    }

    @Override
    public WalletDto getWallet(Integer walletId) throws WalletException{
        WalletDto walletFound = walletRepository.getWalletbyId(walletId);
        throwExceptionIfWalletNotFound(walletFound, "Wallet Id not present");
        return walletFound;
    }

    @Override
    public WalletDto replaceWallet(WalletDto walletDto) throws WalletException{

        WalletDto walletFound = walletRepository.getWalletbyId(walletDto.getWalletId());
        throwExceptionIfWalletNotFound(walletFound, "Wallet Id not present");
        return walletRepository.updateWallet(walletDto);
    }

    private static void throwExceptionIfWalletNotFound(WalletDto walletFound, String message) throws WalletException {
        if (walletFound == null)
            throw new WalletException(message);
    }

    @Override
    public WalletDto removeWallet(Integer walletId) throws WalletException
    {
      WalletDto walletFound = walletRepository.getWalletbyId(walletId);
        throwExceptionIfWalletNotFound(walletFound, "Product Id not found");
        return walletRepository.deleteWallet(walletId);
    }

    @Override
    public WalletDto addFundToWalletId(Integer walletId, Double amount) throws WalletException {
        WalletDto walletFound = walletRepository.getWalletbyId(walletId);
        throwExceptionIfWalletNotFound(walletFound, "Product Id not found");
        Double newBalance = walletRepository.getWalletbyId(walletId).getBalance()+amount;
        walletFound.setBalance(newBalance);
        return walletRepository.updateWallet(walletFound);

    }

    @Override
    public Boolean fundTransfer(Integer fromWalletId, Integer toWalletId, Double amount) throws WalletException {

        WalletDto fromWallet = walletRepository.getWalletbyId(fromWalletId);
        WalletDto toWallet = walletRepository.getWalletbyId(toWalletId);
        if(fromWallet==null||toWallet==null)
            throw new WalletException(" from or to wallet Id not found");

        else if (fromWallet==toWallet) {
            throw new WalletException("Same wallet transfer not permitted");

        }
        else if(fromWallet.getBalance()<amount){

            throw new WalletException("No sufficient balance");

        }
        Double balanceOne = walletRepository.getWalletbyId(fromWalletId).getBalance()-amount;
        walletRepository.getWalletbyId(fromWalletId).setBalance(balanceOne);
        walletRepository.updateWallet(fromWallet);

        Double balanceTwo = walletRepository.getWalletbyId(toWalletId).getBalance()+amount;
        walletRepository.getWalletbyId(toWalletId).setBalance(balanceTwo);
        walletRepository.updateWallet(toWallet);
        return true;

    }

    @Override
    public Double fundWithdrawalById(Integer walletId, Double amount) throws WalletException {

        WalletDto walletFound = walletRepository.getWalletbyId(walletId);
        if(walletFound==null)
            throw new WalletException("Product Id not found");
        else if (walletFound.getBalance()<amount) {
            throw new WalletException("Insufficient balance");

        }
        Double newBalance = walletFound.getBalance()-amount;
        walletRepository.getWalletbyId(walletId).setBalance(newBalance);
        walletRepository.updateWallet(walletFound);
        return walletFound.getBalance();
    }

    @Override
    public List<WalletDto> getAllWallet() {
        return walletRepository.getAll();
    }


}
