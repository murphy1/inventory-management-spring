package com.murphy1.inventory.services.impl;

import com.murphy1.inventory.model.Wallet;
import com.murphy1.inventory.repositories.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class WalletServiceImplTest {

    @Mock
    WalletRepository walletRepository;

    WalletServiceImpl walletServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        walletServiceImpl = new WalletServiceImpl(walletRepository);
    }

    @Test
    void getAllWallets() {
        // given
        List<Wallet> wallets = new ArrayList<>();
        wallets.add(new Wallet());

        // when
        when(walletServiceImpl.getAllWallets()).thenReturn(wallets);
        List<Wallet> callingList = walletServiceImpl.getAllWallets();

        // then
        assertEquals(1, callingList.size());
        verify(walletRepository, times(1)).findAll();
    }
}