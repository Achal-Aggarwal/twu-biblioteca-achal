package com.twu.biblioteca;

import java.util.HashMap;
import java.util.List;

public class LibraryManager {
    private HashMap issuedBooks = new HashMap();
    private BookLibrary bookLibrary;
    private MovieLibrary movieLibrary;
    private HashMap issuedMovies = new HashMap();
    private HashMap users = new HashMap();

    public LibraryManager(BookLibrary bookLibrary, MovieLibrary movieLibrary) {
        this.bookLibrary = bookLibrary;
        this.movieLibrary = movieLibrary;
    }

    private boolean checkoutItem(String itemTitle, Library library){
        HashMap issuedItems;
        if(library instanceof BookLibrary){
            issuedItems = issuedBooks;
        } else {
            issuedItems = issuedMovies;
        }
        Item item = (Item) issuedItems.get(itemTitle);
        if(item != null){
            return false;
        }

        item = library.removeItem(itemTitle);
        if(item == null){
            return false;
        }

        issuedItems.put(itemTitle, item);

        return true;
    }

    private boolean checkinItem(String itemTitle, Library library){
        HashMap issuedItems;
        if(library instanceof BookLibrary){
            issuedItems = issuedBooks;
        } else {
            issuedItems = issuedMovies;
        }

        Item item = (Item) issuedItems.get(itemTitle);
        if(item == null){
            return false;
        }

        library.addItem(item);
        issuedItems.remove(itemTitle);

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

    public boolean isMovieCheckedOut(String movieName) {
        return issuedMovies.containsKey(movieName);
    }

    public boolean isBookCheckedOut(String bookTitle) {
        return issuedBooks.containsKey(bookTitle);
    }

    public void registerUser(User user) {
        users.put(user.getLibraryNumber(), user);
    }

    public boolean isUserPresent(String libraryNumber) {
        return users.containsKey(libraryNumber);
    }

    public boolean isUserValid(String libraryNumber, String password) {
        User user = (User) users.get(libraryNumber);

        if (user == null){
            return false;
        }

        return user.getPassword().equals(password);
    }
}
