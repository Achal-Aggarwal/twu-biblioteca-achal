package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {

    private List<Book> books = new ArrayList<Book>();

    public String welcomeMessage(){
        return "Welcome and thank you for taking time to visit Biblioteca.";
    }

    public void addBook(Book bookTitle) {
        books.add(bookTitle);
    }

    public boolean isPresent(Book bookTitle) {
        return books.contains(bookTitle);
    }

    public List<Book> getListOfBooks() {
        return books;
    }
}
