package com.murphy1.inventory.controllers;

import com.murphy1.inventory.services.GroceryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private GroceryService groceryService;

    public IndexController(GroceryService groceryService) {
        this.groceryService = groceryService;
    }

    @RequestMapping({"", "/", "index", "index.html"})
    public String getIndex(Model model){

        model.addAttribute("groceries", groceryService.getAllGroceries());

        return "index";
    }

}
