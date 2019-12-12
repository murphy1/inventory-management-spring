package com.murphy1.inventory.services;

import com.murphy1.inventory.model.Wallet;

import java.util.List;

public interface WalletService {
    List<Wallet> getAllWallets();
    Wallet getWalletByPrincipal();
    Wallet getWalletById(Long id);
    Wallet save(Wallet wallet);
    boolean financeRole();
    void depositFunds(Wallet wallet, Double amount);
    void withdrawFunds(Wallet wallet, Double amount);
}
