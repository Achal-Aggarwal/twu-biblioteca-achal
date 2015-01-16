package com.twu.biblioteca.view;

import com.twu.biblioteca.io.InputOutputManger;
import com.twu.biblioteca.library.Item;
import com.twu.biblioteca.library.Movie;

import java.util.Arrays;
import java.util.List;

public class ListOfAvailableMoviesView extends View {
    private List<Item> listOfItems;
    private String viewTitle;

    public ListOfAvailableMoviesView(InputOutputManger inputOutputManger, String viewTitle) {
        super(inputOutputManger);
        listOfItems =  Arrays.asList();
        this.viewTitle = viewTitle;
    }

    private String formatMovieInfo(Movie movie){
        String string = movie.getTitle() + "|\t|";
        string += movie.getYear() + "|\t|";
        string += movie.getDirector() + "|\t|";
        string += movie.getRating() + "|";

        return string;
    }

    @Override
    public void render() {
        io.printLine(viewTitle);
        for (Item item : listOfItems) {
            io.printLine(formatMovieInfo((Movie) item));
        }
    }

    public void setItems(List<Item> listOfItems) {
        this.listOfItems = listOfItems;
    }
}
