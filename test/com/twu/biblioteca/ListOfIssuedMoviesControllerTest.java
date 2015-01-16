package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class ListOfIssuedMoviesControllerTest {

    ItemCollection movieLibrary;
    Movie seven = new Movie("Seven", "1995", "David Fincher", "8");
    Movie darkKnight = new Movie("The Dark Knight", "2008", "Christopher Nolan", "unrated");
    User user = new User("000-0000", "achal", "", "", "");
    SessionManager session;

    @Before
    public void setUp() {
        movieLibrary = new ItemCollection();
        movieLibrary.addItem(seven);
        movieLibrary.addItem(darkKnight);
        session = SessionManager.getSession();
    }

    @After
    public void tearDown() {
        SessionManager.clearSession();
    }

    private String formatMovieInfo(Movie movie, User issuer){
        String string = movie.getTitle() + "|\t|";
        string += movie.getYear() + "|\t|";
        string += movie.getDirector() + "|\t|";
        string += movie.getRating() + "|\t|";
        string += "issued by " + issuer.getLibraryNumber() + "|";

        return string + "\n";
    }

    @Test
    public void shouldPrintListOfIssuedBooks(){
        String input = "\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Library manager = new Library(new ItemCollection(), movieLibrary);
        ListOfIssuedMoviesController listOfIssuedMoviesVC =
                new ListOfIssuedMoviesController(
                        new InputOutputManger(
                                new ByteArrayInputStream(input.getBytes()),
                                new PrintStream(output)
                        ), manager
                );
        session.registerUser(user);
        session.login(user.getLibraryNumber());
        manager.checkoutMovie(seven.getTitle());
        manager.checkoutMovie(darkKnight.getTitle());

        listOfIssuedMoviesVC.execute();

        String viewTitle = "List of issued movies.\n";
        String listOfMovies = "";
        listOfMovies += formatMovieInfo(darkKnight, user);
        listOfMovies += formatMovieInfo(seven, user);
        assertTrue(output.toString().contains(viewTitle + listOfMovies));
    }

    @Test
    public void shouldShowItselfForLibrarian(){
        String input = "\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Library manager = new Library(new ItemCollection(), movieLibrary);
        ListOfIssuedMoviesController listOfIssuedMoviesVC =
                new ListOfIssuedMoviesController(
                        new InputOutputManger(
                                new ByteArrayInputStream(input.getBytes()),
                                new PrintStream(output)
                        ), manager
                );
        user = new User("000-0010", "abhishek", "", "", "", true);
        session.registerUser(user);
        session.login(user.getLibraryNumber());

        assertFalse(listOfIssuedMoviesVC.isHidden());
    }

    @Test
    public void shouldHideItselfForAllUsersOtherThanLibrarian(){
        String input = "\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Library manager = new Library(new ItemCollection(), movieLibrary);
        ListOfIssuedMoviesController listOfIssuedMoviesVC =
                new ListOfIssuedMoviesController(
                        new InputOutputManger(
                                new ByteArrayInputStream(input.getBytes()),
                                new PrintStream(output)
                        ), manager
                );
        session.registerUser(user);
        session.login(user.getLibraryNumber());

        assertTrue(listOfIssuedMoviesVC.isHidden());
    }
}
