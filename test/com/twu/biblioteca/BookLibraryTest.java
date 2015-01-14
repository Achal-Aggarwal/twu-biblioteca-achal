package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertSame;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class BookLibraryTest {
    BookLibrary bookLibrary;
    Book letusc = new Book("Let Us C", "Yashwant Kanetkar", "2000");
    Book galvin = new Book("Operating System", "Galvin", "2005");
    Book internetSec = new Book("Internet Security", "Ankit Fadia", "1995");
    Book fivePoint = new Book("Five Point Someone", "Chetan Bhagat", "2012");

    @Before
    public void setUp() {
        bookLibrary = new BookLibrary();
    }

    @Test
    public void testAddBookShouldReturnReferenceOfAddedBook() {
        assertSame(letusc, bookLibrary.addBook(letusc));
        assertTrue(bookLibrary.isBookPresent(letusc.getTitle()));
    }

    @Test
    public void testAddBookShouldReturnNullIfAddedBookIsAlreadyPresent() {
        bookLibrary.addBook(letusc);
        assertNull(bookLibrary.addBook(letusc));
    }

    @Test
    public void testListOfBooks() {
        bookLibrary.addBook(letusc);
        bookLibrary.addBook(galvin);
        bookLibrary.addBook(internetSec);

        List<String> bookList = bookLibrary.getListOfAvailableBooks();
        assertTrue(bookList.contains(letusc.getFormattedString()));
        assertTrue(bookList.contains(galvin.getFormattedString()));
        assertTrue(bookList.contains(internetSec.getFormattedString()));
    }

    @Test
    public void testRemoveBookShouldRemoveBookFromLibrary(){
        bookLibrary.addBook(letusc);
        bookLibrary.removeBook(letusc.getTitle());
        assertFalse(bookLibrary.isBookPresent(letusc.getTitle()));
    }

    @Test
    public void testRemoveBookShouldReturnReferenceOfRemovedBook() {
        bookLibrary.addBook(letusc);
        assertSame(letusc, bookLibrary.removeBook(letusc.getTitle()));
    }

    @Test
    public void testRemoveBookShouldReturnNullIfBookIsNotPresent() {
        assertNull(bookLibrary.removeBook(letusc.getTitle()));
    }
}
