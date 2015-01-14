package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class LibraryManagerTest {
    BookLibrary bookLibrary;
    Book letusc = new Book("Let Us C", "Yashwant Kanetkar", "2000");
    Book galvin = new Book("Operating System", "Galvin", "2005");
    Book internetSec = new Book("Internet Security", "Ankit Fadia", "1995");
    Book fivePoint = new Book("Five Point Someone", "Chetan Bhagat", "2012");

    @Before
    public void setUp() {
        bookLibrary = new BookLibrary();
        bookLibrary.addBook(letusc);
        bookLibrary.addBook(galvin);
        bookLibrary.addBook(internetSec);
        bookLibrary.addBook(fivePoint);
    }

    @Test
    public void testCheckingOutABook() {
        LibraryManager manager = new LibraryManager(bookLibrary);
        assertTrue(manager.checkoutBook(letusc.getTitle()));
        assertTrue(manager.isBookCheckedOut(letusc.getTitle()));
    }

    @Test
    public void testCheckingInABook() {
        LibraryManager manager = new LibraryManager(bookLibrary);
        manager.checkoutBook(letusc.getTitle());

        assertTrue(manager.checkinBook(letusc.getTitle()));
        assertFalse(manager.isBookCheckedOut(letusc.getTitle()));
    }

    @Test
    public void testListOfAvailableBooks() {
        LibraryManager manager = new LibraryManager(bookLibrary);

        List<String> bookList = manager.getListOfAvailableBooks();
        assertTrue(bookList.contains(letusc.getFormattedString()));
        assertTrue(bookList.contains(galvin.getFormattedString()));
        assertTrue(bookList.contains(internetSec.getFormattedString()));
        assertTrue(bookList.contains(fivePoint.getFormattedString()));
    }
}
