package com.murphy1.inventory.controllers;

import com.murphy1.inventory.model.Grocery;
import com.murphy1.inventory.services.GroceryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GroceryController {

    private GroceryService groceryService;

    public GroceryController(GroceryService groceryService) {
        this.groceryService = groceryService;
    }

    @RequestMapping({"/grocery", "/groceries", "/grocery.html", "/groceries.html"})
    public String getAllGroceries(Model model){

        model.addAttribute("groceries", groceryService.getAllGroceries());

        return "grocery";
    }

    @PostMapping("/user/new/grocery")
    public String saveAndUpdate(@ModelAttribute Grocery grocery){
        Grocery savedGrocery = groceryService.save(grocery);

        return "redirect:/grocery.html";
    }

    @GetMapping("grocery/new")
    public String newGrocery(Model model){
        model.addAttribute("grocery", new Grocery());

        return "forms/groceryform";
    }

}
