package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MenuViewControllerTest {
    Library manager;
    Book letusc = new Book("Let Us C", "Yashwant Kanetkar", "2000");
    Book galvin = new Book("Operating System", "Galvin", "2005");
    Book internetSec = new Book("Internet Security", "Ankit Fadia", "1995");
    Book fivePoint = new Book("Five Point Someone", "Chetan Bhagat", "2012");

    private ByteArrayOutputStream output;
    private InputOutputManger io;
    private MenuController menuVC;
    private SessionManager session;

    @Before
    public void setUp() {
        ItemCollection bookLibrary = new ItemCollection();
        bookLibrary.addItem(letusc);
        bookLibrary.addItem(galvin);
        bookLibrary.addItem(internetSec);
        bookLibrary.addItem(fivePoint);

        manager = new Library(bookLibrary, new ItemCollection());
        session = SessionManager.getSession();
    }

    @After
    public void tearDown(){
        SessionManager.clearSession();
    }

    private void runTestCaseWithInput(String input) {
        output = new ByteArrayOutputStream();
        io = new InputOutputManger(
                new ByteArrayInputStream(input.getBytes()),
                new PrintStream(output)
        );
        menuVC = new MenuController(io);
        menuVC.setAction("q", new QuitMenuController(io));

    }

    @Test
    public void shouldDisplayOptionToPerformQuitAction(){
        runTestCaseWithInput("q\n");

        assertTrue(menuVC.execute());
        assertEquals("Main Menu.\nq. \tQuit.\n", output.toString());
    }

    @Test
    public void shouldAbleToDisplayOptionBasedOnTypeOfUserLoggedIn(){
        runTestCaseWithInput("q\n");
        User librarian = new User("000-0000", "achal", "", "", "", true);
        session.registerUser(librarian);
        session.login(librarian.getLibraryNumber());
        menuVC.setAction("1", new ListOfIssuedBooksController(io, manager));

        assertTrue(menuVC.execute());
        assertEquals("Main Menu.\nq. \tQuit.\n1. \tList of issued books.\n", output.toString());
    }

    @Test
    public void shouldAbleToHideOptionBasedOnTypeOfUserLoggedIn(){
        runTestCaseWithInput("q\n");
        User normal_user = new User("000-0000", "achal", "", "", "");
        session.registerUser(normal_user);
        session.login(normal_user.getLibraryNumber());
        menuVC.setAction("1", new ListOfIssuedBooksController(io, manager));

        assertTrue(menuVC.execute());
        assertEquals("Main Menu.\nq. \tQuit.\n", output.toString());
    }

    private String formatBookInfo(Book book){
        String string = book.getTitle() + "|\t|";
        string += book.getAuthor() + "|\t|";
        string += book.getPublicationDate() + "|";

        return string + "\n";
    }

    @Test
    public void shouldPerformListOfBooksActionOnItsSelection(){
        runTestCaseWithInput("1\nq\n");
        menuVC.setAction("1", new ListOfAvailableBooksController(io, manager));
        menuVC.execute();
        String viewTitle = "List of available books.\n";
        String listOfBooks = "";
        listOfBooks += formatBookInfo(fivePoint);
        listOfBooks += formatBookInfo(letusc);
        listOfBooks += formatBookInfo(galvin);
        listOfBooks += formatBookInfo(internetSec);
        assertTrue(output.toString().contains(viewTitle + listOfBooks));
    }

    @Test
    public void shouldDisplayErrorMessageOnInvalidSelectionOfMenuItem(){
        runTestCaseWithInput("2\nq\n");
        menuVC.setAction("1", new ListOfAvailableBooksController(io, manager));
        assertTrue(menuVC.execute());

        assertTrue(output.toString().contains("Select a valid option!"));
    }
}
