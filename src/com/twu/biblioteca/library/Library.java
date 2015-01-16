package com.twu.biblioteca.library;

import com.twu.biblioteca.session.SessionManager;

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

    public List<Item> getListOfAvailableBooks() {
        return bookCollection.getListOfAvailableItems();
    }

    public List<Item> getListOfAvailableMovies() {
        return movieCollection.getListOfAvailableItems();
    }

    public List<Issue> getListOfIssuedBooks() {
        return bookCollection.getListOfIssuedItems();
    }

    public List<Issue> getListOfIssuedMovies() {
        return movieCollection.getListOfIssuedItems();
    }
}
