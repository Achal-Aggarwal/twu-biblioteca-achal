package com.twu.biblioteca;

public class ListOfIssuedBooksController extends ListOfItemsController {
    public ListOfIssuedBooksController(InputOutputManger inputOutputManger, Library library) {
        super(new ListOfItemsView(inputOutputManger, "List of issued books."), library);
    }

    public boolean execute() {
        view.setItems(library.getListOfIssuedBooks());
        return super.execute();
    }

    @Override
    public String getTitle() {
        return "List of issued books.";
    }

    @Override
    public boolean isHidden() {
        return !(SessionManager.getSession().getLoggedInUser().isLibrarian());
    }
}
