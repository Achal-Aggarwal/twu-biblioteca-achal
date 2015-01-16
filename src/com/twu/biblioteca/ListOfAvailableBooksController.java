package com.twu.biblioteca;

public class ListOfAvailableBooksController extends ListOfItemsController {
    public ListOfAvailableBooksController(InputOutputManger inputOutputManger, Library library) {
        super(new ListOfItemsView(inputOutputManger, "List of available books."), library);
    }

    public boolean execute() {
        view.setItems(library.getListOfAvailableBooks());
        return super.execute();
    }

    @Override
    public String getTitle() {
        return "List of available books.";
    }
}
