package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class BibliotecaApp {

    private HashMap books = new HashMap();

    public String welcomeMessage(){
        return "Welcome and thank you for taking time to visit Biblioteca.";
    }

    public void addBook(Book book) {
        books.put(book.getTitle(), book);
    }

    public boolean isPresent(Book book) {
        return books.containsKey(book.getTitle());
    }

    public List<Book> getListOfBooks() {
        Collection allBooks = books.values();

        List<Book> availableBooks = new ArrayList<Book>();
        for (Object book : allBooks) {
            if(!((Book)book).isCheckedOut()){
                availableBooks.add((Book) book);
            }
        }

        return availableBooks;
    }

    public boolean checkout(String bookTitle) {
        Book book = (Book) books.get(bookTitle);
        return book.checkOut();
    }

    public boolean isBookCheckedOut(String bookTitle) {
        Book book = (Book) books.get(bookTitle);
        return book.isCheckedOut();
    }
}
