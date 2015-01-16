package com.twu.biblioteca.controller;

import com.sun.deploy.util.StringUtils;
import com.twu.biblioteca.controller.CheckinBookController;
import com.twu.biblioteca.io.InputOutputManger;
import com.twu.biblioteca.library.Book;
import com.twu.biblioteca.library.Item;
import com.twu.biblioteca.library.ItemCollection;
import com.twu.biblioteca.library.Library;
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

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckinBookControllerTest {
    Library library;
    Book letusc = new Book("Let Us C", "Yashwant Kanetkar", "2000");
    Book galvin = new Book("Operating System", "Galvin", "2005");
    Book internetSec = new Book("Internet Security", "Ankit Fadia", "1995");
    Book fivePoint = new Book("Five Point Someone", "Chetan Bhagat", "2012");
    private ByteArrayOutputStream output;
    private InputOutputManger io;
    private CheckinBookController checkinBookVC;
    User user = new User("000-0000", "achal", "", "", "");
    private SessionManager session;

    @Before
    public void setUp() {
        ItemCollection bookLibrary = new ItemCollection();
        bookLibrary.addItem(letusc);
        bookLibrary.addItem(galvin);
        bookLibrary.addItem(internetSec);
        bookLibrary.addItem(fivePoint);
        library = new Library(bookLibrary, new ItemCollection());
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
        checkinBookVC = new CheckinBookController(io, library);
    }

    @Test
    public void shouldCheckinLetUsCBook(){
        runTestCaseWithInput(Arrays.asList(user.getLibraryNumber(), user.getPassword(), letusc.getTitle()));

        session.login(user.getLibraryNumber());
        library.checkoutBook(letusc.getTitle());
        session.logout();

        checkinBookVC.execute();

        List<Item> books = library.getListOfAvailableBooks();
        assertTrue(books.contains(letusc));
    }

    @Test
    public void shouldNotCheckinLetUsCBookIfUserIsInvalid(){
        session.login(user.getLibraryNumber());
        library.checkoutBook(letusc.getTitle());
        session.logout();

        runTestCaseWithInput(Arrays.asList(user.getLibraryNumber(), "asd", letusc.getTitle()));
        checkinBookVC.execute();

        List<Item> books = library.getListOfAvailableBooks();
        assertFalse(books.contains(letusc));
    }

    @Test
    public void shouldPrintSuccessfulMessageOnSuccessfulCheckin(){
        session.login(user.getLibraryNumber());
        runTestCaseWithInput(Arrays.asList(letusc.getTitle()));
        library.checkoutBook(letusc.getTitle());
        checkinBookVC.execute();
        assertEquals("Thank you for returning the book.\n", output.toString());
    }

    @Test
    public void shouldPrintUnSuccessfulMessageOnUnSuccessfulCheckinIfBookCheckedinAlready(){
        session.login(user.getLibraryNumber());
        runTestCaseWithInput(Arrays.asList(letusc.getTitle()));
        checkinBookVC.execute();
        assertEquals("That is not a valid book to return.\n", output.toString());
    }

    @Test
    public void shouldPrintUnSuccessfulMessageOnUnSuccessfulCheckinIfBookDoesntExist(){
        session.login(user.getLibraryNumber());
        runTestCaseWithInput(Arrays.asList("Programing C"));
        checkinBookVC.execute();
        assertEquals("That is not a valid book to return.\n", output.toString());
    }
}
