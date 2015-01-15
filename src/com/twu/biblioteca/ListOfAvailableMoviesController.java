package com.twu.biblioteca;

public class ListOfAvailableMoviesController extends ListOfItemsController {
    public ListOfAvailableMoviesController(InputOutputManger inputOutputManger, LibraryManager libraryManager) {
        super(new ListOfItemsView(inputOutputManger, "List of available movies."), libraryManager);
    }

    public boolean execute() {
        view.setItems(libraryManager.getListOfAvailableMovies());
        return super.execute();
    }

    public String getTitle() {
        return "List of available movies.";
    }
}
