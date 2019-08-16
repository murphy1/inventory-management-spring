package com.murphy1.inventory.services.impl;

import com.murphy1.inventory.model.Wallet;
import com.murphy1.inventory.repositories.WalletRepository;
import com.murphy1.inventory.services.WalletService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    private WalletRepository walletRepository;

    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public List<Wallet> getAllWallets() {
        List<Wallet> wallets = new ArrayList<>();
        walletRepository.findAll().forEach(wallets::add);
        return wallets;
    }
}
