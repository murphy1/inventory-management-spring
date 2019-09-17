package com.murphy1.inventory.services.impl;

import com.murphy1.inventory.model.User;
import com.murphy1.inventory.model.Wallet;
import com.murphy1.inventory.repositories.UserRepository;
import com.murphy1.inventory.repositories.WalletRepository;
import com.murphy1.inventory.services.WalletService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {

    private WalletRepository walletRepository;
    private UserRepository userRepository;

    public WalletServiceImpl(WalletRepository walletRepository, UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Wallet> getAllWallets() {
        List<Wallet> wallets = new ArrayList<>();
        walletRepository.findAll().forEach(wallets::add);
        return wallets;
    }

    public Wallet getWalletByPrincipal(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipal = authentication.getName();

        Optional<User> user = userRepository.findByUsername(currentPrincipal);

        return user.get().getWallet();
    }

    public Wallet save(Wallet wallet){
        walletRepository.save(wallet);

        return wallet;
    }
}
