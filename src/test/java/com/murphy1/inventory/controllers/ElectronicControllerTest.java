package com.murphy1.inventory.controllers;

import com.murphy1.inventory.model.Electronic;
import com.murphy1.inventory.services.ElectronicService;
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
import static org.mockito.Mockito.*;

class ElectronicControllerTest {

    @Mock
    ElectronicService electronicService;

    @Mock
    Model model;

    ElectronicController electronicController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        electronicController = new ElectronicController(electronicService);
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
}