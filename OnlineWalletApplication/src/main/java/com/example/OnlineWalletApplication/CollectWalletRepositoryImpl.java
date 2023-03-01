package com.example.OnlineWalletApplication;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CollectWalletRepositoryImpl implements CollectWalletRepository {

    Map<Integer,WalletDto> walletMap= new HashMap<>();
    @Override
    public WalletDto addWallet(WalletDto walletDto){
        WalletDto walletAdded = walletMap.put(walletDto.getWalletId(),walletDto);
        return walletAdded;
    }

    @Override
    public WalletDto updateWallet(WalletDto walletDto) {
        WalletDto wallet =  walletMap.replace(walletDto.getWalletId(),walletDto);
        return walletMap.get(wallet.getWalletId());
    }

    @Override
    public WalletDto getWalletbyId(Integer id) {
        return walletMap.get(id);
    }

    @Override
    public WalletDto deleteWallet(Integer walletDto) {

        return walletMap.remove(walletDto);
    }

    @Override
    public List<WalletDto> getAll() {
        List<WalletDto> list = new ArrayList<>();
        walletMap.values().forEach((v)->list.add(v));
        return list;
    }
}
