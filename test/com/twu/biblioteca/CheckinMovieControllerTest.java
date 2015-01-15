package com.twu.biblioteca;

import com.sun.deploy.util.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckinMovieControllerTest {
    LibraryManager manager;
    Movie seven = new Movie("Seven", "1995", "David Fincher", "8");
    Movie darkKnight = new Movie("The Dark Knight", "2008", "Christopher Nolan", "unrated");
    User user = new User("000-0000", "achal", "", "", "");

    private ByteArrayOutputStream output;
    private InputOutputManger io;
    private CheckinMovieController checkinMovieVC;
    private SessionManager session;

    @Before
    public void setUp() {
        MovieLibrary movieLibrary = new MovieLibrary();
        movieLibrary.addItem(seven);
        movieLibrary.addItem(darkKnight);
        manager = new LibraryManager(new BookLibrary(), movieLibrary);
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
        checkinMovieVC = new CheckinMovieController(io, manager);
    }

    @Test
    public void shouldCheckinSevenMovie(){
        runTestCaseWithInput(Arrays.asList(user.getLibraryNumber(), user.getPassword(), seven.getTitle()));
        manager.checkoutMovie(seven.getTitle());
        seven.setBorrower(user);
        checkinMovieVC.execute();
        assertFalse(manager.isMovieCheckedOut(seven.getTitle()));
    }

    @Test
    public void shouldNotCheckinSevenMovieIfUserIsInvalid(){
        session.login(user.getLibraryNumber());
        manager.checkoutMovie(seven.getTitle());
        session.logout();
        runTestCaseWithInput(Arrays.asList(user.getLibraryNumber(), "asd", seven.getTitle()));
        checkinMovieVC.execute();
        assertTrue(manager.isMovieCheckedOut(seven.getTitle()));
    }

    @Test
    public void shouldPrintSuccessfulMessageOnSuccessfulCheckin(){
        session.login(user.getLibraryNumber());
        runTestCaseWithInput(Arrays.asList(seven.getTitle()));
        manager.checkoutMovie(seven.getTitle());
        checkinMovieVC.execute();
        assertEquals("Thank you for returning the movie.\n", output.toString());
    }

    @Test
    public void shouldPrintUnSuccessfulMessageOnUnSuccessfulCheckinIfMovieCheckedinAlready(){
        session.login(user.getLibraryNumber());
        runTestCaseWithInput(Arrays.asList(seven.getTitle()));
        checkinMovieVC.execute();
        assertEquals("That is not a valid movie to return.\n", output.toString());
    }

    @Test
    public void shouldPrintUnSuccessfulMessageOnUnSuccessfulCheckinIfMovieDoesntExist(){
        session.login(user.getLibraryNumber());
        runTestCaseWithInput(Arrays.asList("Programing C"));
        checkinMovieVC.execute();
        assertEquals("That is not a valid movie to return.\n", output.toString());
    }
}
