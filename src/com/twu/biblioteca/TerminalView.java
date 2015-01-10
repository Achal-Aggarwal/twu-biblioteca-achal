package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.List;

/**
 * Created by achalaggarwal on 1/10/15.
 */
public class TerminalView {
    private BibliotecaApp application;

    public TerminalView(BibliotecaApp app){

        this.application = app;
    }

    public int runApplication(PrintStream output){
        output.println(application.welcomeMessage());

        List<Book> books = application.getListOfBooks();
        output.println("Sr. \tBook\tAuthor\tPublicationDate");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            output.println((i+1)+ ". \t" + book);
        }

        return 0;
    }
}
