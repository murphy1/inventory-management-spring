package com.murphy1.inventory.services.impl;

import com.murphy1.inventory.model.Grocery;
import com.murphy1.inventory.repositories.GroceryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class GroceryServiceImplTest {

    @Mock
    GroceryRepository groceryRepository;

    GroceryServiceImpl groceryServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        groceryServiceImpl = new GroceryServiceImpl(groceryRepository);
    }

    @Test
    void getAllGroceries() {
        // given
        List<Grocery> groceries = new ArrayList<>();
        groceries.add(new Grocery());

        // when
        when(groceryServiceImpl.getAllGroceries()).thenReturn(groceries);
        List<Grocery> callingList = groceryServiceImpl.getAllGroceries();

        // then
        assertEquals(1, callingList.size());
        verify(groceryRepository, times(1)).findAll();
    }

    @Test
    void save() throws Exception{
        Grocery grocery = new Grocery();
        grocery.setId(1L);

        when(groceryRepository.save(any())).thenReturn(grocery);
        groceryRepository.save(any());

        verify(groceryRepository, times(1)).save(any());
    }

    @Test
    void findById() throws Exception{
        Optional<Grocery> groceryOptional = groceryRepository.findById(anyLong());

        when(groceryRepository.findById(anyLong())).thenReturn(groceryOptional);

        assertNotNull(groceryOptional);
        verify(groceryRepository, times(1)).findById(anyLong());
    }
}