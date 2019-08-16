package com.murphy1.inventory.controllers;

import com.murphy1.inventory.services.GroceryService;
import com.murphy1.inventory.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private UserService userService;

    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping({"", "/", "index", "index.html"})
    public String getIndex(Model model){

        model.addAttribute("users", userService.getAllUsers());

        return "index";
    }

}
