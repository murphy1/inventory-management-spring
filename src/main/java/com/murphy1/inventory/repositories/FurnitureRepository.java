package com.murphy1.inventory.repositories;

import com.murphy1.inventory.model.Furniture;
import org.springframework.data.repository.CrudRepository;

public interface FurnitureRepository extends CrudRepository<Furniture, Long> {
}
