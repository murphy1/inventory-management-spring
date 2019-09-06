package com.murphy1.inventory.services.impl;

import com.murphy1.inventory.exceptions.NotFoundException;
import com.murphy1.inventory.model.Grocery;
import com.murphy1.inventory.repositories.GroceryRepository;
import com.murphy1.inventory.services.GroceryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroceryServiceImpl implements GroceryService {

    private GroceryRepository groceryRepository;

    public GroceryServiceImpl(GroceryRepository groceryRepository) {
        this.groceryRepository = groceryRepository;
    }

    @Override
    public List<Grocery> getAllGroceries() {

        List<Grocery> groceries = new ArrayList<>();

        groceryRepository.findAll().iterator().forEachRemaining(groceries :: add);

        return groceries;
    }

    @Override
    public Grocery save(Grocery grocery) {

        groceryRepository.save(grocery);

        return grocery;
    }

    @Override
    public Grocery findById(Long id) {

        Optional<Grocery> returnedGrocery = groceryRepository.findById(id);

        if (!returnedGrocery.isPresent()){
            throw new NotFoundException("Grocery does not exist!");
        }

        return returnedGrocery.get();
    }

    @Override
    public void deleteById(Long id) {
        groceryRepository.deleteById(id);
    }
}
