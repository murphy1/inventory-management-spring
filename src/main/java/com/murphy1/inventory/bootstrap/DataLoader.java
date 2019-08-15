package com.murphy1.inventory.bootstrap;

import com.murphy1.inventory.model.Electronic;
import com.murphy1.inventory.model.Game;
import com.murphy1.inventory.model.Grocery;
import com.murphy1.inventory.repositories.ElectronicRepository;
import com.murphy1.inventory.repositories.GameRepository;
import com.murphy1.inventory.repositories.GroceryRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private GameRepository gameRepository;
    private ElectronicRepository electronicRepository;
    private GroceryRepository groceryRepository;

    public DataLoader(GameRepository gameRepository, ElectronicRepository electronicRepository, GroceryRepository groceryRepository) {
        this.gameRepository = gameRepository;
        this.electronicRepository = electronicRepository;
        this.groceryRepository = groceryRepository;
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

    }
}
