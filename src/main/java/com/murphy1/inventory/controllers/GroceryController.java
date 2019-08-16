package com.murphy1.inventory.controllers;

import com.murphy1.inventory.services.GroceryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GroceryController {

    private GroceryService groceryService;

    public GroceryController(GroceryService groceryService) {
        this.groceryService = groceryService;
    }

    @RequestMapping({"/grocery", "/groceries", "/grocery.html", "/groceries.html"})
    public String getAllGroceries(){
        return "grocery";
    }

}
