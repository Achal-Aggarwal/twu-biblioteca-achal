package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by achalaggarwal on 1/13/15.
 */
public class MenuViewControllerTest {
    Library library;
    Book letusc = new Book("Let Us C", "Yashwant Kanetkar", "2000");
    Book galvin = new Book("Operating System", "Galvin", "2005");
    Book internetSec = new Book("Internet Security", "Ankit Fadia", "1995");
    Book fivePoint = new Book("Five Point Someone", "Chetan Bhagat", "2012");

    @Before
    public void setUp() {
        library = new Library();
        library.addBook(letusc);
        library.addBook(galvin);
        library.addBook(internetSec);
        library.addBook(fivePoint);
    }

    @Test
    public void shouldDisplayOptionToPerformListOfBooksAction(){
        String input = "\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Scanner inputScanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        inputScanner.useDelimiter("\n");

        MenuViewController menuVC =
                new MenuViewController(library, new PrintStream(output),
                        inputScanner);

        menuVC.setAction("1", new ListOfBooksViewController(library, new PrintStream(output), inputScanner));
        assertTrue(menuVC.execute());
        assertEquals("Main Menu.\n1. \tList of books available.\n", output.toString());
    }

    @Test
    public void shouldPerformListOfBooksActionOnItsSelection(){
        String input = "1\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Scanner inputScanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        inputScanner.useDelimiter("\n");

        MenuViewController menuVC =
                new MenuViewController(library, new PrintStream(output),
                        inputScanner);

        menuVC.setAction("1", new ListOfBooksViewController(library, new PrintStream(output), inputScanner));
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
        String input = "2\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Scanner inputScanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        inputScanner.useDelimiter("\n");

        MenuViewController menuVC =
                new MenuViewController(library, new PrintStream(output),
                        inputScanner);

        menuVC.setAction("1", new ListOfBooksViewController(library, new PrintStream(output), inputScanner));
        assertTrue(menuVC.execute());

        assertTrue(output.toString().contains("Select a valid option!"));
    }

    @Test
    public void shouldDisplayOptionToPerformCheckoutOfBookAction(){
        String input = "\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Scanner inputScanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        inputScanner.useDelimiter("\n");

        MenuViewController menuVC =
                new MenuViewController(library, new PrintStream(output),
                        inputScanner);

        menuVC.setAction("1", new CheckoutBookViewController(library, new PrintStream(output), inputScanner));
        menuVC.execute();
        assertEquals("Main Menu.\n1. \tCheckout book.\n", output.toString());
    }
}
