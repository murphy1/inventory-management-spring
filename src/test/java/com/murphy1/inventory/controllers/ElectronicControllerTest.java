package com.murphy1.inventory.controllers;

import com.murphy1.inventory.model.Electronic;
import com.murphy1.inventory.services.ElectronicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.eq;

class ElectronicControllerTest {

    @Mock
    ElectronicService electronicService;

    @Mock
    Model model;

    ElectronicController electronicController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        electronicController = new ElectronicController(electronicService);
        mockMvc = MockMvcBuilders.standaloneSetup(electronicController).build();
    }

    @Test
    void getElectronics() {
        // given
        List<Electronic> electronics = new ArrayList<>();
        electronics.add(new Electronic());
        electronics.add(new Electronic());

        when(electronicService.getAllElectronics()).thenReturn(electronics);

        ArgumentCaptor<List<Electronic>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        // when
        String returnValue = electronicController.getElectronics(model);

        // then
        assertEquals("electronic", returnValue);
        verify(electronicService, times(1)).getAllElectronics();
        verify(model, times(1)).addAttribute(eq("electronics"), argumentCaptor.capture());
        List<Electronic> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }

    @Test
    void createElectronic() throws Exception{
        mockMvc.perform(get("/electronic/new"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("electronic"))
                .andExpect(view().name("forms/electronicform"));
    }

    @Test
    void saveAndUpdate() throws Exception{
        Electronic electronic = new Electronic();

        when(electronicService.save(any())).thenReturn(electronic);
        electronicService.save(any());

        verify(electronicService, times(1)).save(any());

        mockMvc.perform(post("/user/new/electronic")
                .param("id", "")
                .param("name", "string")
                .param("price", String.valueOf(2.0))
                .param("description", "string")
                .param("brand", "string")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/electronics.html"));
    }

    @Test
    void updateElectronic() throws Exception{
        Electronic electronic = new Electronic();

        when(electronicService.findById(anyLong())).thenReturn(electronic);
        electronicService.findById(anyLong());

        verify(electronicService, times(1)).findById(anyLong());
        mockMvc.perform(get("/electronic/update/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("electronic"))
                .andExpect(view().name("forms/electronicform"));
    }

    @Test
    void deleteElectronicById() throws Exception{
        mockMvc.perform(get("/electronic/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/electronics.html"));
    }


}