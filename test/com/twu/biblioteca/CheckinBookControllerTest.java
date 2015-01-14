package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckinBookControllerTest {
    LibraryManager manager;
    Book letusc = new Book("Let Us C", "Yashwant Kanetkar", "2000");
    Book galvin = new Book("Operating System", "Galvin", "2005");
    Book internetSec = new Book("Internet Security", "Ankit Fadia", "1995");
    Book fivePoint = new Book("Five Point Someone", "Chetan Bhagat", "2012");
    private ByteArrayOutputStream output;
    private InputOutputManger io;
    private CheckinBookController checkinBookVC;

    @Before
    public void setUp() {
        BookLibrary bookLibrary = new BookLibrary();
        bookLibrary.addBook(letusc);
        bookLibrary.addBook(galvin);
        bookLibrary.addBook(internetSec);
        bookLibrary.addBook(fivePoint);
        manager = new LibraryManager(bookLibrary);
    }

    private void runTestCaseWithInput(String input) {
        output = new ByteArrayOutputStream();
        io = new InputOutputManger(
                new ByteArrayInputStream(input.getBytes()),
                new PrintStream(output)
        );
        checkinBookVC = new CheckinBookController(manager, io);
    }

    @Test
    public void shouldCheckinLetUsCBook(){
        runTestCaseWithInput(letusc.getTitle() + "\n");
        manager.checkoutBook(letusc.getTitle());
        checkinBookVC.execute();
        assertFalse(manager.isBookCheckedOut(letusc.getTitle()));
    }

    @Test
    public void shouldPrintSuccessfulMessageOnSuccessfulCheckin(){
        runTestCaseWithInput(letusc.getTitle() + "\n");
        manager.checkoutBook(letusc.getTitle());
        checkinBookVC.execute();
        assertEquals("Thank you for returning the book.\n", output.toString());
    }

    @Test
    public void shouldPrintUnSuccessfulMessageOnUnSuccessfulCheckinIfBookCheckedinAlready(){
        runTestCaseWithInput(letusc.getTitle() + "\n");
        checkinBookVC.execute();
        assertEquals("That is not a valid book to return.\n", output.toString());
    }

    @Test
    public void shouldPrintUnSuccessfulMessageOnUnSuccessfulCheckinIfBookDoesntExist(){
        runTestCaseWithInput("Programing C\n");
        checkinBookVC.execute();
        assertEquals("That is not a valid book to return.\n", output.toString());
    }
}
