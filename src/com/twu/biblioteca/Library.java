package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Library {

    private HashMap books = new HashMap();

    public void addBook(Book book) {
        books.put(book.getTitle(), book);
    }

    public boolean isPresent(Book book) {
        return books.containsKey(book.getTitle());
    }

    public List<String> getListOfBooks() {

        List<String> availableBooks = new ArrayList<String>();
        for (Object book : books.values()) {
            if(!((Book)book).isCheckedOut()){
                availableBooks.add(((Book) book).getFormattedString());
            }
        }

        return availableBooks;
    }

    public boolean checkout(String bookTitle) {
        Book book = (Book) books.get(bookTitle);
        return book != null && book.checkOut();
    }

    public boolean isBookCheckedOut(String bookTitle) {
        Book book = (Book) books.get(bookTitle);
        return book == null || book.isCheckedOut();
    }

    public boolean checkin(String bookTitle) {
        Book book = (Book) books.get(bookTitle);
        return book != null && book.checkin();
    }
}
