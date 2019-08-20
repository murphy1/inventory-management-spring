package com.murphy1.inventory.controllers;

import com.murphy1.inventory.model.Grocery;
import com.murphy1.inventory.services.GroceryService;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class GroceryControllerTestFive {

    @Mock
    GroceryService groceryService;

    @Mock
    Model model;

    GroceryController groceryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        groceryController = new GroceryController(groceryService);
    }

    @Test
    void getAllGroceries() {
        // given
        List<Grocery> groceries = new ArrayList<>();
        groceries.add(new Grocery());
        groceries.add(new Grocery());

        when(groceryService.getAllGroceries()).thenReturn(groceries);

        ArgumentCaptor<List<Grocery>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        // when
        String returnType = groceryController.getAllGroceries(model);

        // then
        assertEquals("grocery", returnType);
        verify(groceryService, times(1)).getAllGroceries();
        verify(model, times(1)).addAttribute(eq("groceries"), argumentCaptor.capture());
        List<Grocery> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }
}