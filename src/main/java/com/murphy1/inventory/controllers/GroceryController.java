package com.murphy1.inventory.controllers;

import com.murphy1.inventory.model.Grocery;
import com.murphy1.inventory.services.GroceryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
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
    public String saveAndUpdate(@Valid @ModelAttribute("grocery") Grocery grocery, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError ->
                    log.debug(objectError.toString())
                    );
            return "forms/groceryform";
        }

        groceryService.save(grocery);

        return "redirect:/grocery.html";
    }

    @GetMapping("grocery/new")
    public String newGrocery(Model model){
        model.addAttribute("grocery", new Grocery());

        return "forms/groceryform";
    }

    @GetMapping("/grocery/update/{groceryId}")
    public String updateGrocery(@PathVariable String groceryId, Model model){
        model.addAttribute("grocery", groceryService.findById(Long.valueOf(groceryId)));

        return "forms/groceryform";
    }

    @GetMapping("/grocery/delete/{groceryId}")
    public String deleteGroceryById(@PathVariable String groceryId){
        groceryService.deleteById(Long.valueOf(groceryId));

        return "redirect:/grocery.html";
    }

}
