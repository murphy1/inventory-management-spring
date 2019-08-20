package com.murphy1.inventory.controllers;

import com.murphy1.inventory.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;

class IndexControllerTestFive {

    @Mock
    UserService userService;

    @Mock
    Model model;

    IndexController indexController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(userService);
    }

    @Test
    void getIndex() {
        String returnValue = indexController.getIndex(model);
        assertEquals("index", returnValue);
    }
}