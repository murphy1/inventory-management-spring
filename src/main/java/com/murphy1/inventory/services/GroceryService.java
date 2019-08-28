package com.murphy1.inventory.services;

import com.murphy1.inventory.model.Grocery;

import java.util.List;

public interface GroceryService{

    List<Grocery> getAllGroceries();
    Grocery save(Grocery grocery);
}
