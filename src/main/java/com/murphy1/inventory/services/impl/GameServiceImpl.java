package com.murphy1.inventory.services.impl;

import com.murphy1.inventory.exceptions.NotFoundException;
import com.murphy1.inventory.model.Game;
import com.murphy1.inventory.repositories.GameRepository;
import com.murphy1.inventory.services.GameService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Game findById(Long id) {
        Optional<Game> returnedGame = gameRepository.findById(id);

        if (!returnedGame.isPresent()){
            throw new NotFoundException("Game does not exist!");
        }

        return returnedGame.get();
    }

    @Override
    public void deleteGameById(Long id) {
        gameRepository.deleteById(id);
    }
}
