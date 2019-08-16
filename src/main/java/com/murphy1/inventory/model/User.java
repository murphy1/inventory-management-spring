package com.murphy1.inventory.model;

import javax.persistence.*;

@Entity
public class User extends BaseEntity{

    private String firstName;
    private String lastName;
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
