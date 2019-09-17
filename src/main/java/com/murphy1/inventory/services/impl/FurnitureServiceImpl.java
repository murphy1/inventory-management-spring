package com.murphy1.inventory.services.impl;

import com.murphy1.inventory.exceptions.BadRequestException;
import com.murphy1.inventory.exceptions.NotFoundException;
import com.murphy1.inventory.model.Furniture;
import com.murphy1.inventory.model.Wallet;
import com.murphy1.inventory.repositories.FurnitureRepository;
import com.murphy1.inventory.services.FurnitureService;
import com.murphy1.inventory.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FurnitureServiceImpl implements FurnitureService {

    private FurnitureRepository furnitureRepository;

    @Autowired
    WalletService walletService;

    public FurnitureServiceImpl(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    @Override
    public List<Furniture> getAllFurniture() {
        List<Furniture> furnitureList = new ArrayList<>();
        furnitureRepository.findAll().forEach(furnitureList::add);
        return furnitureList;
    }

    @Override
    public Furniture save(Furniture furniture) {

        Double price = furniture.getPrice();
        Wallet wallet = walletService.getWalletByPrincipal();

        double newBalance = wallet.getBalance() - price;
        if (newBalance < 0){
            throw new BadRequestException("Not Enough Funds!");
        }else{
            wallet.setBalance(wallet.getBalance() - price);
            furnitureRepository.save(furniture);
        }

        return furniture;
    }

    @Override
    public Furniture findById(Long id) {
        Optional<Furniture> furnitureOptional = furnitureRepository.findById(id);

        if (!furnitureOptional.isPresent()){
            throw new NotFoundException("Furniture does not exist!");
        }

        return furnitureOptional.get();
    }

    @Override
    public void deleteById(Long id) {
        furnitureRepository.deleteById(id);
    }
}
