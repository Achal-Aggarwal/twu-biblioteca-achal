package com.twu.biblioteca;

public class ListOfIssuedBooksController extends ListOfItemsController {
    public ListOfIssuedBooksController(InputOutputManger inputOutputManger, LibraryManager libraryManager) {
        super(new ListOfItemsView(inputOutputManger, "List of issued books."), libraryManager);
    }

    public boolean execute() {
        view.setItems(libraryManager.getListOfIssuedBooks());
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
