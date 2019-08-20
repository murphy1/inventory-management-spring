package com.murphy1.inventory.services.impl;

import com.murphy1.inventory.model.User;
import com.murphy1.inventory.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userServiceImpl = new UserServiceImpl(userRepository);
    }

    @Test
    void getAllUsers() {
        // given
        List<User> users = new ArrayList<>();
        users.add(new User());

        // when
        when(userServiceImpl.getAllUsers()).thenReturn(users);
        List<User> callingList = userServiceImpl.getAllUsers();

        //then
        assertEquals(1, callingList.size());
        verify(userRepository, times(1)).findAll();
    }
}