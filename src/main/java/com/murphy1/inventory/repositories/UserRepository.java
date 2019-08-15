package com.murphy1.inventory.repositories;

import com.murphy1.inventory.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
