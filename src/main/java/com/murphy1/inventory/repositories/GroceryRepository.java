package com.murphy1.inventory.repositories;

import com.murphy1.inventory.model.Grocery;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GroceryRepository extends CrudRepository<Grocery, Long> {

    Optional<Grocery> findByName(String name);

}
