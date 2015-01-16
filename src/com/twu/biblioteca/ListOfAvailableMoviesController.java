package com.twu.biblioteca;

public class ListOfAvailableMoviesController extends ListOfItemsController {
    public ListOfAvailableMoviesController(InputOutputManger inputOutputManger, Library library) {
        super(new ListOfItemsView(inputOutputManger, "List of available movies."), library);
    }

    public boolean execute() {
        view.setItems(library.getListOfAvailableMovies());
        return super.execute();
    }

    public String getTitle() {
        return "List of available movies.";
    }
}
