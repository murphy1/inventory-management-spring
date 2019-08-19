package com.murphy1.inventory.controllers;

import com.murphy1.inventory.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class IndexControllerTest {

    @Mock
    UserService userService;

    @Mock
    Model model;

    IndexController indexController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(userService);
    }

    @Test
    public void getIndex() {
        String returnType = indexController.getIndex(model);
        assertEquals("index", returnType);

        verify(userService, times(1)).getAllUsers();

        verify(model, times(1)).addAttribute(eq("users"), anyList());
    }
}