package com.twu.biblioteca;

public class Issue {
    private Item item;
    private User user;

    public Issue(Item item, User user){
        this.item = item;
        this.user = user;
    }

    public Item getIssuedItem() {
        return item;
    }

    public User getIssuer() {
        return user;
    }
}
