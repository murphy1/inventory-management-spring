package com.murphy1.inventory.controllers;

import com.murphy1.inventory.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class UserControllerTest {

    @Mock
    UserService userService;

    @Mock
    Model model;

    UserController userController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userController = new UserController(userService);
    }

    @Test
    public void getAllUsers() {
        String returnType = userController.getAllUsers(model);
        assertEquals("user", returnType);

        verify(userService, times(1)).getAllUsers();

        verify(model, times(1)).addAttribute(eq("users"), anyList());
    }
}