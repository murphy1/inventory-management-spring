package com.murphy1.inventory.bootstrap;

import com.murphy1.inventory.model.*;
import com.murphy1.inventory.repositories.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Profile("default")
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private GameRepository gameRepository;
    private ElectronicRepository electronicRepository;
    private GroceryRepository groceryRepository;
    private UserRepository userRepository;
    private WalletRepository walletRepository;

    public DataLoader(GameRepository gameRepository, ElectronicRepository electronicRepository,
                      GroceryRepository groceryRepository, UserRepository userRepository,
                      WalletRepository walletRepository) {
        this.gameRepository = gameRepository;
        this.electronicRepository = electronicRepository;
        this.groceryRepository = groceryRepository;
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadData();
    }

    private void loadData(){

        Game marioKart = new Game();
        marioKart.setName("Mario Kart");
        marioKart.setDescription("Multiplayer Racing game");
        marioKart.setPlatform("Nintendo Switch");
        marioKart.setPrice(60.0);
        gameRepository.save(marioKart);

        Game falloutThree = new Game();
        falloutThree.setName("Fallout 3");
        falloutThree.setDescription("Post-Apocalyptic action rpg");
        falloutThree.setPlatform("Windows");
        falloutThree.setPrice(25.0);
        gameRepository.save(falloutThree);

        Electronic nespresso = new Electronic();
        nespresso.setName("Espresso Machine");
        nespresso.setBrand("Nespresso");
        nespresso.setDescription("Vertuo Machine");
        nespresso.setPrice(275.0);
        electronicRepository.save(nespresso);

        Electronic iphone = new Electronic();
        iphone.setName("Iphone XS");
        iphone.setBrand("Apple");
        iphone.setDescription("Cell Phone");
        iphone.setPrice(1000.0);
        electronicRepository.save(iphone);

        Grocery chicken = new Grocery();
        chicken.setName("Chicken Fillets");
        chicken.setDescription("Frozen chicken");
        chicken.setExpiration(LocalDate.now().plusDays(12));
        chicken.setPrice(6.75);
        groceryRepository.save(chicken);

        Grocery pasta = new Grocery();
        pasta.setName("Spaghetti");
        pasta.setExpiration(LocalDate.now().plusYears(1).plusDays(57));
        pasta.setDescription("Uncooked Spaghetti pasta");
        pasta.setPrice(2.10);
        groceryRepository.save(pasta);

        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setUsername("jd12345");
        user1.setWallet(new Wallet(300.0));
        userRepository.save(user1);

        User user2 = new User();
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setUsername("doe12");
        user2.setWallet(new Wallet(212.0));
        userRepository.save(user2);

    }
}
