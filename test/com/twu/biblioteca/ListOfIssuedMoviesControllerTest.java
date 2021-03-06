package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class ListOfIssuedMoviesControllerTest {

    MovieLibrary movieLibrary;
    Movie seven = new Movie("Seven", "1995", "David Fincher", "8");
    Movie darkKnight = new Movie("The Dark Knight", "2008", "Christopher Nolan", "unrated");
    User user = new User("000-0000", "achal", "", "", "");

    @Before
    public void setUp() {
        movieLibrary = new MovieLibrary();
        movieLibrary.addItem(seven);
        movieLibrary.addItem(darkKnight);
    }

    @Test
    public void shouldPrintListOfIssuedBooks(){
        String input = "\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        LibraryManager manager = new LibraryManager(new BookLibrary(), movieLibrary);
        ListOfIssuedMoviesController listOfIssuedMoviesVC =
                new ListOfIssuedMoviesController(manager,
                        new InputOutputManger(
                                new ByteArrayInputStream(input.getBytes()),
                                new PrintStream(output)
                        )
                );
        manager.registerUser(user);
        manager.setCurrentUser(user.getLibraryNumber());
        manager.checkoutMovie(seven.getTitle());
        manager.checkoutMovie(darkKnight.getTitle());

        listOfIssuedMoviesVC.execute();

        String viewTitle = "List of issued movies.\n";
        String listOfMovies = "";
        listOfMovies += "1. \t" + darkKnight.getFormattedString() + " issued by " + user.contactInformation() + "\n";
        listOfMovies += "2. \t" + seven.getFormattedString() + " issued by " + user.contactInformation() + "\n";
        assertTrue(output.toString().contains(viewTitle + listOfMovies));
    }

    @Test
    public void shouldShowItselfForLibrarian(){
        String input = "\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        LibraryManager manager = new LibraryManager(new BookLibrary(), movieLibrary);
        ListOfIssuedMoviesController listOfIssuedMoviesVC =
                new ListOfIssuedMoviesController(manager,
                        new InputOutputManger(
                                new ByteArrayInputStream(input.getBytes()),
                                new PrintStream(output)
                        )
                );
        user = new Librarian("000-0010", "abhishek", "", "", "");
        manager.registerUser(user);
        manager.setCurrentUser(user.getLibraryNumber());

        assertFalse(listOfIssuedMoviesVC.isHidden());
    }

    @Test
    public void shouldHideItselfForAllUsersOtherThanLibrarian(){
        String input = "\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        LibraryManager manager = new LibraryManager(new BookLibrary(), movieLibrary);
        ListOfIssuedMoviesController listOfIssuedMoviesVC =
                new ListOfIssuedMoviesController(manager,
                        new InputOutputManger(
                                new ByteArrayInputStream(input.getBytes()),
                                new PrintStream(output)
                        )
                );
        manager.registerUser(user);
        manager.setCurrentUser(user.getLibraryNumber());

        assertTrue(listOfIssuedMoviesVC.isHidden());
    }
}
