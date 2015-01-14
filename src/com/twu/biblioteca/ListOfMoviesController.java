package com.twu.biblioteca;

public class ListOfMoviesController extends Controller {
    private ListOfMoviesView view;
    public ListOfMoviesController(LibraryManager libraryManager, InputOutputManger inputOutputManger) {
        super(libraryManager);
        view = new ListOfMoviesView(inputOutputManger);
    }

    public boolean execute() {
        view.setAvailableMovies(libraryManager.getListOfAvailableMovies());

        view.render();

        return true;
    }

    public String getTitle() {
        return "List of movies available.";
    }
}
