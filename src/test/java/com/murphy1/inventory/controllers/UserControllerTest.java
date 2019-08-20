package com.murphy1.inventory.controllers;

import com.murphy1.inventory.model.User;
import com.murphy1.inventory.services.UserService;
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
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    UserService userService;

    @Mock
    Model model;

    UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userController = new UserController(userService);
    }

    @Test
    void getAllUsers() {
        // given
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());

        when(userService.getAllUsers()).thenReturn(users);

        ArgumentCaptor<List<User>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        // when
        String returnType = userController.getAllUsers(model);

        // then
        assertEquals("user", returnType);
        verify(userService, times(1)).getAllUsers();
        verify(model, times(1)).addAttribute(eq("users"), argumentCaptor.capture());
        List<User> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }
}