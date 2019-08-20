package com.murphy1.inventory.services.impl;

import com.murphy1.inventory.model.Furniture;
import com.murphy1.inventory.repositories.FurnitureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class FurnitureServiceImplTest {

    @Mock
    FurnitureRepository furnitureRepository;

    FurnitureServiceImpl furnitureServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        furnitureServiceImpl = new FurnitureServiceImpl(furnitureRepository);
    }

    @Test
    void getAllFurniture() {
        // given
        List<Furniture> furniture = new ArrayList<>();
        furniture.add(new Furniture());

        // when
        when(furnitureServiceImpl.getAllFurniture()).thenReturn(furniture);
        List<Furniture> listToCall = furnitureServiceImpl.getAllFurniture();

        // then
        assertEquals(1, listToCall.size());
        verify(furnitureRepository, times(1)).findAll();
    }
}