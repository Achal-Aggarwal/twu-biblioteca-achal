package com.twu.biblioteca;

import java.util.List;

public class LibraryManager {
    private BookLibrary bookLibrary;
    private MovieLibrary movieLibrary;
    private SessionManager session;

    public LibraryManager(BookLibrary bookLibrary, MovieLibrary movieLibrary) {
        this.bookLibrary = bookLibrary;
        this.movieLibrary = movieLibrary;
        session = SessionManager.getSession();
    }

    private boolean checkoutItem(String itemTitle, Library library){
        if(session.getLoggedInUser() == null){
            return false;
        }

        if(library.isItemIssued(itemTitle)){
            return false;
        }

        Item item = library.removeFromAvailableItem(itemTitle);
        if(item == null){
            return false;
        }

        item.setBorrower(session.getLoggedInUser());
        library.addIntoIssuedItem(item);

        return true;
    }

    private List<String> getListOfIssuedItemsFrom(Library library) {
        return library.getListOfIssuedItems();
    }

    private boolean checkinItem(String itemTitle, Library library){
        if(session.getLoggedInUser() == null){
            return false;
        }

        Item item = library.removeFromIssuedItem(itemTitle);
        if(item == null){
            return false;
        }

        if(item.getBorrower() != session.getLoggedInUser()){
            return false;
        }

        library.addIntoAvailableItem(item);

        return true;
    }

    public boolean checkoutMovie(String movieName) {
        return checkoutItem(movieName, movieLibrary);
    }

    public boolean checkoutBook(String bookTitle) {
        return checkoutItem(bookTitle, bookLibrary);
    }

    public boolean checkinBook(String bookTitle) {
        return checkinItem(bookTitle, bookLibrary);
    }

    public boolean checkinMovie(String movieName) {
        return checkinItem(movieName, movieLibrary);
    }

    public List<String> getListOfAvailableBooks() {
        return bookLibrary.getListOfAvailableItems();
    }

    public List<String> getListOfAvailableMovies() {
        return movieLibrary.getListOfAvailableItems();
    }

    public List<String> getListOfIssuedBooks() {
        return getListOfIssuedItemsFrom(bookLibrary);
    }

    public List<String> getListOfIssuedMovies() {
        return getListOfIssuedItemsFrom(movieLibrary);
    }

    public boolean isMovieCheckedOut(String movieName) {
        return movieLibrary.isItemIssued(movieName);
    }

    public boolean isBookCheckedOut(String bookTitle) {
        return bookLibrary.isItemIssued(bookTitle);
    }
}
