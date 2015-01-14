package com.twu.biblioteca;

import java.util.List;

public class ListOfBooksViewController extends ViewController {
    public ListOfBooksViewController(Library library, InputOutputManger inputOutputManger) {
        super(library, inputOutputManger);
    }

    public boolean execute() {
        List<String> books = library.getListOfAvailableBooks();
        io.printLine(getTitle());
        for (int i = 0; i < books.size(); i++) {
            io.printLine((i+1)+ ". \t" + books.get(i));
        }

        return true;
    }

    public String getTitle() {
        return "List of books available.";
    }
}
