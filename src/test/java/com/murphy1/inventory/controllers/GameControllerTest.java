package com.murphy1.inventory.controllers;

import com.murphy1.inventory.services.GameService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GameControllerTest {

    @Mock
    GameService gameService;

    @Mock
    Model model;

    GameController gameController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        gameController = new GameController(gameService);
    }

    @Test
    public void getAllGames() {
        String returnType = gameController.getAllGames(model);
        assertEquals("game", returnType);

        verify(gameService, times(1)).getAllGames();

        verify(model, times(1)).addAttribute(eq("games"), anyList());
    }
}