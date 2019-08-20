package com.murphy1.inventory.services.impl;

import com.murphy1.inventory.model.Electronic;
import com.murphy1.inventory.repositories.ElectronicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ElectronicServiceImplTest {

    @Mock
    ElectronicRepository electronicRepository;

    ElectronicServiceImpl electronicServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        electronicServiceImpl = new ElectronicServiceImpl(electronicRepository);
    }

    @Test
    void getAllElectronics() {
        // given
        List<Electronic> electronics = new ArrayList<>();
        electronics.add(new Electronic());

        // when
        when(electronicServiceImpl.getAllElectronics()).thenReturn(electronics);
        List<Electronic> callingList = electronicServiceImpl.getAllElectronics();

        // then
        assertEquals(1, callingList.size());
        verify(electronicRepository, times(1)).findAll();
    }
}