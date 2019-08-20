package com.murphy1.inventory.controllers;

import com.murphy1.inventory.model.Game;
import com.murphy1.inventory.services.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class GameControllerTest {

    @Mock
    GameService gameService;

    @Mock
    Model model;

    GameController gameController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        gameController = new GameController(gameService);
    }

    @Test
    void getAllGames() {
        // given
        List<Game> games = new ArrayList<>();
        games.add(new Game());
        games.add(new Game());

        when(gameService.getAllGames()).thenReturn(games);

        ArgumentCaptor<List<Game>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        // when
        String returnType = gameController.getAllGames(model);

        // then
        assertEquals("game", returnType);
        verify(gameService, times(1)).getAllGames();
        verify(model, times(1)).addAttribute(eq("games"), argumentCaptor.capture());
        List<Game> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }
}