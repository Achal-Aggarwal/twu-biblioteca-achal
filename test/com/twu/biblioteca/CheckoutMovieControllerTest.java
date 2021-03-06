package com.twu.biblioteca;

import com.sun.deploy.util.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckoutMovieControllerTest {
    LibraryManager manager;
    Movie seven = new Movie("Seven", "1995", "David Fincher", "8");
    Movie darkKnight = new Movie("The Dark Knight", "2008", "Christopher Nolan", "unrated");
    User user = new User("000-0000", "achal", "", "", "");

    private ByteArrayOutputStream output;
    private InputOutputManger io;
    private CheckoutMovieController checkoutMovieVC;

    @Before
    public void setUp() {
        MovieLibrary movieLibrary = new MovieLibrary();
        movieLibrary.addItem(seven);
        movieLibrary.addItem(darkKnight);
        manager = new LibraryManager(new BookLibrary(), movieLibrary);
        manager.registerUser(user);
    }

    private void runTestCaseWithInput(List<String> inputs) {
        String input = StringUtils.join(inputs, "\n");
        output = new ByteArrayOutputStream();
        io = new InputOutputManger(
                new ByteArrayInputStream(input.getBytes()),
                new PrintStream(output)
        );
        checkoutMovieVC = new CheckoutMovieController(manager, io);
    }

    @Test
    public void shouldCheckoutSevenMovie(){
        runTestCaseWithInput(Arrays.asList(user.getLibraryNumber(), user.getPassword(), seven.getTitle()));
        checkoutMovieVC.execute();
        assertTrue(manager.isMovieCheckedOut(seven.getTitle()));
    }

    @Test
    public void shouldNotCheckoutSevenMovieIfUserIsInValid(){
        runTestCaseWithInput(Arrays.asList(user.getLibraryNumber(), "asd", seven.getTitle()));
        checkoutMovieVC.execute();
        assertFalse(manager.isBookCheckedOut(seven.getTitle()));
    }

    @Test
    public void shouldPrintSuccessfulMessageOnSuccessfulCheckout(){
        manager.setCurrentUser(user.getLibraryNumber());
        runTestCaseWithInput(Arrays.asList(seven.getTitle()));
        checkoutMovieVC.execute();
        assertEquals("Thank you! Enjoy the movie\n", output.toString());
    }

    @Test
    public void shouldPrintUnSuccessfulMessageOnUnSuccessfulCheckoutIfMovieCheckedoutAlready(){
        manager.setCurrentUser(user.getLibraryNumber());
        runTestCaseWithInput(Arrays.asList(seven.getTitle()));
        manager.checkoutMovie(seven.getTitle());
        checkoutMovieVC.execute();
        assertEquals("That movie is not available.\n", output.toString());
    }

    @Test
    public void shouldPrintUnSuccessfulMessageOnUnSuccessfulCheckoutIfMovieDoesntExist(){
        manager.setCurrentUser(user.getLibraryNumber());
        runTestCaseWithInput(Arrays.asList("Programing\n"));
        checkoutMovieVC.execute();
        assertEquals("That movie is not available.\n", output.toString());
    }
}
