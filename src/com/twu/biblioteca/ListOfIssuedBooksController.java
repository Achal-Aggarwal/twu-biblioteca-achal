package com.twu.biblioteca;

/**
 * Created by achalaggarwal on 1/15/15.
 */
public class ListOfIssuedBooksController extends ListOfItemsController {
    public ListOfIssuedBooksController(LibraryManager libraryManager, InputOutputManger inputOutputManger) {
        super(libraryManager, new ListOfItemsView(inputOutputManger, "List of issued books."));
    }

    public boolean execute() {
        view.setItems(libraryManager.getListOfIssuedBooks());
        return super.execute();
    }

    @Override
    public String getTitle() {
        return "List of issued books.";
    }
}
