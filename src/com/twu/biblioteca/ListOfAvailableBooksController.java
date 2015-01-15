package com.twu.biblioteca;

public class ListOfAvailableBooksController extends ListOfItemsController {
    public ListOfAvailableBooksController(InputOutputManger inputOutputManger, LibraryManager libraryManager) {
        super(new ListOfItemsView(inputOutputManger, "List of available books."), libraryManager);
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
