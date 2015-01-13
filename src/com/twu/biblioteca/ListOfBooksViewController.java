package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class ListOfBooksViewController extends ViewController {
    public ListOfBooksViewController(Library library, PrintStream output, Scanner input) {
        super(library, output, input);
    }

    public boolean execute() {
        List<String> books = library.getListOfAvailableBooks();
        output.println(getTitle());
        for (int i = 0; i < books.size(); i++) {
            output.println((i+1)+ ". \t" + books.get(i));
        }

        return true;
    }

    public String getTitle() {
        return "List of books available.";
    }
}
