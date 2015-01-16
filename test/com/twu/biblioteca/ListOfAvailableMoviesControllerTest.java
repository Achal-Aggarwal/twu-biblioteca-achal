package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertTrue;

public class ListOfAvailableMoviesControllerTest {
    ItemCollection movieLibrary;
    Movie seven = new Movie("Seven", "1995", "David Fincher", "8");
    Movie darkKnight = new Movie("The Dark Knight", "2008", "Christopher Nolan", "unrated");

    @Before
    public void setUp() {
        movieLibrary = new ItemCollection();
        movieLibrary.addItem(seven);
        movieLibrary.addItem(darkKnight);
    }

    private String formatMovieInfo(Movie movie){
        String string = movie.getTitle() + "|\t|";
        string += movie.getYear() + "|\t|";
        string += movie.getDirector() + "|\t|";
        string += movie.getRating() + "|";

        return string + "\n";
    }

    @Test
    public void shouldPrintListOfAvailableMovies(){
        String input = "\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        ListOfAvailableMoviesController listOfMoviesVC =
                new ListOfAvailableMoviesController(
                        new InputOutputManger(
                                new ByteArrayInputStream(input.getBytes()),
                                new PrintStream(output)
                        ), new Library(new ItemCollection(), movieLibrary)
                );

        listOfMoviesVC.execute();

        String viewTitle = "List of available movies.\n";
        String listOfMovies = "";
        listOfMovies += formatMovieInfo(darkKnight);
        listOfMovies += formatMovieInfo(seven);
        assertTrue(output.toString().contains(viewTitle + listOfMovies));
    }
}
