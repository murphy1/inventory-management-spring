package com.murphy1.inventory.controllers;

import com.murphy1.inventory.model.Electronic;
import com.murphy1.inventory.services.ElectronicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ElectronicController {

    private ElectronicService electronicService;

    public ElectronicController(ElectronicService electronicService) {
        this.electronicService = electronicService;
    }

    @RequestMapping({"/electronic", "/electronics", "/electronics.html"})
    public String getElectronics(Model model){

        model.addAttribute("electronics", electronicService.getAllElectronics());

        return "electronic";
    }

    @GetMapping("/electronic/new")
    public String createElectronic(Model model){
        model.addAttribute("electronic", new Electronic());

        return "forms/electronicform";
    }

    @PostMapping("/user/new/electronic")
    public String saveAndUpdate(@ModelAttribute Electronic electronic){
        Electronic savedElectronic = electronicService.save(electronic);

        return "redirect:/electronics.html";
    }

    @GetMapping("/electronic/update/{electronicId}")
    public String updateElectronic(@PathVariable String electronicId, Model model){
        model.addAttribute("electronic", electronicService.findById(Long.valueOf(electronicId)));

        return "forms/electronicform";
    }

    @GetMapping("/electronic/delete/{electronicId}")
    public String deleteElectronicById(@PathVariable String electronicId){
        electronicService.deleteById(Long.valueOf(electronicId));

        return "redirect:/electronics.html";
    }
}
