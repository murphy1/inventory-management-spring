package com.murphy1.inventory.controllers;

import com.murphy1.inventory.model.SearchObject;
import com.murphy1.inventory.searching.SearchQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class SearchController {
    // for searching
    private SearchQuery searchQuery;
    private String objectForResults;
    private List returnedObject;

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

        switch (objectForResults) {
            case "user":
                log.info("sending new user attribute to the user result page for display...");
                returnString = "searchresults/userresult";
                break;
            case "electronic":
                log.info("sending new Electronic attribute to the Electronic result page for display...");
                returnString = "searchresults/electronicresult";
                break;
            case "furniture":
                log.info("sending new Furniture attribute to the Furniture result page for display...");
                returnString = "searchresults/furnitureresult";
                break;
            case "game":
                log.info("sending new Game attribute to the Game result page for display...");
                returnString = "searchresults/gameresult";
                break;
            case "grocery":
                log.info("sending new Grocery attribute to the Grocery result page for display...");
                returnString = "searchresults/groceryresult";
                break;
        }

        return returnString;
    }

    @GetMapping("/search/newsearch")
    public String returnSearch(@Valid @ModelAttribute("searchObject") SearchObject searchObject, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError ->
                    log.debug(objectError.toString())
            );
            return "forms/searchform";
        }

        // gets the object -> User, Grocery ...
        String returnedString = searchQuery.objectSearchType(searchObject.getSearchObjectForSearch(), searchObject.getQuery(), searchObject.getSearchType());

        // splits the string for the delgation method and return the required object
        String[] stringForDelegation = returnedString.split(" ");
        String attribute = stringForDelegation[stringForDelegation.length - 1];

        // array has length of 3 when there is one word in the query
        if (stringForDelegation.length == 3){
            returnedObject = searchQuery.searchDelegater(stringForDelegation[0], stringForDelegation[1], attribute);
        }else{
            String query = "";
            int count = 1;
            while (count < (stringForDelegation.length - 1)){
                query +=stringForDelegation[count]+" ";
                count++;
            }
            returnedObject = searchQuery.searchDelegater(stringForDelegation[0], query, attribute);
        }

        // object to be passed to the results page.
        String[] stringArray = returnedObject.stream().findFirst().get().getClass().getName().split("model.");
        objectForResults = stringArray[1].toLowerCase();

        return "redirect:/searchresult";
    }

}
