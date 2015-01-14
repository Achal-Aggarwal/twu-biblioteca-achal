package com.twu.biblioteca;

import java.util.HashMap;
import java.util.List;

public class LibraryManager {
    private HashMap issuedBooks = new HashMap();
    private Library library;

    public LibraryManager(Library library) {
        this.library = library;
    }

    public boolean checkoutBook(String bookTitle) {
        Book book = (Book) issuedBooks.get(bookTitle);
        if(book != null){
            return false;
        }

        book = library.removeBook(bookTitle);
        if(book == null){
            return false;
        }

        issuedBooks.put(bookTitle, book);

        return true;
    }

    public boolean isBookCheckedOut(String bookTitle) {
        return issuedBooks.containsKey(bookTitle);
    }

    public boolean checkinBook(String bookTitle) {
        Book book = (Book) issuedBooks.get(bookTitle);
        if(book == null){
            return false;
        }

        library.addBook(book);
        issuedBooks.remove(bookTitle);

        return true;
    }

    public List<String> getListOfAvailableBooks() {
        return library.getListOfAvailableBooks();
    }
}
