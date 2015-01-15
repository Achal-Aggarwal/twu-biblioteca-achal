package com.twu.biblioteca;

public class ListOfIssuedMoviesController extends ListOfItemsController {
    public ListOfIssuedMoviesController(LibraryManager libraryManager, InputOutputManger inputOutputManger) {
        super(libraryManager, new ListOfItemsView(inputOutputManger, "List of issued movies."));
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
    public boolean isHidden(){
        return !(SessionManager.getSession().getLoggedInUser() instanceof Librarian);
    }
}
