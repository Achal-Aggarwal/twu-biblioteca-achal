package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BibliotecaTest {
    BibliotecaApp app;
    Book letusc = new Book("Let Us C", "Yashwant Kanetkar", "2000");
    Book galvin = new Book("Operating System", "Galvin", "2005");
    Book internetSec = new Book("Internet Security", "Ankit Fadia", "1995");
    Book fivePoint = new Book("Five Point Someone", "Chetan Bhagat", "2012");

    @Before
    public void setUp() {
        app = new BibliotecaApp();
        app.addBook(letusc);
        app.addBook(galvin);
        app.addBook(internetSec);
        app.addBook(fivePoint);
    }

    @Test
    public void testWelcomeMessage() {
        assertEquals("Welcome and thank you for taking time to visit Biblioteca.",
                app.welcomeMessage());
    }

    @Test
    public void testAddBook() {
        assertTrue(app.isPresent(letusc));
    }

    @Test
    public void testListOfBooks() {
        List<Book> bookList = app.getListOfBooks();
        assertTrue(bookList.contains(letusc));
        assertTrue(bookList.contains(galvin));
        assertTrue(bookList.contains(internetSec));
        assertTrue(bookList.contains(fivePoint));
    }

    @Test
    public void testCheckoutOfABook(){
        assertFalse(app.isBookCheckedOut(letusc.getTitle()));
        assertTrue(app.checkout(letusc.getTitle()));
        assertTrue(app.isBookCheckedOut(letusc.getTitle()));
    }

    @Test
    public void testCheckoutOfABookThatDoesntExist(){
        assertTrue(app.isBookCheckedOut("Foobar"));
        assertFalse(app.checkout("Foobar"));
    }

    @Test
    public void testCheckoutOfABookThatIsCheckedout(){
        assertFalse(app.isBookCheckedOut(letusc.getTitle()));
        assertTrue(app.checkout(letusc.getTitle()));
        assertFalse(app.checkout(letusc.getTitle()));
    }

    @Test
    public void testListOfBooksAfterCheckingOutABook(){
        List<Book> bookList = app.getListOfBooks();
        assertTrue(bookList.contains(galvin));
        assertTrue(app.checkout(galvin.getTitle()));
        bookList = app.getListOfBooks();
        assertFalse(bookList.contains(galvin));
    }

    @Test
    public void testCheckinOfABook(){
        assertFalse(app.isBookCheckedOut(letusc.getTitle()));
        assertTrue(app.checkout(letusc.getTitle()));
        assertTrue(app.isBookCheckedOut(letusc.getTitle()));
        assertTrue(app.checkin(letusc.getTitle()));
        assertFalse(app.isBookCheckedOut(letusc.getTitle()));
    }

    @Test
    public void testCheckinOfABookThatDoesntExist(){
        assertTrue(app.isBookCheckedOut("Foobar"));
        assertFalse(app.checkin("Foobar"));
    }

    @Test
    public void testCheckinOfABookThatIsCheckedinAlready(){
        assertFalse(app.isBookCheckedOut(letusc.getTitle()));
        assertFalse(app.checkin(letusc.getTitle()));
    }
}
