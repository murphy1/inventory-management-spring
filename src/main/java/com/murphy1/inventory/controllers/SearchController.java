package com.murphy1.inventory.controllers;

import com.murphy1.inventory.model.SearchObject;
import com.murphy1.inventory.searching.SearchQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@Controller
public class SearchController {
    // for searching
    private SearchQuery searchQuery;
    private String objectForResults;
    private Object returnedObject;

    public SearchController(SearchQuery searchQuery) {
        this.searchQuery = searchQuery;

        this.objectForResults = null;
        this.returnedObject = null;
    }

    @GetMapping("/search")
    private String newSearch(Model model){
        model.addAttribute("searchObject", new SearchObject());

        return "forms/searchform";
    }

    @GetMapping("/searchresult")
    private String searchResults(Model model){

        model.addAttribute(this.objectForResults, this.returnedObject);

        String returnString = "";

        if (objectForResults.equals("user")){
            log.info("sending new user attribute to the user result page for display...");
            returnString = "searchresults/userresult";
        }
        else if (objectForResults.equals("electronic")){
            log.info("sending new Electronic attribute to the Electronic result page for display...");
            returnString = "searchresults/electronicresult";
        }
        else if (objectForResults.equals("furniture")){
            log.info("sending new Furniture attribute to the Furniture result page for display...");
            returnString = "searchresults/furnitureresult";
        }
        else if (objectForResults.equals("game")){
            log.info("sending new Game attribute to the Game result page for display...");
            returnString = "searchresults/gameresult";
        }
        else if (objectForResults.equals("grocery")){
            log.info("sending new Grocery attribute to the Grocery result page for display...");
            returnString = "searchresults/groceryresult";
        }

        return returnString;
    }

    @GetMapping("/search/newsearch")
    public String returnSearch(@ModelAttribute SearchObject searchObject, Model model){

        // gets the object -> User, Grocery ...
        String returnedString = searchQuery.objectSearchType(searchObject.getSearchObjectForSearch(), searchObject.getQuery(), searchObject.getSearchType());

        // splits the string for the delgation method and return the required object
        String[] stringForDelegation = returnedString.split(" ");
        returnedObject = searchQuery.searchDelegater(stringForDelegation[0], stringForDelegation[1], stringForDelegation[2]);

        // object to be passed to the results page.
        String[] stringArray = returnedObject.getClass().getName().split("model.");
        objectForResults = stringArray[1].toLowerCase();

        return "redirect:/searchresult";
    }

}
