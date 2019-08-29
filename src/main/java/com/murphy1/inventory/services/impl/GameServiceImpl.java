package com.murphy1.inventory.services.impl;

import com.murphy1.inventory.model.Game;
import com.murphy1.inventory.repositories.GameRepository;
import com.murphy1.inventory.services.GameService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();
        gameRepository.findAll().forEach(games::add);
        return games;
    }

    @Override
    public Game save(Game game) {
        return gameRepository.save(game);
    }
}
