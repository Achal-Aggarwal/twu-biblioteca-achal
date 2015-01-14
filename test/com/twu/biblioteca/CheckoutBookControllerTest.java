package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckoutBookControllerTest {
    LibraryManager manager;
    Book letusc = new Book("Let Us C", "Yashwant Kanetkar", "2000");
    Book galvin = new Book("Operating System", "Galvin", "2005");
    Book internetSec = new Book("Internet Security", "Ankit Fadia", "1995");
    Book fivePoint = new Book("Five Point Someone", "Chetan Bhagat", "2012");
    private ByteArrayOutputStream output;
    private InputOutputManger io;
    private CheckoutBookController checkoutBookVC;

    @Before
    public void setUp() {
        BookLibrary bookLibrary = new BookLibrary();
        bookLibrary.addBook(letusc);
        bookLibrary.addBook(galvin);
        bookLibrary.addBook(internetSec);
        bookLibrary.addBook(fivePoint);
        manager = new LibraryManager(bookLibrary, new MovieLibrary());
    }

    private void runTestCaseWithInput(String input) {
        output = new ByteArrayOutputStream();
        io = new InputOutputManger(
                new ByteArrayInputStream(input.getBytes()),
                new PrintStream(output)
        );
        checkoutBookVC = new CheckoutBookController(manager, io);
    }

    @Test
    public void shouldCheckinLetUsCBook(){
        runTestCaseWithInput(letusc.getTitle() + "\n");
        checkoutBookVC.execute();
        assertTrue(manager.isBookCheckedOut(letusc.getTitle()));
    }

    @Test
    public void shouldPrintSuccessfulMessageOnSuccessfulCheckout(){
        runTestCaseWithInput(letusc.getTitle() + "\n");
        checkoutBookVC.execute();
        assertEquals("Thank you! Enjoy the book\n", output.toString());
    }

    @Test
    public void shouldPrintUnSuccessfulMessageOnUnSuccessfulCheckoutIfBookCheckedoutAlready(){
        runTestCaseWithInput(letusc.getTitle() + "\n");
        manager.checkoutBook(letusc.getTitle());
        checkoutBookVC.execute();
        assertEquals("That book is not available.\n", output.toString());
    }

    @Test
    public void shouldPrintUnSuccessfulMessageOnUnSuccessfulCheckoutIfBookDoesntExist(){
        runTestCaseWithInput("Programing C\n");
        checkoutBookVC.execute();
        assertEquals("That book is not available.\n", output.toString());
    }
}
