package com.murphy1.inventory.services.impl;

import com.murphy1.inventory.model.Game;
import com.murphy1.inventory.repositories.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class GameServiceImplTest {

    @Mock
    GameRepository gameRepository;

    GameServiceImpl gameServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        gameServiceImpl = new GameServiceImpl(gameRepository);
    }

    @Test
    void getAllGames() {
        // given
        List<Game> games = new ArrayList<>();
        games.add(new Game());

        // when
        when(gameServiceImpl.getAllGames()).thenReturn(games);
        List<Game> callingList = gameServiceImpl.getAllGames();

        // then
        assertEquals(1, callingList.size());
        verify(gameRepository, times(1)).findAll();
    }

    @Test
    void save(){
        Game game = new Game();

        when(gameRepository.save(any())).thenReturn(game);
        gameRepository.save(any());

        verify(gameRepository, times(1)).save(any());
    }

    @Test
    void updateGame() throws Exception{
        Game game = new Game();

        when(gameRepository.findById(anyLong())).thenReturn(Optional.of(game));
        gameRepository.findById(anyLong());

        verify(gameRepository, times(1)).findById(anyLong());
    }

    @Test
    void deleteGameById() throws Exception{
        Game game = new Game();

        gameRepository.deleteById(anyLong());

        verify(gameRepository, times(1)).deleteById(anyLong());
    }
}