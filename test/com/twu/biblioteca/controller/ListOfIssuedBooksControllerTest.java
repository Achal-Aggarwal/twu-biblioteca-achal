package com.twu.biblioteca.controller;

import com.twu.biblioteca.controller.ListOfIssuedBooksController;
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

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class ListOfIssuedBooksControllerTest {

    ItemCollection bookLibrary;
    Book letusc = new Book("Let Us C", "Yashwant Kanetkar", "2000");
    Book galvin = new Book("Operating System", "Galvin", "2005");
    Book internetSec = new Book("Internet Security", "Ankit Fadia", "1995");
    Book fivePoint = new Book("Five Point Someone", "Chetan Bhagat", "2012");
    User user = new User("000-0000", "achal", "", "", "");
    SessionManager session;

    @Before
    public void setUp() {
        bookLibrary = new ItemCollection();
        bookLibrary.addItem(letusc);
        bookLibrary.addItem(galvin);
        bookLibrary.addItem(internetSec);
        bookLibrary.addItem(fivePoint);
        session = SessionManager.getSession();
    }

    @After
    public void tearDown() {
        SessionManager.clearSession();
    }

    private String formatBookInfo(Book book, User issuer){
        String string = book.getTitle() + "|\t|";
        string += book.getAuthor() + "|\t|";
        string += book.getPublicationDate() + "|\t|";
        string += "issued by " + issuer.getLibraryNumber() + "|";

        return string + "\n";
    }

    @Test
    public void shouldPrintListOfIssuedBooks(){
        String input = "\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Library manager = new Library(bookLibrary, new ItemCollection());
        ListOfIssuedBooksController listOfIssuedBooksVC =
                new ListOfIssuedBooksController(
                        new InputOutputManger(
                                new ByteArrayInputStream(input.getBytes()),
                                new PrintStream(output)
                        ), manager
                );
        session.registerUser(user);
        session.login(user.getLibraryNumber());
        manager.checkoutBook(letusc.getTitle());
        manager.checkoutBook(internetSec.getTitle());

        listOfIssuedBooksVC.execute();

        String viewTitle = "List of issued books.\n";
        String listOfBooks = "";
        listOfBooks += formatBookInfo(letusc, user);
        listOfBooks += formatBookInfo(internetSec, user);

        assertTrue(output.toString().contains(viewTitle + listOfBooks));
    }

    @Test
    public void shouldShowItselfForLibrarian(){
        String input = "\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Library manager = new Library(bookLibrary, new ItemCollection());
        ListOfIssuedBooksController listOfIssuedBooksVC =
                new ListOfIssuedBooksController(
                        new InputOutputManger(
                                new ByteArrayInputStream(input.getBytes()),
                                new PrintStream(output)
                        ), manager
                );
        user = new User("000-0010", "abhishek", "", "", "", true);
        session.registerUser(user);
        session.login(user.getLibraryNumber());

        assertFalse(listOfIssuedBooksVC.isHidden());
    }

    @Test
    public void shouldHideItselfForAllUsersOtherThanLibrarian(){
        String input = "\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Library manager = new Library(bookLibrary, new ItemCollection());
        ListOfIssuedBooksController listOfIssuedBooksVC =
                new ListOfIssuedBooksController(
                        new InputOutputManger(
                                new ByteArrayInputStream(input.getBytes()),
                                new PrintStream(output)
                        ), manager
                );
        session.registerUser(user);
        session.login(user.getLibraryNumber());

        assertTrue(listOfIssuedBooksVC.isHidden());
    }
}
