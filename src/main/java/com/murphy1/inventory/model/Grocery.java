package com.murphy1.inventory.model;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Grocery extends BaseEntity{

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    private Double price;

    @Lob
    @NotNull
    @Size(min = 3, max = 255)
    private String description;

    @NotNull
    @Future
    private LocalDate expiration;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDate expiration) {
        this.expiration = expiration;
    }
}
