package com.murphy1.inventory.services;

import com.murphy1.inventory.model.Furniture;

import java.util.List;

public interface FurnitureService {
    List<Furniture> getAllFurniture();
    Furniture save(Furniture furniture);
    Furniture findById(Long id);
    void deleteById(Long id);
}
