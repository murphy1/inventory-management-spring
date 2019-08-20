package com.murphy1.inventory.bootstrap;

import com.murphy1.inventory.model.Electronic;
import com.murphy1.inventory.model.Game;
import com.murphy1.inventory.model.Grocery;
import com.murphy1.inventory.model.User;
import com.murphy1.inventory.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class DataLoaderTest {

    @Mock
    GameRepository gameRepository;
    @Mock
    ElectronicRepository electronicRepository;
    @Mock
    GroceryRepository groceryRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    WalletRepository walletRepository;

    DataLoader dataLoader;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        dataLoader = new DataLoader(gameRepository, electronicRepository, groceryRepository, userRepository,
                walletRepository);
    }

    @Test
    void onApplicationEvent() {
        // given
        Game game = new Game();
        Electronic electronic = new Electronic();
        Grocery grocery = new Grocery();
        User user = new User();

        // when
        when(gameRepository.save(game)).thenReturn(game);
        when(electronicRepository.save(electronic)).thenReturn(electronic);
        when(groceryRepository.save(grocery)).thenReturn(grocery);
        when(userRepository.save(user)).thenReturn(user);

        gameRepository.save(game);
        electronicRepository.save(electronic);
        groceryRepository.save(grocery);
        userRepository.save(user);

        // then
        verify(gameRepository, times(1)).save(game);
        verify(electronicRepository, times(1)).save(electronic);
        verify(groceryRepository, times(1)).save(grocery);
        verify(userRepository, times(1)).save(user);
    }
}