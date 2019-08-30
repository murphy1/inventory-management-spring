package com.murphy1.inventory.controllers;

import com.murphy1.inventory.model.User;
import com.murphy1.inventory.searching.SearchQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class SearchControllerTest {

    @Mock
    SearchQuery searchQuery;

    @Mock
    Model model;

    SearchController searchController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        searchController = new SearchController(searchQuery);
        mockMvc = MockMvcBuilders.standaloneSetup(searchController).build();
    }

    @Test
    void newSearch() throws Exception{
        mockMvc.perform(get("/search"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("searchObject"))
                .andExpect(view().name("forms/searchform"));
    }
}