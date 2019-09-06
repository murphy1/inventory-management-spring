package com.murphy1.inventory.model;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

public class SearchObject {

    @Lob
    private String query;

    @NotNull
    private String searchObjectForSearch;

    @NotNull
    private String searchType;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getSearchObjectForSearch() {
        return searchObjectForSearch;
    }

    public void setSearchObjectForSearch(String searchObjectForSearch) {
        this.searchObjectForSearch = searchObjectForSearch;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
}
