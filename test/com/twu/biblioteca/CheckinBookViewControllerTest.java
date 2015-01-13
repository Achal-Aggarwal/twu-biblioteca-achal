package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by achalaggarwal on 1/13/15.
 */
public class CheckinBookViewControllerTest {
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
    public void shouldCheckinLetUsCBook(){
        String input = letusc.getTitle() + "\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Scanner inputScanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        inputScanner.useDelimiter("\n");

        CheckinBookViewController checkinBookVC =
                new CheckinBookViewController(library, new PrintStream(output),
                        inputScanner);

        letusc.checkOut();
        checkinBookVC.execute();
        assertFalse(letusc.isCheckedOut());
    }

    @Test
    public void shouldPrintSuccessfulMessageOnSuccessfulCheckin(){
        String input = letusc.getTitle() + "\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Scanner inputScanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        inputScanner.useDelimiter("\n");

        CheckinBookViewController checkinBookVC =
                new CheckinBookViewController(library, new PrintStream(output),
                        inputScanner);

        letusc.checkOut();
        checkinBookVC.execute();
        assertEquals("Thank you for returning the book.\n", output.toString());
    }

    @Test
    public void shouldPrintUnSuccessfulMessageOnUnSuccessfulCheckinIfBookCheckedinAlready(){
        String input = letusc.getTitle() + "\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Scanner inputScanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        inputScanner.useDelimiter("\n");

        CheckinBookViewController checkinBookVC =
                new CheckinBookViewController(library, new PrintStream(output),
                        inputScanner);

        checkinBookVC.execute();
        assertEquals("That is not a valid book to return.\n", output.toString());
    }

    @Test
    public void shouldPrintUnSuccessfulMessageOnUnSuccessfulCheckinIfBookDoesntExist(){
        String input = "Programing C\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Scanner inputScanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        inputScanner.useDelimiter("\n");

        CheckinBookViewController checkinBookVC =
                new CheckinBookViewController(library, new PrintStream(output),
                        inputScanner);

        checkinBookVC.execute();
        assertEquals("That is not a valid book to return.\n", output.toString());
    }
}
