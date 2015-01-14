package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckoutBookControllerTest {
    Library library;
    Book letusc = new Book("Let Us C", "Yashwant Kanetkar", "2000");
    Book galvin = new Book("Operating System", "Galvin", "2005");
    Book internetSec = new Book("Internet Security", "Ankit Fadia", "1995");
    Book fivePoint = new Book("Five Point Someone", "Chetan Bhagat", "2012");
    private ByteArrayOutputStream output;
    private InputOutputManger io;
    private CheckoutBookController checkoutBookVC;

    @Before
    public void setUp() {
        library = new Library();
        library.addBook(letusc);
        library.addBook(galvin);
        library.addBook(internetSec);
        library.addBook(fivePoint);
    }

    private void runTestCaseWithInput(String input) {
        output = new ByteArrayOutputStream();
        io = new InputOutputManger(
                new ByteArrayInputStream(input.getBytes()),
                new PrintStream(output)
        );
        checkoutBookVC = new CheckoutBookController(library, io);
    }

    @Test
    public void shouldCheckinLetUsCBook(){
        runTestCaseWithInput(letusc.getTitle() + "\n");
        checkoutBookVC.execute();
        assertTrue(letusc.isCheckedOut());
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
        letusc.checkOut();
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
