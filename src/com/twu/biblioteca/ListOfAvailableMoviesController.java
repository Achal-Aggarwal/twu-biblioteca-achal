package com.twu.biblioteca;

public class ListOfAvailableMoviesController extends ListOfItemsController {
    protected ListOfAvailableMoviesView view;
    public ListOfAvailableMoviesController(InputOutputManger inputOutputManger, Library library) {
        super(library);
        view = new ListOfAvailableMoviesView(inputOutputManger, "List of available movies.");
    }

    public boolean execute() {
        view.setItems(library.getListOfAvailableMovies());
        view.render();
        return true;
    }

    public String getTitle() {
        return "List of available movies.";
    }
}
