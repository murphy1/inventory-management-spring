package com.murphy1.inventory.model;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SearchObject {

    @Lob
    private String query;

    @NotNull
    @Size(min = 1, max = 20)
    private String searchObjectForSearch;

    @NotNull
    @Size(min = 1, max = 20)
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
