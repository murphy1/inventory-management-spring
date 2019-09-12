package com.murphy1.inventory.searching;

import com.murphy1.inventory.exceptions.NoSuchElementException;
import com.murphy1.inventory.exceptions.NotFoundException;
import com.murphy1.inventory.model.*;
import com.murphy1.inventory.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

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

    public String objectSearchType(String objectToCheck, List query, String type){
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

        String queryToCheck = "";
        for (Object s : query){
            queryToCheck += s;
        }

        if (electronics.contains(objectToCheck.toLowerCase())){
            log.info("Electronic found creating new return String");
            returnString = "electronic"+" "+ queryToCheck +" "+type;
        } else if (furniture.contains(objectToCheck.toLowerCase())){
            log.info("Furniture found creating new return String");
            returnString = "furniture"+" "+ queryToCheck +" "+type;
        } else if (games.contains(objectToCheck.toLowerCase())){
            log.info("Game found creating new return String");
            returnString = "game"+" "+ queryToCheck +" "+type;
        } else if (groceries.contains(objectToCheck.toLowerCase())){
            log.info("Grocery found creating new return String");
            returnString = "grocery"+" "+ queryToCheck +" "+type;
        } else if (users.contains(objectToCheck.toLowerCase())){
            log.info("user found creating new return String");
            returnString = "user"+" "+ queryToCheck +" "+type;
        } else{
            log.error("Object not found! -> ObjectSearchType");
            throw new NotFoundException("Object does not exist!");
        }

        return returnString;
    }

    // Decides which Service to use to query the DB
    public List searchDelegater(String object, String query, String type){

        if (object.equals("electronic")){
            log.info("Search delegater found electronic. Sending to electronic search.");
            return electronicSearch(query, type);
        }
        if (object.equals("furniture")){
            log.info("Search delegater found furniture. Sending to furniture search.");
            return furnitureSearch(query, type);
        }
        if (object.equals("game")){
            log.info("Search delegater found game. Sending to game search.");
            return gameSearch(query, type);
        }
        if (object.equals("grocery")){
            log.info("Search delegater found grocery. Sending to grocery search.");
            return grocerySearch(query, type);
        }
        if (object.equals("user")){
            log.info("Search delegater found user. Sending to person search.");
            return personSearch(query, type);
        }
        return null;
    }

    private List<User> personSearch(String query, String typeOfSearch){
        List<User> returnList = new ArrayList<>();

        if (idAttributes.contains(typeOfSearch.toLowerCase())){
            log.info("Person search found the type id. Searching...");
            try {
                returnList.add(userService.findUserById(Long.valueOf(query)));
            }catch (NumberFormatException e){
                log.error("ID field has to be a number!");
                throw new NoSuchElementException("ID field has to be a number!");
            }
        }
        else if (nameAttributes.contains(typeOfSearch.toLowerCase())){
            List<User> users = userService.getAllUsers();
            for (User user : users){
                String[] queryCount = query.split(" ");

                if (queryCount.length > 1){
                    if (user.getFirstName().toLowerCase().contains(query.split(" ")[0].toLowerCase()) &&
                            user.getLastName().toLowerCase().contains(query.split(" ")[1].strip().toLowerCase())){
                        returnList.add(user);
                    }
                }else if (queryCount.length == 1){
                    if (user.getFirstName().toLowerCase().contains(query) || user.getLastName().toLowerCase().contains(query)){
                        returnList.add(user);
                    }
                }
            }
        }
        else {
            log.error("That attribute does not exist!");
            throw new NotFoundException("Attribute does not exist! "+typeOfSearch);
        }

        return returnList;
    }

    private List<Electronic> electronicSearch(String query, String typeOfSearch){
        List<Electronic> returnList = new ArrayList<>();

        if (idAttributes.contains(typeOfSearch.toLowerCase())){
            log.info("Electronic search found the type id. Searching...");
            try {
                returnList.add(electronicService.findById(Long.valueOf(query)));
            }catch (NumberFormatException e){
                log.error("ID field has to be a number!");
                throw new NoSuchElementException("ID field has to be a number!");
            }
        }
        else if (nameAttributes.contains(typeOfSearch.toLowerCase())){
            List<Electronic> electronics = electronicService.getAllElectronics();
            for (Electronic electronic : electronics){
                if (electronic.getName().strip().toLowerCase().contains(query.strip().toLowerCase())){
                    returnList.add(electronic);
                }
            }
        }
        else if (descriptionAttributes.contains(typeOfSearch.toLowerCase())){
            List<Electronic> electronics = electronicService.getAllElectronics();
            for (Electronic electronic : electronics){
                if (electronic.getDescription().strip().toLowerCase().contains(query.strip().toLowerCase())){
                    returnList.add(electronic);
                }
            }
        }
        else {
            log.error("That attribute does not exist!");
            throw new NotFoundException("Attribute does not exist! "+typeOfSearch);
        }

        return returnList;
    }

    private List<Furniture> furnitureSearch(String query, String typeOfSearch){
        List<Furniture> returnList = new ArrayList<>();

        if (idAttributes.contains(typeOfSearch.toLowerCase())){
            log.info("Furniture search found the type id. Searching...");
            try {
                returnList.add(furnitureService.findById(Long.valueOf(query)));
            }catch (NumberFormatException e){
                log.error("ID field has to be a number!");
                throw new NoSuchElementException("ID field has to be a number!");
            }
        }
        else if (nameAttributes.contains(typeOfSearch.toLowerCase())){
            List<Furniture> furniture = furnitureService.getAllFurniture();
            for (Furniture furniture1 : furniture){
                if (furniture1.getName().strip().toLowerCase().contains(query.strip().toLowerCase())){
                    returnList.add(furniture1);
                }
            }
        }
        else if (descriptionAttributes.contains(typeOfSearch.toLowerCase())){
            List<Furniture> furniture = furnitureService.getAllFurniture();
            for (Furniture furniture1 : furniture){
                if (furniture1.getDescription().strip().toLowerCase().contains(query.strip().toLowerCase())){
                    returnList.add(furniture1);
                }
            }
        }
        else {
            log.error("That attribute does not exist!");
            throw new NotFoundException("Attribute does not exist! "+typeOfSearch);
        }

        return returnList;
    }

    private List<Game> gameSearch(String query, String typeOfSearch){
        List<Game> returnList = new ArrayList<>();

        if (idAttributes.contains(typeOfSearch.toLowerCase())){
            log.info("Game search found the type id. Searching...");
            try {
                returnList.add(gameService.findById(Long.valueOf(query)));
            }catch (NumberFormatException e){
                log.error("ID field has to be a number!");
                throw new NoSuchElementException("ID field has to be a number!");
            }
        }
        else if (nameAttributes.contains(typeOfSearch.toLowerCase())){
            List<Game> games = gameService.getAllGames();
            for (Game game : games){
                if (game.getName().strip().toLowerCase().contains(query.strip().toLowerCase())
                    || game.getName().strip().toLowerCase().equals(query.strip().toLowerCase()) ){
                    returnList.add(game);
                }
            }
        }
        else if (descriptionAttributes.contains(typeOfSearch.toLowerCase())){
            List<Game> games = gameService.getAllGames();
            for (Game game : games){
                if (game.getDescription().strip().toLowerCase().contains(query.strip().toLowerCase())){
                    returnList.add(game);
                }
            }
        }
        else {
            log.error("That attribute does not exist!");
            throw new NotFoundException("Attribute does not exist! "+typeOfSearch);
        }

        return returnList;
    }

    private List<Grocery> grocerySearch(String query, String typeOfSearch){
        List<Grocery> returnList = new ArrayList<>();

        if (idAttributes.contains(typeOfSearch.toLowerCase())){
            log.info("Grocery search found the type id. Searching...");
            try {
                returnList.add(groceryService.findById(Long.valueOf(query)));
            }catch (NumberFormatException e){
                log.error("ID field has to be a number!");
                throw new NoSuchElementException("ID field has to be a number!");
            }
        }
        else if (nameAttributes.contains(typeOfSearch.toLowerCase())){
            List<Grocery> groceries = groceryService.getAllGroceries();
            for (Grocery grocery : groceries){
                if (grocery.getName().strip().toLowerCase().contains(query.strip().toLowerCase())){
                    returnList.add(grocery);
                }
            }
        }
        else if (descriptionAttributes.contains(typeOfSearch.toLowerCase())){
            List<Grocery> groceries = groceryService.getAllGroceries();
            for (Grocery grocery : groceries){
                if (grocery.getDescription().strip().toLowerCase().contains(query.strip().toLowerCase())){
                    returnList.add(grocery);
                }
            }
        }
        else {
            log.error("That attribute does not exist!");
            throw new NotFoundException("Attribute does not exist! "+typeOfSearch);
        }

        return returnList;
    }

}
