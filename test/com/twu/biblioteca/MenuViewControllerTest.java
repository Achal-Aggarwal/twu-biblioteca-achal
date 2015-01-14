package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MenuViewControllerTest {
    LibraryManager manager;
    Book letusc = new Book("Let Us C", "Yashwant Kanetkar", "2000");
    Book galvin = new Book("Operating System", "Galvin", "2005");
    Book internetSec = new Book("Internet Security", "Ankit Fadia", "1995");
    Book fivePoint = new Book("Five Point Someone", "Chetan Bhagat", "2012");

    private ByteArrayOutputStream output;
    private InputOutputManger io;
    private MenuController menuVC;

    @Before
    public void setUp() {
        BookLibrary bookLibrary = new BookLibrary();
        bookLibrary.addItem(letusc);
        bookLibrary.addItem(galvin);
        bookLibrary.addItem(internetSec);
        bookLibrary.addItem(fivePoint);

        manager = new LibraryManager(bookLibrary, new MovieLibrary());
    }

    private void runTestCaseWithInput(String input) {
        output = new ByteArrayOutputStream();
        io = new InputOutputManger(
                new ByteArrayInputStream(input.getBytes()),
                new PrintStream(output)
        );
        menuVC = new MenuController(manager, io);
        menuVC.setAction("q", new QuitMenuController(manager, io));

    }

    @Test
    public void shouldDisplayOptionToPerformQuitAction(){
        runTestCaseWithInput("q\n");

        assertTrue(menuVC.execute());
        assertEquals("Main Menu.\nq. \tQuit.\n", output.toString());
    }

    @Test
    public void shouldPerformListOfBooksActionOnItsSelection(){
        runTestCaseWithInput("1\nq\n");
        menuVC.setAction("1", new ListOfBooksController(manager, io));
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
        runTestCaseWithInput("2\nq\n");
        menuVC.setAction("1", new ListOfBooksController(manager, io));
        assertTrue(menuVC.execute());

        assertTrue(output.toString().contains("Select a valid option!"));
    }
}
