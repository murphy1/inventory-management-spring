package com.murphy1.inventory.controllers;

import com.murphy1.inventory.services.ElectronicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
