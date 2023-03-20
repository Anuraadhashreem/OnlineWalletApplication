package com.example.OnlineWalletApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService{

    @Autowired
    private WalletRepository walletRepository;


    @Override
    public WalletDto registerWallet(WalletDto newWallet) {
        return this.walletRepository.save(newWallet);
    }

    @Override
    public WalletDto getWalletById(Integer walletId) throws WalletException {
        Optional<WalletDto> walletOptional = getWalletDto(walletId);

        return walletOptional.get();
    }

    private Optional<WalletDto> getWalletDto(Integer walletId) throws WalletException {
        Optional<WalletDto> walletOptional = this.walletRepository.findById(walletId);
        if(walletOptional.isEmpty())
            throw new WalletException("Wallet  id could not be found");
        return walletOptional;
    }

    @Override
    public WalletDto updateWallet(WalletDto wallet) throws WalletException {
        getWalletDto(wallet.getWalletId());
        return this.walletRepository.save(wallet);

    }

    @Override
    public WalletDto deleteWalletById(Integer walletId) throws WalletException {
        Optional<WalletDto> walletOptional = getWalletDto(walletId);
        WalletDto deleteWallet = walletOptional.get();
        this.walletRepository.delete(deleteWallet);
        return deleteWallet;
    }

    @Override
    public List<WalletDto> getAllWallet() {
        return this.walletRepository.getAllWallets();
    }


    @Override
    public WalletDto addFundToWalletId(Integer walletId, Double amount) throws WalletException {
        Optional<WalletDto> walletOptional = getWalletDto(walletId);
        WalletDto actionWallet = walletOptional.get();
        Double newBalance = actionWallet.getBalance()+amount;
        actionWallet.setBalance(newBalance);
        return walletRepository.save(actionWallet);

    }

    @Override
    public Boolean fundTransfer(Integer fromWalletId, Integer toWalletId, Double amount) throws WalletException {


        Optional<WalletDto> walletOptional1 = getWalletDto(fromWalletId);
        Optional<WalletDto> walletOptional2 = getWalletDto(toWalletId);


        if (walletOptional1==walletOptional2) {
            throw new WalletException("Same wallet transfer not permitted");

        }
        else if(walletOptional1.get().getBalance()<amount){

            throw new WalletException("No sufficient balance");

        }
        updateBalance(walletOptional1.get().getBalance() - amount, walletOptional1);

        updateBalance(walletOptional1.get().getBalance() + amount, walletOptional1);
        return true;

    }

    @Override
    public Double fundWithdrawalById(Integer walletId, Double amount) throws WalletException {

        Optional<WalletDto> walletOptional1 = getWalletDto(walletId);

        if (walletOptional1.get().getBalance()<amount) {
            throw new WalletException("Insufficient balance");

        }
        updateBalance(walletOptional1.get().getBalance() - amount, walletOptional1);
        return walletOptional1.get().getBalance();
    }

    private void updateBalance(double walletOptional1, Optional<WalletDto> walletOptional11) {
        Double balanceOne = walletOptional1;
        walletOptional11.get().setBalance(balanceOne);
        walletRepository.save(walletOptional11.get());
    }
}
