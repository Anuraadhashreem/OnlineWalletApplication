package com.example.OnlineWalletApplication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@PropertySource("classpath:application.properties")
@Entity
public class WalletDto {
    public Integer getWalletId() {
        return walletId;
    }

    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }

    public String getWalletUserName() {
        return walletUserName;
    }

    public void setWalletUserName(String walletUserName) {
        this.walletUserName = walletUserName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getWalletEmail() {
        return walletEmail;
    }

    public void setWalletEmail(String walletEmail) {
        this.walletEmail = walletEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull(message = "id should not be null")
    @Id
    private Integer walletId;

    @NotBlank(message = "Name cant be null, it should contain chars")
    @Pattern(regexp = "[a-zA-Z]{3,16}", message = "Name should contain min 3 & max 16 chars , no digits and special chars allowed.")
    private String walletUserName;


    @NotNull(message = "balance should not be null")
    @Value("100.0")
    private Double balance;

    @Email(message = "Email not valid")
    private String walletEmail;

    @NotBlank
    private String password;

    public WalletDto(Integer walletId, String walletUserName, Double balance, String walletEmail, String password) {
        this.walletId = walletId;
        this.walletUserName = walletUserName;
        this.balance = balance;
        this.walletEmail = walletEmail;
        this.password = password;
    }

    public WalletDto() {

    }


    @Override
    public String toString() {
        return "WalletDto{" +
                "walletId=" + walletId +
                ", walletUserName='" + walletUserName + '\'' +
                ", balance=" + balance +
                ", walletEmail='" + walletEmail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}


