package com.twu.biblioteca;

public class ListOfIssuedMoviesController extends ListOfItemsController {
    public ListOfIssuedMoviesController(InputOutputManger inputOutputManger, LibraryManager libraryManager) {
        super(new ListOfItemsView(inputOutputManger, "List of issued movies."), libraryManager);
    }

    public boolean execute() {
        view.setItems(libraryManager.getListOfIssuedMovies());
        return super.execute();
    }

    @Override
    public String getTitle() {
        return "List of issued movies.";
    }

    @Override
    public boolean isHidden() {
        return !(SessionManager.getSession().getLoggedInUser() instanceof Librarian);
    }
}
