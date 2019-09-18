package com.murphy1.inventory.controllers;

import com.murphy1.inventory.model.Grocery;
import com.murphy1.inventory.services.GroceryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.format.datetime.joda.DateTimeParser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.eq;

class GroceryControllerTest {

    @Mock
    GroceryService groceryService;

    @Mock
    Model model;

    private GroceryController groceryController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        groceryController = new GroceryController(groceryService);
        mockMvc = MockMvcBuilders.standaloneSetup(groceryController).build();
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

    @Test
    void saveAndUpdate() throws Exception{
        Grocery grocery = new Grocery();

        when(groceryService.save(any())).thenReturn(grocery);
        groceryService.save(any());

        verify(groceryService, times(1)).save(any());

        mockMvc.perform(post("/user/new/grocery")
                .param("id", "")
                .param("name", "string")
                .param("price", String.valueOf(2.0))
                .param("description", "string")
                .param("expiration", "12/12/19")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/grocery.html"));
    }

    @Test
    void newGrocery() throws Exception{
        mockMvc.perform(get("/grocery/new"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("grocery"))
                .andExpect(view().name("forms/groceryform"));
    }

    @Test
    void updateGrocery() throws Exception{
        Grocery grocery = new Grocery();

        when(groceryService.findById(any())).thenReturn(grocery);
        groceryService.findById(any());

        verify(groceryService, times(1)).findById(any());
        mockMvc.perform(get("/grocery/update/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("grocery"))
                .andExpect(view().name("forms/groceryform"));
    }

    @Test
    void deleteGrocery() throws Exception{
        groceryService.deleteById(anyLong());
        verify(groceryService, times(1)).deleteById(anyLong());

        mockMvc.perform(get("/grocery/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/grocery.html"));
    }
}