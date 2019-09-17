package com.murphy1.inventory.controllers;

import com.murphy1.inventory.model.Wallet;
import com.murphy1.inventory.services.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class WalletController {

    private WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/wallet")
    public String viewWallet(Model model){

        model.addAttribute("wallet", walletService.getWalletByPrincipal());

        return "wallet.html";
    }

    @GetMapping("/update/wallet")
    public String updateWallet(Model model){
        model.addAttribute("wallet", walletService.getWalletByPrincipal());

        return "forms/walletform";
    }

    @PostMapping("/update/balance")
    public String saveWallet(@ModelAttribute Wallet wallet){

        Wallet getWallet = walletService.getWalletByPrincipal();
        getWallet.setBalance(getWallet.getBalance() + wallet.getBalance());

        walletService.save(getWallet);

        return "redirect:/wallet";
    }

}
