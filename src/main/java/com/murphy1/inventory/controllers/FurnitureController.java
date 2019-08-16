package com.murphy1.inventory.controllers;

import com.murphy1.inventory.services.FurnitureService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FurnitureController {

    private FurnitureService furnitureService;

    public FurnitureController(FurnitureService furnitureService) {
        this.furnitureService = furnitureService;
    }

    @RequestMapping({"/furniture", "/furniture.html"})
    public String getAllFurniture(){
        return "furniture";
    }

}
