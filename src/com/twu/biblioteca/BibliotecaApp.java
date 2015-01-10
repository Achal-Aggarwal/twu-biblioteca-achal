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

    public static void main(String[] args) {
        BibliotecaApp application = new BibliotecaApp();
        Book letusc = new Book("Let Us C");
        Book galvin = new Book("Galvin");
        Book internetSec = new Book("Internet Security");
        Book fivePoint = new Book("Five Point Someone");
        application.addBook(letusc);
        application.addBook(galvin);
        application.addBook(internetSec);
        application.addBook(fivePoint);
        TerminalView terminal = new TerminalView(application);
        terminal.runApplication(System.out);
    }
}
