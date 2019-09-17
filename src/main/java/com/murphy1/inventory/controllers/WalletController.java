package com.murphy1.inventory.controllers;

import com.murphy1.inventory.model.Wallet;
import com.murphy1.inventory.services.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class WalletController {

    private WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/wallet")
    public String viewWallet(Model model){

        if (walletService.financeRole() == true){
            model.addAttribute("wallet", walletService.getAllWallets());
        }else{
            model.addAttribute("wallet", walletService.getWalletByPrincipal());
        }

        return "wallet.html";
    }

    @GetMapping("/update/wallet/{walletId}")
    public String updateWallet(@PathVariable String walletId, Model model){
        model.addAttribute("wallet", walletService.getWalletById(Long.valueOf(walletId)));

        return "forms/walletform";
    }

    @PostMapping("/update/balancedeposit")
    public String saveWalletDeposit(@ModelAttribute Wallet wallet){

        Wallet getWallet = walletService.getWalletByPrincipal();
        getWallet.setBalance(getWallet.getBalance() + wallet.getBalance());

        walletService.save(getWallet);

        return "redirect:/wallet";
    }

    @PostMapping("/update/balancewithdraw")
    public String saveWalletWithdraw(@ModelAttribute Wallet wallet){

        Wallet getWallet = walletService.getWalletByPrincipal();
        getWallet.setBalance(getWallet.getBalance() - wallet.getBalance());

        walletService.save(getWallet);

        return "redirect:/wallet";
    }

}
