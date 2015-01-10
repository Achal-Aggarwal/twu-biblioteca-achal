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

        List<String> books = application.getListOfBooks();
        output.println("Sr. \tBook");
        for (int i = 0; i < books.size(); i++) {
            String book = books.get(i);
            output.println((i+1)+ ". \t" + book);
        }

        return 0;
    }
}
