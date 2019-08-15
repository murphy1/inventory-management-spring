package com.murphy1.inventory.repositories;

import com.murphy1.inventory.model.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GameRepository extends CrudRepository<Game, Long> {

    Optional<Game> findByName(String name);

}
