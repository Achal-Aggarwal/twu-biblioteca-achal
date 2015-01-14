package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckoutMovieControllerTest {
    LibraryManager manager;
    Movie seven = new Movie("Seven", "1995", "David Fincher", "8");
    Movie darkKnight = new Movie("The Dark Knight", "2008", "Christopher Nolan", "unrated");

    private ByteArrayOutputStream output;
    private InputOutputManger io;
    private CheckoutMovieController checkoutMovieVC;

    @Before
    public void setUp() {
        MovieLibrary movieLibrary = new MovieLibrary();
        movieLibrary.addItem(seven);
        movieLibrary.addItem(darkKnight);
        manager = new LibraryManager(new BookLibrary(), movieLibrary);
    }

    private void runTestCaseWithInput(String input) {
        output = new ByteArrayOutputStream();
        io = new InputOutputManger(
                new ByteArrayInputStream(input.getBytes()),
                new PrintStream(output)
        );
        checkoutMovieVC = new CheckoutMovieController(manager, io);
    }

    @Test
    public void shouldCheckinSevenMovie(){
        runTestCaseWithInput(seven.getTitle() + "\n");
        checkoutMovieVC.execute();
        assertTrue(manager.isMovieCheckedOut(seven.getTitle()));
    }

    @Test
    public void shouldPrintSuccessfulMessageOnSuccessfulCheckout(){
        runTestCaseWithInput(seven.getTitle() + "\n");
        checkoutMovieVC.execute();
        assertEquals("Thank you! Enjoy the movie\n", output.toString());
    }

    @Test
    public void shouldPrintUnSuccessfulMessageOnUnSuccessfulCheckoutIfMovieCheckedoutAlready(){
        runTestCaseWithInput(seven.getTitle() + "\n");
        manager.checkoutMovie(seven.getTitle());
        checkoutMovieVC.execute();
        assertEquals("That movie is not available.\n", output.toString());
    }

    @Test
    public void shouldPrintUnSuccessfulMessageOnUnSuccessfulCheckoutIfMovieDoesntExist(){
        runTestCaseWithInput("Programing\n");
        checkoutMovieVC.execute();
        assertEquals("That movie is not available.\n", output.toString());
    }
}
