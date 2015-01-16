package com.twu.biblioteca;

public class ListOfIssuedBooksController extends ListOfItemsController {
    protected ListOfIssuedBooksView view;
    public ListOfIssuedBooksController(InputOutputManger inputOutputManger, Library library) {
        super(library);
        view = new ListOfIssuedBooksView(inputOutputManger, "List of issued books.");
    }

    public boolean execute() {
        view.setItems(library.getListOfIssuedBooks());
        view.render();
        return true;
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
