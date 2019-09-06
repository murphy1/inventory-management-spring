package com.murphy1.inventory.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User extends BaseEntity{

    @NotNull
    @Size(min = 2, max = 50)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 50)
    private String lastName;

    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @OneToOne(cascade = CascadeType.ALL)
    private Wallet wallet;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
        wallet.setUser(this);
    }
}
