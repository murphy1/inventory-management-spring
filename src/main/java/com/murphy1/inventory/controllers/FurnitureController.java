package com.murphy1.inventory.controllers;

import com.murphy1.inventory.model.Furniture;
import com.murphy1.inventory.services.FurnitureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
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
    public String saveAndUpdate(@Valid @ModelAttribute("furniture") Furniture furniture, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError ->
                    log.debug(objectError.toString())
                    );
            return "forms/furnitureform";
        }

        furnitureService.save(furniture);

        return "redirect:/furniture.html";
    }

    @GetMapping("furniture/update/{furnitureId}")
    public String updateFurniture(@PathVariable String furnitureId, Model model){
        model.addAttribute("furniture", furnitureService.findById(Long.valueOf(furnitureId)));

        return "forms/furnitureform";
    }

    @GetMapping("furniture/delete/{furnitureId}")
    public String deleteFurniture(@PathVariable String furnitureId, Model model){
        furnitureService.deleteById(Long.valueOf(furnitureId));

        return "redirect:/furniture.html";
    }

}
