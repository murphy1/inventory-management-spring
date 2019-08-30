package com.murphy1.inventory.controllers;

import com.murphy1.inventory.model.Furniture;
import com.murphy1.inventory.services.FurnitureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FurnitureController {

    private FurnitureService furnitureService;

    public FurnitureController(FurnitureService furnitureService) {
        this.furnitureService = furnitureService;
    }

    @RequestMapping({"/furniture", "/furniture.html"})
    public String getAllFurniture(Model model){

        model.addAttribute("furniture", furnitureService.getAllFurniture());

        return "furniture";
    }

    @GetMapping("/furniture/new")
    public String newFurniture(Model model){
        model.addAttribute("furniture", new Furniture());

        return "forms/furnitureform";
    }

    @PostMapping("/user/new/furniture")
    public String saveAndUpdate(@ModelAttribute Furniture furniture, Model model){
        model.addAttribute("furniture", furnitureService.save(furniture));

        return "redirect:/furniture.html";
    }

}
