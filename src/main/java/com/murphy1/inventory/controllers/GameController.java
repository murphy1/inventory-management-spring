package com.murphy1.inventory.controllers;

import com.murphy1.inventory.model.Game;
import com.murphy1.inventory.services.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GameController {

    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping({"/game", "/games", "/game.html", "/games.html"})
    public String getAllGames(Model model){

        model.addAttribute("games", gameService.getAllGames());

        return "game";
    }

    @GetMapping("/game/new")
    public String newGame(Model model){
        model.addAttribute("game", new Game());

        return "forms/gameform";
    }

    @PostMapping("/user/new/game")
    public String saveAndUpdate(@ModelAttribute Game game, Model model){
        model.addAttribute("game", gameService.save(game));

        return "redirect:/games.html";
    }

}
