package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Library {

    private HashMap books = new HashMap();

    public boolean isBookPresent(String bookTitle) {
        return books.containsKey(bookTitle);
    }

    public List<String> getListOfAvailableBooks() {

        List<String> availableBooks = new ArrayList<String>();
        for (Object book : books.values()) {
            //if(!((Book)book).isCheckedOut()){
                availableBooks.add(((Book) book).getFormattedString());
            //}
        }

        return availableBooks;
    }

    public Book addBook(Book book) {
        if(isBookPresent(book.getTitle())){
            return null;
        }

        books.put(book.getTitle(), book);

        return book;
    }

    public Book removeBook(String bookTitle) {
        if(!isBookPresent(bookTitle)){
            return null;
        }

        return (Book) books.remove(bookTitle);
    }
}
