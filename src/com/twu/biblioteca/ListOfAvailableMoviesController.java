package com.twu.biblioteca;

public class ListOfAvailableMoviesController extends ListOfItemsController {
    public ListOfAvailableMoviesController(LibraryManager libraryManager, InputOutputManger inputOutputManger) {
        super(libraryManager, new ListOfItemsView(inputOutputManger, "movie"));
    }

    public boolean execute() {
        view.setItems(libraryManager.getListOfAvailableMovies());
        return super.execute();
    }

    public String getTitle() {
        return "List of movies available.";
    }
}
