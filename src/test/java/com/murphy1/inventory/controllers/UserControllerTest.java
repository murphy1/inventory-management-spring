package com.murphy1.inventory.controllers;

import com.murphy1.inventory.model.User;
import com.murphy1.inventory.services.UserService;
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

class UserControllerTest {

    @Mock
    UserService userService;

    @Mock
    Model model;

    UserController userController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userController = new UserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
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

    @Test
    void newUserTest() throws Exception{
        mockMvc.perform(get("/user/new"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("forms/userform"));
    }

    @Test
    void updateUserTest() throws Exception{
        User user = new User();
        user.setId(1L);

        when(userService.findUserById(anyLong())).thenReturn(user);
        userService.findUserById(anyLong());

        mockMvc.perform(get("/user/update/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("forms/userform"));

        verify(userService, times(2)).findUserById(anyLong());
    }

    @Test
    void saveAndUpdateTest() throws Exception{
        User user = new User();
        user.setId(1L);

        when(userService.saveUser(any())).thenReturn(user);
        User returnedUser = userService.saveUser(any());

        assertEquals(Long.valueOf(1L), returnedUser.getId());
        verify(userService, times(1)).saveUser(any());

        mockMvc.perform(post("/user/new/user")
                .param("id", "")
                .param("firstName", "string")
                .param("lastName", "string")
                .param("username", "string")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/users.html"));
    }

    @Test
    void deleteUserById() throws Exception{
        mockMvc.perform(get("/user/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/users.html"));
    }

}