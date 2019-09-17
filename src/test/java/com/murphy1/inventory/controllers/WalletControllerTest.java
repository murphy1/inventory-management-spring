package com.murphy1.inventory.controllers;

import com.murphy1.inventory.services.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class WalletControllerTest {

    @Mock
    WalletService service;

    @Mock
    Model model;

    private WalletController walletController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        walletController = new WalletController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(walletController).build();
    }

    @Test
    void viewWallet() throws Exception{
        mockMvc.perform(get("/wallet"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("wallet.html"));
    }

    @Test
    void updateWallet() {
    }

    @Test
    void saveWallet() {
    }
}