package com.twu.biblioteca;

public class ListOfBooksController extends Controller {
    private ListOfBooksView view;
    public ListOfBooksController(Library library, InputOutputManger inputOutputManger) {
        super(library);
        view = new ListOfBooksView(inputOutputManger);
    }

    public boolean execute() {
        view.setAvailableBooks(library.getListOfAvailableBooks());

        view.render();

        return true;
    }

    public String getTitle() {
        return "List of books available.";
    }
}
