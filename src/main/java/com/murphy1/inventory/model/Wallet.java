package com.murphy1.inventory.model;

import javax.persistence.*;

@Entity
public class Wallet extends BaseEntity{

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
        user.setWallet(this);
    }
}
