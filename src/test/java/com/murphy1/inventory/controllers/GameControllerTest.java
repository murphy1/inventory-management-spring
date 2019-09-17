package com.murphy1.inventory.controllers;

import com.murphy1.inventory.model.Game;
import com.murphy1.inventory.services.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class GameControllerTest {

    @Mock
    GameService gameService;

    @Mock
    Model model;

    private GameController gameController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        gameController = new GameController(gameService);
        mockMvc = MockMvcBuilders.standaloneSetup(gameController).build();
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

    @Test
    void newGame() throws Exception{
        mockMvc.perform(get("/game/new"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("game"))
                .andExpect(view().name("forms/gameform"));
    }

    @Test
    void saveAndUpdate() throws Exception{
        Game game = new Game();

        when(gameService.save(any())).thenReturn(game);
        gameService.save(any());

        verify(gameService, times(1)).save(any());

        mockMvc.perform(post("/user/new/game")
                .param("id", "")
                .param("name", "string")
                .param("price", String.valueOf(2.0))
                .param("description", "string")
                .param("platform", "string")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("game"))
                .andExpect(view().name("redirect:/games.html"));
    }

    @Test
    void updateGame() throws Exception{
        Game game = new Game();
        when(gameService.findById(anyLong())).thenReturn(game);
        gameService.findById(anyLong());

        verify(gameService, times(1)).findById(anyLong());

        mockMvc.perform(get("/game/update/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("game"))
                .andExpect(view().name("forms/gameform"));
    }

    @Test
    void deleteGameById() throws Exception{
        gameService.deleteGameById(anyLong());

        verify(gameService, times(1)).deleteGameById(anyLong());

        mockMvc.perform(get("/game/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/games.html"));
    }
}