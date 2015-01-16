package com.twu.biblioteca.view;

import com.twu.biblioteca.io.InputOutputManger;
import com.twu.biblioteca.library.Book;
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
        String strings[] = new String[]{
                movie.getTitle(),
                movie.getYear(),
                movie.getDirector(),
                movie.getRating()
        };
        return io.formatLine(strings, 20, "|");
    }

    @Override
    public void render() {
        io.printLine(io.buildLine('-', 85));
        io.printLine(io.formatLine(viewTitle, 85));
        io.printLine(io.buildLine('-', 85));
        String headers[] = new String[]{"Title", "Year", "Director", "Rating"};
        io.printLine(io.formatLine(headers, 20, "|"));
        String underline = io.buildLine('-',20);
        io.printLine(io.formatLine(new String[]{underline, underline, underline, underline}, 20, "|"));

        if(listOfItems.size() == 0){
            io.printLine(io.formatLine("Empty", 104));
        }

        for (Item item : listOfItems) {
            io.printLine(formatMovieInfo((Movie) item));
        }
        io.printLine(io.buildLine('-', 85));
    }

    public void setItems(List<Item> listOfItems) {
        this.listOfItems = listOfItems;
    }
}
