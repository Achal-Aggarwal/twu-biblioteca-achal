package com.twu.biblioteca;

import java.util.List;

public class ListOfBooksView extends View {
    private List<String> listOfAvailableBooks;

    public ListOfBooksView(InputOutputManger inputOutputManger) {
        super(inputOutputManger);
    }

    @Override
    public void render() {
        io.printLine("List of books available.");
        for (int i = 0; i < listOfAvailableBooks.size(); i++) {
            io.printLine((i + 1) + ". \t" + listOfAvailableBooks.get(i));
        }
    }

    public void setAvailableBooks(List<String> listOfAvailableBooks) {
        this.listOfAvailableBooks = listOfAvailableBooks;
    }
}
