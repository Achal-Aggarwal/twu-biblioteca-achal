package com.twu.biblioteca;

public class ListOfIssuedMoviesController extends ListOfItemsController {
    public ListOfIssuedMoviesController(InputOutputManger inputOutputManger, Library library) {
        super(new ListOfItemsView(inputOutputManger, "List of issued movies."), library);
    }

    public boolean execute() {
        view.setItems(library.getListOfIssuedMovies());
        return super.execute();
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
