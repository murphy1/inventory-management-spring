package com.murphy1.inventory.services.impl;

import com.murphy1.inventory.exceptions.BadRequestException;
import com.murphy1.inventory.exceptions.NotFoundException;
import com.murphy1.inventory.model.Electronic;
import com.murphy1.inventory.model.Wallet;
import com.murphy1.inventory.repositories.ElectronicRepository;
import com.murphy1.inventory.services.ElectronicService;
import com.murphy1.inventory.services.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ElectronicServiceImpl implements ElectronicService {

    private ElectronicRepository electronicRepository;

    @Autowired
    private WalletService walletService;

    public ElectronicServiceImpl(ElectronicRepository electronicRepository) {
        this.electronicRepository = electronicRepository;
    }

    @Override
    public List<Electronic> getAllElectronics() {
        List<Electronic> electronics = new ArrayList<>();
        electronicRepository.findAll().forEach(electronics::add);
        return electronics;
    }

    @Override
    public Electronic save(Electronic electronic) {

        Double price = electronic.getPrice();
        Wallet wallet = walletService.getWalletByPrincipal();

        double newBalance = wallet.getBalance() - price;
        if (newBalance < 0){
            throw new BadRequestException("Not Enough Funds!");
        }else{
            wallet.setBalance(wallet.getBalance() - price);
            electronicRepository.save(electronic);
        }

        return electronic;
    }

    @Override
    public Electronic findById(Long id) {
        Optional<Electronic> returnedElectronic = electronicRepository.findById(id);

        if (!returnedElectronic.isPresent()){
            log.error("Electronic does not exist with id: "+id);
            throw new NotFoundException("Electronic does not exist!");
        }
        return returnedElectronic.get();
    }

    @Override
    public void deleteById(Long id) {
        electronicRepository.deleteById(id);
    }
}
