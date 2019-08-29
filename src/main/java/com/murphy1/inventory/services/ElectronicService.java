package com.murphy1.inventory.services;

import com.murphy1.inventory.model.Electronic;

import java.util.List;

public interface ElectronicService {
    List<Electronic> getAllElectronics();
    Electronic save(Electronic electronic);
    Electronic findById(Long id);
    void deleteById(Long id);
}
