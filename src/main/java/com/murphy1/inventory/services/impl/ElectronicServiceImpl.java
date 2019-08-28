package com.murphy1.inventory.services.impl;

import com.murphy1.inventory.model.Electronic;
import com.murphy1.inventory.repositories.ElectronicRepository;
import com.murphy1.inventory.services.ElectronicService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ElectronicServiceImpl implements ElectronicService {

    private ElectronicRepository electronicRepository;

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
        electronicRepository.save(electronic);
        return electronic;
    }
}
