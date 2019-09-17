package com.murphy1.inventory.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Wallet extends BaseEntity{

    @NotNull
    private Double balance;

    @OneToOne
    private User user;

    public Wallet(){

    }

    public Wallet(Double balance){
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
