package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {

    private List<String> books = new ArrayList<String>();

    public String welcomeMessage(){
        return "Welcome and thank you for taking time to visit Biblioteca.";
    }

    public void addBook(String bookTitle) {
        books.add(bookTitle);
    }

    public boolean isPresent(String bookTitle) {
        return books.contains(bookTitle);
    }

    public List<String> getListOfBooks() {
        return books;
    }

    public static void main(String[] args) {
        BibliotecaApp application = new BibliotecaApp();
        String letusc = "Let Us C";
        String galvin = "Galvin";
        String internetSec = "Internet Security";
        String fivePoint = "Five Point Someone";
        application.addBook(letusc);
        application.addBook(galvin);
        application.addBook(internetSec);
        application.addBook(fivePoint);
        TerminalView terminal = new TerminalView(application);
        terminal.runApplication(System.out);
    }
}
