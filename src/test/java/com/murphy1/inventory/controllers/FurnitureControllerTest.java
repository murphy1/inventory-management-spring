package com.murphy1.inventory.controllers;

import com.murphy1.inventory.model.Furniture;
import com.murphy1.inventory.services.FurnitureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class FurnitureControllerTest {

    @Mock
    FurnitureService furnitureService;

    @Mock
    Model model;

    FurnitureController furnitureController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        furnitureController = new FurnitureController(furnitureService);
        mockMvc = MockMvcBuilders.standaloneSetup(furnitureController).build();
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

    @Test
    void newFurniture() throws Exception{
        mockMvc.perform(get("/furniture/new"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("furniture"))
                .andExpect(view().name("forms/furnitureform"));
    }

    @Test
    void saveAndUpdate() throws Exception{
        Furniture furniture = new Furniture();

        when(furnitureService.save(any())).thenReturn(furniture);
        furnitureService.save(any());

        verify(furnitureService, times(1)).save(any());
        mockMvc.perform(post("/user/new/furniture")
                .param("id", "")
                .param("name", "string")
                .param("price", String.valueOf(2.0))
                .param("description", "string")
                .param("store", "string")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("furniture"))
                .andExpect(view().name("redirect:/furniture.html"));
    }

    @Test
    void updateFurniture() throws Exception{
        Furniture furniture = new Furniture();

        when(furnitureService.findById(anyLong())).thenReturn(furniture);
        furnitureService.findById(anyLong());

        verify(furnitureService, times(1)).findById(anyLong());
        mockMvc.perform(get("/furniture/update/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("furniture"))
                .andExpect(view().name("forms/furnitureform"));
    }

    @Test
    void deleteFurniture() throws Exception{
        furnitureService.deleteById(anyLong());

        verify(furnitureService, times(1)).deleteById(anyLong());

        mockMvc.perform(get("/furniture/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/furniture.html"));
    }
}