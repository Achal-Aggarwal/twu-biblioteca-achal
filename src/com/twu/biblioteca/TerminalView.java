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
        output.println("2. \tQuit");
        return input.next();
    }

    public int runApplication() {
        output.println(application.welcomeMessage());

        do {
            String selectedOption = showMenu();

            if (selectedOption.equals("1")) {
                showListOfBooksView();
            } else if (selectedOption.equals("2")) {
                break;
            } else {
                output.println("Select a valid option!");
            }
        }while(true);
        return 0;
    }
}
