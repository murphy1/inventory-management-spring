package com.murphy1.inventory.controllers;

import com.murphy1.inventory.services.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GameController {

    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping({"/game", "/games", "/game.html", "/games.html"})
    public String getAllGames(){
        return "game";
    }

}
