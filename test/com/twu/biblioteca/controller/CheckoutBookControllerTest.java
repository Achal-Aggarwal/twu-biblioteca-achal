package com.twu.biblioteca.controller;

import com.sun.deploy.util.StringUtils;
import com.twu.biblioteca.controller.CheckoutBookController;
import com.twu.biblioteca.io.InputOutputManger;
import com.twu.biblioteca.library.Book;
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

import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckoutBookControllerTest {
    Library library;
    Book letusc = new Book("Let Us C", "Yashwant Kanetkar", "2000");
    Book galvin = new Book("Operating System", "Galvin", "2005");
    Book internetSec = new Book("Internet Security", "Ankit Fadia", "1995");
    Book fivePoint = new Book("Five Point Someone", "Chetan Bhagat", "2012");
    User user = new User("000-0000", "achal", "", "", "");

    private ByteArrayOutputStream output;
    private InputOutputManger io;
    private CheckoutBookController checkoutBookVC;
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
        checkoutBookVC = new CheckoutBookController(io, library);
    }

    @Test
    public void shouldCheckoutLetUsCBook(){
        runTestCaseWithInput(Arrays.asList(user.getLibraryNumber(), user.getPassword(), letusc.getTitle()));
        checkoutBookVC.execute();
        assertFalse(library.getListOfAvailableBooks().contains(letusc));
    }

    @Test
    public void shouldNotCheckoutLetUsCBookIfUserIsInValid(){
        runTestCaseWithInput(Arrays.asList(user.getLibraryNumber(), "asd", letusc.getTitle()));
        checkoutBookVC.execute();
        assertTrue(library.getListOfAvailableBooks().contains(letusc));

    }

    @Test
    public void shouldPrintSuccessfulMessageOnSuccessfulCheckout(){
        session.login(user.getLibraryNumber());
        runTestCaseWithInput(Arrays.asList(letusc.getTitle()));
        checkoutBookVC.execute();
        assertEquals("Thank you! Enjoy the book\n", output.toString());
    }

    @Test
    public void shouldPrintUnSuccessfulMessageOnUnSuccessfulCheckoutIfBookCheckedoutAlready(){
        session.login(user.getLibraryNumber());
        runTestCaseWithInput(Arrays.asList(letusc.getTitle()));
        library.checkoutBook(letusc.getTitle());
        checkoutBookVC.execute();
        assertEquals("That book is not available.\n", output.toString());
    }

    @Test
    public void shouldPrintUnSuccessfulMessageOnUnSuccessfulCheckoutIfBookDoesntExist(){
        session.login(user.getLibraryNumber());
        runTestCaseWithInput(Arrays.asList("Programing C"));
        checkoutBookVC.execute();
        assertEquals("That book is not available.\n", output.toString());
    }
}
