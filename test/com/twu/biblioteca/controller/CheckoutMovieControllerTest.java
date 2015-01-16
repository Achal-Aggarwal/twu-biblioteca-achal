package com.twu.biblioteca.controller;

import com.sun.deploy.util.StringUtils;
import com.twu.biblioteca.controller.CheckoutMovieController;
import com.twu.biblioteca.io.InputOutputManger;
import com.twu.biblioteca.library.ItemCollection;
import com.twu.biblioteca.library.Library;
import com.twu.biblioteca.library.Movie;
import com.twu.biblioteca.session.SessionManager;
import com.twu.biblioteca.session.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckoutMovieControllerTest {
    Library library;
    Movie seven = new Movie("Seven", "1995", "David Fincher", "8");
    Movie darkKnight = new Movie("The Dark Knight", "2008", "Christopher Nolan", "unrated");
    User user = new User("000-0000", "achal", "", "", "");

    private ByteArrayOutputStream output;
    private InputOutputManger io;
    private CheckoutMovieController checkoutMovieVC;
    private SessionManager session;

    @Before
    public void setUp() {
        ItemCollection movieLibrary = new ItemCollection();
        movieLibrary.addItem(seven);
        movieLibrary.addItem(darkKnight);
        library = new Library(new ItemCollection(), movieLibrary);
        session = SessionManager.getSession();
        session.registerUser(user);
    }

    @After
    public void tearDown() {
        SessionManager.clearSession();
    }


    private void runTestCaseWithInput(List<String> inputs) {
        String input = StringUtils.join(inputs, "\n");
        output = new ByteArrayOutputStream();
        io = new InputOutputManger(
                new ByteArrayInputStream(input.getBytes()),
                new PrintStream(output)
        );
        checkoutMovieVC = new CheckoutMovieController(io, library);
    }

    @Test
    public void shouldCheckoutSevenMovie(){
        runTestCaseWithInput(Arrays.asList(user.getLibraryNumber(), user.getPassword(), seven.getTitle()));
        checkoutMovieVC.execute();
        assertFalse(library.getListOfAvailableMovies().contains(seven));

    }

    @Test
    public void shouldNotCheckoutSevenMovieIfUserIsInValid(){
        runTestCaseWithInput(Arrays.asList(user.getLibraryNumber(), "asd", seven.getTitle()));
        checkoutMovieVC.execute();
        assertTrue(library.getListOfAvailableMovies().contains(seven));
    }

    @Test
    public void shouldPrintSuccessfulMessageOnSuccessfulCheckout(){
        session.login(user.getLibraryNumber());
        runTestCaseWithInput(Arrays.asList(seven.getTitle()));
        checkoutMovieVC.execute();
        assertEquals("Thank you! Enjoy the movie\n", output.toString());
    }

    @Test
    public void shouldPrintUnSuccessfulMessageOnUnSuccessfulCheckoutIfMovieCheckedoutAlready(){
        session.login(user.getLibraryNumber());
        runTestCaseWithInput(Arrays.asList(seven.getTitle()));
        library.checkoutMovie(seven.getTitle());
        checkoutMovieVC.execute();
        assertEquals("That movie is not available.\n", output.toString());
    }

    @Test
    public void shouldPrintUnSuccessfulMessageOnUnSuccessfulCheckoutIfMovieDoesntExist(){
        session.login(user.getLibraryNumber());
        runTestCaseWithInput(Arrays.asList("Programing\n"));
        checkoutMovieVC.execute();
        assertEquals("That movie is not available.\n", output.toString());
    }
}
