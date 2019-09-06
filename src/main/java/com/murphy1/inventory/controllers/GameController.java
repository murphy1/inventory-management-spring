package com.murphy1.inventory.controllers;

import com.murphy1.inventory.model.Game;
import com.murphy1.inventory.services.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
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
    public String saveAndUpdate(@Valid @ModelAttribute("game") Game game, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError ->
                    log.debug(objectError.toString())
                    );
            return "forms/gameform";
        }

        gameService.save(game);

        return "redirect:/games.html";
    }

    @GetMapping("/game/update/{gameId}")
    public String updateGame(@PathVariable String gameId, Model model){
        model.addAttribute("game", gameService.findById(Long.valueOf(gameId)));

        return "forms/gameform";
    }

    @GetMapping("/game/delete/{gameId}")
    public String deleteGameById(@PathVariable String gameId){
        gameService.deleteGameById(Long.valueOf(gameId));

        return "redirect:/games.html";
    }

}
