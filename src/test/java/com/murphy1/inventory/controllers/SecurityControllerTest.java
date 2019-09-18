package com.murphy1.inventory.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SecurityControllerTest {

    private SecurityController securityController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        securityController = new SecurityController();
        mockMvc = MockMvcBuilders.standaloneSetup(securityController).build();
    }

    @Test
    void forbidden() throws Exception {
        mockMvc.perform(get("/403"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("exceptions/403error"));
    }
}