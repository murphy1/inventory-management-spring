package com.murphy1.inventory.controllers;

import com.murphy1.inventory.services.FurnitureService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FurnitureControllerTest {

    @Mock
    FurnitureService furnitureService;

    @Mock
    Model model;

    FurnitureController furnitureController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        furnitureController = new FurnitureController(furnitureService);
    }

    @Test
    public void getAllFurniture() {
        String returnValue = furnitureController.getAllFurniture(model);
        assertEquals("furniture", returnValue);

        verify(furnitureService, times(1)).getAllFurniture();

        verify(model, times(1)).addAttribute(eq("furniture"), anyList());
    }
}