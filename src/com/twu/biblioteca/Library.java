package com.twu.biblioteca;

import java.util.List;

public class Library {
    private ItemCollection bookCollection;
    private ItemCollection movieCollection;
    private SessionManager session;

    public Library(ItemCollection bookCollection, ItemCollection movieCollection) {
        this.bookCollection = bookCollection;
        this.movieCollection = movieCollection;
        session = SessionManager.getSession();
    }

    private boolean checkoutItem(String itemTitle, ItemCollection itemCollection){
        return itemCollection.issueItem(itemTitle, session.getLoggedInUser());
    }

    private List<String> getListOfIssuedItemsFrom(ItemCollection itemCollection) {
        return itemCollection.getListOfIssuedItems();
    }

    private boolean checkinItem(String itemTitle, ItemCollection itemCollection){
        return itemCollection.returnItem(itemTitle, session.getLoggedInUser());
    }

    public boolean checkoutMovie(String movieName) {
        return checkoutItem(movieName, movieCollection);
    }

    public boolean checkoutBook(String bookTitle) {
        return checkoutItem(bookTitle, bookCollection);
    }

    public boolean checkinBook(String bookTitle) {
        return checkinItem(bookTitle, bookCollection);
    }

    public boolean checkinMovie(String movieName) {
        return checkinItem(movieName, movieCollection);
    }

    public List<String> getListOfAvailableBooks() {
        return bookCollection.getListOfAvailableItems();
    }

    public List<String> getListOfAvailableMovies() {
        return movieCollection.getListOfAvailableItems();
    }

    public List<String> getListOfIssuedBooks() {
        return getListOfIssuedItemsFrom(bookCollection);
    }

    public List<String> getListOfIssuedMovies() {
        return getListOfIssuedItemsFrom(movieCollection);
    }
}
