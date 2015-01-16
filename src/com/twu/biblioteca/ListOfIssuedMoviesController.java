package com.twu.biblioteca;

public class ListOfIssuedMoviesController extends ListOfItemsController {
    protected ListOfIssuedMoviesView view;
    public ListOfIssuedMoviesController(InputOutputManger inputOutputManger, Library library) {
        super(library);
        view = new ListOfIssuedMoviesView(inputOutputManger, "List of issued movies.");
    }

    public boolean execute() {
        view.setItems(library.getListOfIssuedMovies());
        view.render();
        return true;
    }

    @Override
    public String getTitle() {
        return "List of issued movies.";
    }

    @Override
    public boolean isHidden() {
        return !(SessionManager.getSession().getLoggedInUser().isLibrarian());
    }
}
