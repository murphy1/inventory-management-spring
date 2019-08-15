package com.murphy1.inventory.repositories;

import com.murphy1.inventory.model.Grocery;
import org.springframework.data.repository.CrudRepository;

public interface GroceryRepository extends CrudRepository<Grocery, Long> {
}
