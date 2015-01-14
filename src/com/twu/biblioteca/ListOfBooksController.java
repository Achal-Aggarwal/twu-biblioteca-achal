package com.twu.biblioteca;

public class ListOfBooksController extends Controller {
    private ListOfBooksView view;
    public ListOfBooksController(LibraryManager libraryManager, InputOutputManger inputOutputManger) {
        super(libraryManager);
        view = new ListOfBooksView(inputOutputManger);
    }

    public boolean execute() {
        view.setAvailableBooks(libraryManager.getListOfAvailableBooks());

        view.render();

        return true;
    }

    public String getTitle() {
        return "List of books available.";
    }
}
