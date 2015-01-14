package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;

public class CheckinMovieControllerTest {
    LibraryManager manager;
    Movie seven = new Movie("Seven", "1995", "David Fincher", "8");
    Movie darkKnight = new Movie("The Dark Knight", "2008", "Christopher Nolan", "unrated");

    private ByteArrayOutputStream output;
    private InputOutputManger io;
    private CheckinMovieController checkinMovieVC;

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
        checkinMovieVC = new CheckinMovieController(manager, io);
    }

    @Test
    public void shouldCheckinSevenMovie(){
        runTestCaseWithInput(seven.getTitle() + "\n");
        manager.checkoutMovie(seven.getTitle());
        checkinMovieVC.execute();
        assertFalse(manager.isMovieCheckedOut(seven.getTitle()));
    }

    @Test
    public void shouldPrintSuccessfulMessageOnSuccessfulCheckin(){
        runTestCaseWithInput(seven.getTitle() + "\n");
        manager.checkoutMovie(seven.getTitle());
        checkinMovieVC.execute();
        assertEquals("Thank you for returning the movie.\n", output.toString());
    }

    @Test
    public void shouldPrintUnSuccessfulMessageOnUnSuccessfulCheckinIfMovieCheckedinAlready(){
        runTestCaseWithInput(seven.getTitle() + "\n");
        checkinMovieVC.execute();
        assertEquals("That is not a valid movie to return.\n", output.toString());
    }

    @Test
    public void shouldPrintUnSuccessfulMessageOnUnSuccessfulCheckinIfMovieDoesntExist(){
        runTestCaseWithInput("Programing C\n");
        checkinMovieVC.execute();
        assertEquals("That is not a valid movie to return.\n", output.toString());
    }
}
