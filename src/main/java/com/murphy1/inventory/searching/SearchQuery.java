package com.murphy1.inventory.searching;

import com.murphy1.inventory.model.*;
import com.murphy1.inventory.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class SearchQuery {

    private ElectronicService electronicService;
    private FurnitureService furnitureService;
    private GameService gameService;
    private GroceryService groceryService;
    private UserService userService;

    private Set<String> idAttributes = new HashSet<>();
    private Set<String> nameAttributes = new HashSet<>();
    private Set<String> descriptionAttributes = new HashSet<>();

    public SearchQuery(ElectronicService electronicService, FurnitureService furnitureService, GameService gameService, GroceryService groceryService, UserService userService) {
        this.electronicService = electronicService;
        this.furnitureService = furnitureService;
        this.gameService = gameService;
        this.groceryService = groceryService;
        this.userService = userService;

        idAttributes.add("id");
        idAttributes.add("identifier");
        idAttributes.add("identity");

        nameAttributes.add("name");
        nameAttributes.add("names");

        descriptionAttributes.add("description");
    }

    public String objectSearchType(String objectToCheck, String query, String type){
        Set<String> electronics = new HashSet<>();
        Set<String> furniture = new HashSet<>();
        Set<String> games = new HashSet<>();
        Set<String> groceries = new HashSet<>();
        Set<String> users = new HashSet<>();

        electronics.add("electronics");
        electronics.add("electronic");

        furniture.add("Furniture");
        furniture.add("furniture");

        games.add("Games");
        games.add("games");
        games.add("Game");
        games.add("game");

        groceries.add("Groceries");
        groceries.add("groceries");
        groceries.add("Grocery");
        groceries.add("grocery");
        groceries.add("food");

        users.add("Users");
        users.add("users");
        users.add("User");
        users.add("user");
        users.add("person");
        users.add("Person");

        String returnString = "";

        if (electronics.contains(objectToCheck)){
            log.info("Electronic found creating new return String");
            returnString = "electronic"+" "+query+" "+type;
        } else if (furniture.contains(objectToCheck)){
            log.info("Furniture found creating new return String");
            returnString = "furniture"+" "+query+" "+type;
        } else if (games.contains(objectToCheck)){
            log.info("Game found creating new return String");
            returnString = "game"+" "+query+" "+type;
        } else if (groceries.contains(objectToCheck)){
            log.info("Grocery found creating new return String");
            returnString = "grocery"+" "+query+" "+type;
        } else if (users.contains(objectToCheck)){
            log.info("user found creating new return String");
            returnString = "user"+" "+query+" "+type;
        } else{
            log.error("Object not found! -> ObjectSearchType");
            throw new RuntimeException("Object does not exist!");
        }

        return returnString;
    }

    // Decides which Service to use to query the DB
    public Object searchDelegater(String object, String query, String type){

        if (object.equals("electronic")){
            log.info("Search delegater found electronic. Sending to electronic search.");
            Electronic electronic = electronicSearch(query, type);
            return electronic;
        }
        if (object.equals("furniture")){
            log.info("Search delegater found furniture. Sending to furniture search.");
            Furniture furniture = furnitureSearch(query, type);
            return furniture;
        }
        if (object.equals("game")){
            log.info("Search delegater found game. Sending to game search.");
            Game game = gameSearch(query, type);
            return game;
        }
        if (object.equals("grocery")){
            log.info("Search delegater found grocery. Sending to grocery search.");
            Grocery grocery = grocerySearch(query, type);
            return grocery;
        }
        if (object.equals("user")){
            log.info("Search delegater found user. Sending to person search.");
            User user = personSearch(query, type);
            return user;
        }
        return null;
    }

    private User personSearch(String query, String typeOfSearch){
        User returnedUser = new User();

        if (idAttributes.contains(typeOfSearch.toLowerCase())){
            //todo catch NumberFormatException error if a String is entered when searching for an ID

            log.info("Person search found the type id. Searching...");
            returnedUser = userService.findUserById(Long.valueOf(query));
        }
        else if (nameAttributes.contains(typeOfSearch.toLowerCase())){
            List<User> users = userService.getAllUsers();
            for (User user : users){
                if (user.getFirstName().toLowerCase().contains(query.toLowerCase()) ||
                        user.getLastName().toLowerCase().contains(query.toLowerCase())){
                    return user;
                }
            }
        }
        else {
            log.error("That attribute does not exist!");
            throw new RuntimeException("Attribute does not exist! "+typeOfSearch);
        }

        return returnedUser;
    }

    private Electronic electronicSearch(String query, String typeOfSearch){
        Electronic returnedElectronic = new Electronic();

        if (idAttributes.contains(typeOfSearch.toLowerCase())){
            log.info("Electronic search found the type id. Searching...");
            returnedElectronic = electronicService.findById(Long.valueOf(query));
        }
        else if (nameAttributes.contains(typeOfSearch.toLowerCase())){
            List<Electronic> electronics = electronicService.getAllElectronics();
            for (Electronic electronic : electronics){
                if (electronic.getName().toLowerCase().contains(query.toLowerCase())){
                    return electronic;
                }
            }
        }
        else if (descriptionAttributes.contains(typeOfSearch.toLowerCase())){
            List<Electronic> electronics = electronicService.getAllElectronics();
            for (Electronic electronic : electronics){
                if (electronic.getDescription().toLowerCase().contains(query.toLowerCase())){
                    return electronic;
                }
            }
        }
        else {
            log.error("That attribute does not exist!");
            throw new RuntimeException("Attribute does not exist! "+typeOfSearch);
        }

        return returnedElectronic;
    }

    private Furniture furnitureSearch(String query, String typeOfSearch){
        Furniture returnedFurniture = new Furniture();

        if (idAttributes.contains(typeOfSearch.toLowerCase())){
            log.info("Furniture search found the type id. Searching...");
            returnedFurniture = furnitureService.findById(Long.valueOf(query));
        }
        else if (nameAttributes.contains(typeOfSearch.toLowerCase())){
            List<Furniture> furniture = furnitureService.getAllFurniture();
            for (Furniture furniture1 : furniture){
                if (furniture1.getName().toLowerCase().contains(query.toLowerCase())){
                    return furniture1;
                }
            }
        }
        else if (descriptionAttributes.contains(typeOfSearch.toLowerCase())){
            List<Furniture> furniture = furnitureService.getAllFurniture();
            for (Furniture furniture1 : furniture){
                if (furniture1.getDescription().toLowerCase().contains(query.toLowerCase())){
                    return furniture1;
                }
            }
        }
        else {
            log.error("That attribute does not exist!");
            throw new RuntimeException("Attribute does not exist! "+typeOfSearch);
        }

        return returnedFurniture;
    }

    private Game gameSearch(String query, String typeOfSearch){
        Game returnedGame = new Game();

        if (idAttributes.contains(typeOfSearch.toLowerCase())){
            log.info("Game search found the type id. Searching...");
            returnedGame = gameService.findById(Long.valueOf(query));
        }
        else if (nameAttributes.contains(typeOfSearch.toLowerCase())){
            List<Game> games = gameService.getAllGames();
            for (Game game : games){
                if (game.getName().toLowerCase().contains(query.toLowerCase())){
                    return game;
                }
            }
        }
        else if (descriptionAttributes.contains(typeOfSearch.toLowerCase())){
            List<Game> games = gameService.getAllGames();
            for (Game game : games){
                if (game.getDescription().toLowerCase().contains(query.toLowerCase())){
                    return game;
                }
            }
        }
        else {
            log.error("That attribute does not exist!");
            throw new RuntimeException("Attribute does not exist! "+typeOfSearch);
        }

        return returnedGame;
    }

    private Grocery grocerySearch(String query, String typeOfSearch){
        Grocery returnedGrocery = new Grocery();

        if (idAttributes.contains(typeOfSearch.toLowerCase())){
            log.info("Grocery search found the type id. Searching...");
            returnedGrocery = groceryService.findById(Long.valueOf(query));
        }
        else if (nameAttributes.contains(typeOfSearch.toLowerCase())){
            List<Grocery> groceries = groceryService.getAllGroceries();
            for (Grocery grocery : groceries){
                if (grocery.getName().toLowerCase().contains(query.toLowerCase())){
                    return grocery;
                }
            }
        }
        else if (descriptionAttributes.contains(typeOfSearch.toLowerCase())){
            List<Grocery> groceries = groceryService.getAllGroceries();
            for (Grocery grocery : groceries){
                if (grocery.getDescription().toLowerCase().contains(query.toLowerCase())){
                    return grocery;
                }
            }
        }
        else {
            log.error("That attribute does not exist!");
            throw new RuntimeException("Attribute does not exist! "+typeOfSearch);
        }

        return returnedGrocery;
    }

}
