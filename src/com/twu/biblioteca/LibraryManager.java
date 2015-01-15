package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LibraryManager {
    private HashMap<String, Item> issuedBooks = new HashMap<String, Item>();
    private BookLibrary bookLibrary;
    private MovieLibrary movieLibrary;
    private HashMap<String, Item> issuedMovies = new HashMap<String, Item>();
    private HashMap<String, User> users = new HashMap<String, User>();
    private User currentUser = null;

    public LibraryManager(BookLibrary bookLibrary, MovieLibrary movieLibrary) {
        this.bookLibrary = bookLibrary;
        this.movieLibrary = movieLibrary;
    }

    private boolean checkoutItem(String itemTitle, Library library, HashMap<String, Item> issuedItems){
        if(currentUser == null){
            return false;
        }

        Item item = issuedItems.get(itemTitle);
        if(item != null){
            return false;
        }

        item = library.removeItem(itemTitle);
        if(item == null){
            return false;
        }

        item.setBorrower(currentUser);
        issuedItems.put(itemTitle, item);

        return true;
    }

    private List<String> getListOfIssuedItemsFrom(HashMap<String, Item> issuedItemsMap) {
        List<String> issuedItems = new ArrayList<String>();
        for (Item item : issuedItemsMap.values()) {
            issuedItems.add(item.getFormattedString() + " issued by " + item.getBorrower().contactInformation());
        }

        return issuedItems;
    }

    private boolean checkinItem(String itemTitle, Library library, HashMap<String, Item> issuedItems){
        if(currentUser == null){
            return false;
        }

        Item item = issuedItems.get(itemTitle);
        if(item == null){
            return false;
        }

        if(item.getBorrower() != currentUser){
            return false;
        }

        library.addItem(item);
        issuedItems.remove(itemTitle);

        return true;
    }

    public boolean checkoutMovie(String movieName) {
        return checkoutItem(movieName, movieLibrary, issuedMovies);
    }

    public boolean checkoutBook(String bookTitle) {
        return checkoutItem(bookTitle, bookLibrary, issuedBooks);
    }

    public boolean checkinBook(String bookTitle) {
        return checkinItem(bookTitle, bookLibrary, issuedBooks);
    }

    public boolean checkinMovie(String movieName) {
        return checkinItem(movieName, movieLibrary, issuedMovies);
    }

    public List<String> getListOfAvailableBooks() {
        return bookLibrary.getListOfAvailableItems();
    }

    public List<String> getListOfAvailableMovies() {
        return movieLibrary.getListOfAvailableItems();
    }

    public List<String> getListOfIssuedBooks() {
        return getListOfIssuedItemsFrom(issuedBooks);
    }

    public List<String> getListOfIssuedMovies() {
        return getListOfIssuedItemsFrom(issuedMovies);
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
        User user = users.get(libraryNumber);

        if (user == null){
            return false;
        }

        return user.getPassword().equals(password);
    }

    public boolean setCurrentUser(String userLibraryNumber) {
        if(!isUserPresent(userLibraryNumber)){
            return false;
        }

        currentUser = users.get(userLibraryNumber);
        return true;
    }

    public void unSetCurrentUser() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
