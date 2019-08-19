package com.murphy1.inventory.controllers;

import com.murphy1.inventory.services.GroceryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GroceryControllerTest {

    @Mock
    GroceryService groceryService;

    @Mock
    Model model;

    GroceryController groceryController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        groceryController = new GroceryController(groceryService);
    }

    @Test
    public void getAllGroceries() {
        String returnType = groceryController.getAllGroceries(model);
        assertEquals("grocery", returnType);

        verify(groceryService, times(1)).getAllGroceries();

        verify(model, times(1)).addAttribute(eq("groceries"), anyList());
    }
}