package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MenuViewControllerTest {
    Library library;
    Book letusc = new Book("Let Us C", "Yashwant Kanetkar", "2000");
    Book galvin = new Book("Operating System", "Galvin", "2005");
    Book internetSec = new Book("Internet Security", "Ankit Fadia", "1995");
    Book fivePoint = new Book("Five Point Someone", "Chetan Bhagat", "2012");
    private ByteArrayOutputStream output;
    private InputOutputManger io;
    private MenuViewController menuVC;

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
        menuVC = new MenuViewController(library, io);
    }

    @Test
    public void shouldDisplayOptionToPerformListOfBooksAction(){
        runTestCaseWithInput("\n");

        menuVC.setAction("1", new ListOfBooksViewController(library, io));
        assertTrue(menuVC.execute());
        assertEquals("Main Menu.\n1. \tList of books available.\n", output.toString());
    }

    @Test
    public void shouldPerformListOfBooksActionOnItsSelection(){
        runTestCaseWithInput("1\n");
        menuVC.setAction("1", new ListOfBooksViewController(library, io));
        menuVC.execute();
        String viewTitle = "List of books available.\n";
        String listOfBooks = "";
        listOfBooks += "1. \t" + fivePoint.getFormattedString() + "\n";
        listOfBooks += "2. \t" + letusc.getFormattedString() + "\n";
        listOfBooks += "3. \t" + galvin.getFormattedString() + "\n";
        listOfBooks += "4. \t" + internetSec.getFormattedString() + "\n";
        assertTrue(output.toString().contains(viewTitle + listOfBooks));
    }

    @Test
    public void shouldDisplayErrorMessageOnInvalidSelectionOfMenuItem(){
        runTestCaseWithInput("2\n");
        menuVC.setAction("1", new ListOfBooksViewController(library, io));
        assertTrue(menuVC.execute());

        assertTrue(output.toString().contains("Select a valid option!"));
    }

    @Test
    public void shouldDisplayOptionToPerformCheckoutOfBookAction(){
        runTestCaseWithInput("\n");
        menuVC.setAction("1", new CheckoutBookViewController(library, io));
        menuVC.execute();
        assertEquals("Main Menu.\n1. \tCheckout book.\n", output.toString());
    }
}
