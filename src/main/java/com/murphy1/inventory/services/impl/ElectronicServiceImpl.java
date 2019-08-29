package com.murphy1.inventory.services.impl;

import com.murphy1.inventory.model.Electronic;
import com.murphy1.inventory.repositories.ElectronicRepository;
import com.murphy1.inventory.services.ElectronicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
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

    @Override
    public Electronic findById(Long id) {
        Optional<Electronic> returnedElectronic = electronicRepository.findById(id);

        if (!returnedElectronic.isPresent()){
            log.error("Electronic does not exist with id: "+id);
            throw new RuntimeException("Electronic does not exist!");
        }
        return returnedElectronic.get();
    }

    @Override
    public void deleteById(Long id) {
        electronicRepository.deleteById(id);
    }
}
