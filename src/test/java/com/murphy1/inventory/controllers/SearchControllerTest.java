package com.murphy1.inventory.controllers;

import com.murphy1.inventory.searching.SearchQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SearchControllerTest {

    @Mock
    SearchQuery searchQuery;

    @Mock
    Model model;

    private SearchController searchController;

    private MockMvc mockMvc;

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

    @Test
    void returnSearchWithValidationError() throws Exception{
        ObjectError error = new ObjectError("string","string");

        BindingResult bindingResult = new BindException("String", "String");
        bindingResult.addError(error);

        mockMvc.perform(get("/search/newsearch"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("forms/searchform"));
    }
}