package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class ListOfIssuedBooksControllerTest {

    BookLibrary bookLibrary;
    Book letusc = new Book("Let Us C", "Yashwant Kanetkar", "2000");
    Book galvin = new Book("Operating System", "Galvin", "2005");
    Book internetSec = new Book("Internet Security", "Ankit Fadia", "1995");
    Book fivePoint = new Book("Five Point Someone", "Chetan Bhagat", "2012");
    User user = new User("000-0000", "achal");

    @Before
    public void setUp() {
        bookLibrary = new BookLibrary();
        bookLibrary.addItem(letusc);
        bookLibrary.addItem(galvin);
        bookLibrary.addItem(internetSec);
        bookLibrary.addItem(fivePoint);
    }

    @Test
    public void shouldPrintListOfIssuedBooks(){
        String input = "\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        LibraryManager manager = new LibraryManager(bookLibrary, new MovieLibrary());
        ListOfIssuedBooksController listOfIssuedBooksVC =
                new ListOfIssuedBooksController(manager,
                        new InputOutputManger(
                                new ByteArrayInputStream(input.getBytes()),
                                new PrintStream(output)
                        )
                );
        manager.registerUser(user);
        manager.setCurrentUser(user.getLibraryNumber());
        manager.checkoutBook(letusc.getTitle());
        manager.checkoutBook(internetSec.getTitle());

        listOfIssuedBooksVC.execute();

        String viewTitle = "List of issued books.\n";
        String listOfBooks = "";
        listOfBooks += "1. \t" + letusc.getFormattedString() + " issued by 000-0000\n";
        listOfBooks += "2. \t" + internetSec.getFormattedString() + " issued by 000-0000\n";

        assertTrue(output.toString().contains(viewTitle + listOfBooks));
    }

    @Test
    public void shouldShowItselfForLibrarian(){
        String input = "\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        LibraryManager manager = new LibraryManager(bookLibrary, new MovieLibrary());
        ListOfIssuedBooksController listOfIssuedBooksVC =
                new ListOfIssuedBooksController(manager,
                        new InputOutputManger(
                                new ByteArrayInputStream(input.getBytes()),
                                new PrintStream(output)
                        )
                );
        user = new Librarian("000-0010", "abhishek");
        manager.registerUser(user);
        manager.setCurrentUser(user.getLibraryNumber());

        assertFalse(listOfIssuedBooksVC.isHidden());
    }

    @Test
    public void shouldHideItselfForAllUsersOtherThanLibrarian(){
        String input = "\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        LibraryManager manager = new LibraryManager(bookLibrary, new MovieLibrary());
        ListOfIssuedBooksController listOfIssuedBooksVC =
                new ListOfIssuedBooksController(manager,
                        new InputOutputManger(
                                new ByteArrayInputStream(input.getBytes()),
                                new PrintStream(output)
                        )
                );
        manager.registerUser(user);
        manager.setCurrentUser(user.getLibraryNumber());

        assertTrue(listOfIssuedBooksVC.isHidden());
    }
}
