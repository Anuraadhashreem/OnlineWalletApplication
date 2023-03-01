package com.example.OnlineWalletApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class WalletController {

    @Autowired
    private WalletService walletService;


    @GetMapping("/getThemAll")
    public List<WalletDto> gettingAllWallet()
    {

        return walletService.getAllWallet();
    }

    @PostMapping("/addingWallet")
    @ResponseStatus(value = HttpStatus.CREATED)
    public WalletDto addWallet(@Valid @RequestBody WalletDto walletDto) throws WalletException
    {

        return walletService.registerWallet(walletDto);

    }

    @PutMapping("/getWalletById")
    public WalletDto getWallet(Integer walletIdToBereturned) throws WalletException
    {
        return walletService.getWalletById(walletIdToBereturned);
    }

    @PutMapping("/updateWallet")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public WalletDto updateWallet(WalletDto wallet) throws WalletException
    {
         return walletService.updateWallet(wallet);
    }


    @PatchMapping("/addAmount/id/{id}/amount/{amount}")
    public WalletDto addAmount(@PathVariable Double amount,@PathVariable Integer id) throws WalletException
    {
        return walletService.addFundToWalletId(id,amount);
    }
//
    @PatchMapping("/fundTransfer/{id1}/{id2}/{amount}")
    public Boolean fundTransfer(@PathVariable Integer id1,@PathVariable Integer id2,@PathVariable Double amount) throws WalletException
    {
        return walletService.fundTransfer(id1,id2,amount);
    }
//
    @PatchMapping ("/withdrawal/{id}/{amount}")
    public Double fundWithrawal(@PathVariable Integer id,@PathVariable Double amount) throws WalletException
    {
        return walletService.fundWithdrawalById(id,amount);
    }
//
//
    @DeleteMapping("/removeWallet")
    public WalletDto removeWallet(Integer walletId) throws WalletException
    {
        return walletService.deleteWalletById(walletId);
    }



}
