package com.murphy1.inventory.services;

import com.murphy1.inventory.model.Game;

import java.util.List;

public interface GameService {
    List<Game> getAllGames();
    Game save(Game game);
    Game findById(Long id);
    void deleteGameById(Long id);
}
