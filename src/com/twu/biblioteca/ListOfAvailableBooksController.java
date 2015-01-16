package com.twu.biblioteca;

public class ListOfAvailableBooksController extends ListOfItemsController {
    protected ListOfAvailableBooksView view;
    public ListOfAvailableBooksController(InputOutputManger inputOutputManger, Library library) {
        super(library);
        view = new ListOfAvailableBooksView(inputOutputManger, "List of available books.");
    }

    public boolean execute() {
        view.setItems(library.getListOfAvailableBooks());
        view.render();
        return true;
    }

    @Override
    public String getTitle() {
        return "List of available books.";
    }
}
