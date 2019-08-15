package com.murphy1.inventory.services.impl;

import com.murphy1.inventory.model.Grocery;
import com.murphy1.inventory.repositories.GroceryRepository;
import com.murphy1.inventory.services.GroceryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
