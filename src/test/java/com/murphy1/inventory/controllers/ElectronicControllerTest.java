package com.murphy1.inventory.controllers;

import com.murphy1.inventory.services.ElectronicService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ElectronicControllerTest {

    @Mock
    ElectronicService electronicService;

    @Mock
    Model model;

    ElectronicController electronicController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        electronicController = new ElectronicController(electronicService);
    }

    @Test
    public void getElectronics() {
        String returnValue = electronicController.getElectronics(model);
        assertEquals("electronic", returnValue);

        verify(electronicService, times(1)).getAllElectronics();

        verify(model, times(1)).addAttribute(eq("electronics"), anyList());
    }
}