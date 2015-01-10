package com.twu.biblioteca;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class TerminalView {
    private BibliotecaApp application;
    private PrintStream output;
    private Scanner input;

    public TerminalView(BibliotecaApp app, PrintStream output, InputStream inputStream){
        this.application = app;
        this.output = output;
        this.input = new Scanner(inputStream);
        this.input.useDelimiter("\n");
    }

    private void showListOfBooksView(){
        List<Book> books = application.getListOfBooks();
        output.println("List of books available.");
        output.println("Sr. \tBook\tAuthor\tPublicationDate");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            output.println((i+1)+ ". \t" + book);
        }
    }

    private String showMenu() {
        output.println("--Menu--");
        output.println("1. \tList Books");
        output.println("2. \tCheckout Book");
        output.println("3. \tQuit");
        return input.next();
    }

    public int runApplication() {
        output.println(application.welcomeMessage());

        do {
            String selectedOption = showMenu();

            if (selectedOption.equals("1")) {
                showListOfBooksView();
            } else if (selectedOption.equals("2")) {
                if(application.checkout(input.next())){
                    output.println("Thank you! Enjoy the book");
                }
            } else if (selectedOption.equals("3")) {
                break;
            } else {
                output.println("Select a valid option!");
            }
        }while(true);
        return 0;
    }

    public static void main(String[] args) {
        BibliotecaApp application = new BibliotecaApp();
        Book letusc = new Book("Let Us C", "Yashwant Kanetkar", "2000");
        Book galvin = new Book("Operating System", "Galvin", "2005");
        Book internetSec = new Book("Internet Security", "Ankit Fadia", "1995");
        Book fivePoint = new Book("Five Point Someone", "Chetan Bhagat", "2012");
        application.addBook(letusc);
        application.addBook(galvin);
        application.addBook(internetSec);
        application.addBook(fivePoint);
        TerminalView terminal = new TerminalView(application,System.out, System.in);
        terminal.runApplication();
    }
}
