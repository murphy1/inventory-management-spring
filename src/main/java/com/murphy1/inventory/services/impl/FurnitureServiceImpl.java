package com.murphy1.inventory.services.impl;

import com.murphy1.inventory.model.Furniture;
import com.murphy1.inventory.repositories.FurnitureRepository;
import com.murphy1.inventory.services.FurnitureService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FurnitureServiceImpl implements FurnitureService {

    private FurnitureRepository furnitureRepository;

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
        return furnitureRepository.save(furniture);
    }
}
