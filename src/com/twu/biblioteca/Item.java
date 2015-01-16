package com.twu.biblioteca;

abstract public class Item {
    private User borrower = null;
    abstract public String getTitle();

    public void setBorrower(User borrower){
        this.borrower = borrower;
    }

    public User getBorrower(){
        return borrower;
    }
}
