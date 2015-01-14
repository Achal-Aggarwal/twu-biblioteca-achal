package com.twu.biblioteca;

import java.util.List;

public class ListOfMoviesView extends View{
    private List<String> listOfAvailableMovies;

    public ListOfMoviesView(InputOutputManger inputOutputManger) {
        super(inputOutputManger);
    }

    @Override
    public void render() {
        io.printLine("List of movies available.");
        for (int i = 0; i < listOfAvailableMovies.size(); i++) {
            io.printLine((i + 1) + ". \t" + listOfAvailableMovies.get(i));
        }
    }

    public void setAvailableMovies(List<String> listOfAvailableMovies) {
        this.listOfAvailableMovies = listOfAvailableMovies;
    }
}
