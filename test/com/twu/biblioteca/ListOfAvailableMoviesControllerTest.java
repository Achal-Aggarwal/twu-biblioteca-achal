package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertTrue;

public class ListOfAvailableMoviesControllerTest {
    MovieLibrary movieLibrary;
    Movie seven = new Movie("Seven", "1995", "David Fincher", "8");
    Movie darkKnight = new Movie("The Dark Knight", "2008", "Christopher Nolan", "unrated");

    @Before
    public void setUp() {
        movieLibrary = new MovieLibrary();
        movieLibrary.addItem(seven);
        movieLibrary.addItem(darkKnight);
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
                        ), new LibraryManager(new BookLibrary(), movieLibrary)
                );

        listOfMoviesVC.execute();

        String viewTitle = "List of available movies.\n";
        String listOfMovies = "";
        listOfMovies += "1. \t" + darkKnight.getFormattedString() + "\n";
        listOfMovies += "2. \t" + seven.getFormattedString() + "\n";
        assertTrue(output.toString().contains(viewTitle + listOfMovies));
    }
}
