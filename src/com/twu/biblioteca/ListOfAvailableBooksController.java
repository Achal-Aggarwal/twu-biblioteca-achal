package com.twu.biblioteca;

public class ListOfAvailableBooksController extends ListOfItemsController {
    public ListOfAvailableBooksController(LibraryManager libraryManager, InputOutputManger inputOutputManger) {
        super(libraryManager, new ListOfItemsView(inputOutputManger, "List of available books."));
    }

    public boolean execute() {
        view.setItems(libraryManager.getListOfAvailableBooks());
        return super.execute();
    }

    @Override
    public String getTitle() {
        return "List of available books.";
    }
}
