package com.murphy1.inventory.model;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class SearchObject {

    @Lob
    private List query;

    @NotNull
    @Size(min = 1, max = 20)
    private String searchObjectForSearch;

    @NotNull
    @Size(min = 1, max = 20)
    private String searchType;

    public List getQuery() {
        return query;
    }

    public void setQuery(List query) {
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
