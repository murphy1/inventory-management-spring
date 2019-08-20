package com.murphy1.inventory.controllers;

import com.murphy1.inventory.model.Furniture;
import com.murphy1.inventory.services.FurnitureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class FurnitureControllerTestFive {

    @Mock
    FurnitureService furnitureService;

    @Mock
    Model model;

    FurnitureController furnitureController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        furnitureController = new FurnitureController(furnitureService);
    }

    @Test
    void getAllFurniture() {
        // given
        List<Furniture> furniture = new ArrayList<>();
        furniture.add(new Furniture());
        furniture.add(new Furniture());

        when(furnitureService.getAllFurniture()).thenReturn(furniture);

        ArgumentCaptor<List<Furniture>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        // when
        String returnValue = furnitureController.getAllFurniture(model);

        // then
        assertEquals("furniture", returnValue);
        verify(furnitureService, times(1)).getAllFurniture();
        verify(model, times(1)).addAttribute(eq("furniture"), argumentCaptor.capture());
        List<Furniture> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }
}