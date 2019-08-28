package com.murphy1.inventory.services.impl;

import com.murphy1.inventory.model.User;
import com.murphy1.inventory.model.Wallet;
import com.murphy1.inventory.repositories.UserRepository;
import com.murphy1.inventory.repositories.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    WalletRepository walletRepository;

    UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userServiceImpl = new UserServiceImpl(userRepository, walletRepository);
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

    @Test
    void saveUserTest() throws Exception{
        User user = new User();
        user.setId(1L);

        when(userRepository.save(any())).thenReturn(user);
        userRepository.save(any());

        assertEquals(Long.valueOf(1L), user.getId());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void findUserByIdTest() throws RuntimeException{
        Optional<User> returnedOptional = userRepository.findById(anyLong());

        when(userRepository.findById(anyLong())).thenReturn(returnedOptional);

        assertNotNull(returnedOptional);
        verify(userRepository, times(1)).findById(anyLong());
    }
}