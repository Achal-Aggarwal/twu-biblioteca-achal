package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertSame;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class LibraryTest {
    Library library;
    Book letusc = new Book("Let Us C", "Yashwant Kanetkar", "2000");
    Book galvin = new Book("Operating System", "Galvin", "2005");
    Book internetSec = new Book("Internet Security", "Ankit Fadia", "1995");
    Book fivePoint = new Book("Five Point Someone", "Chetan Bhagat", "2012");

    @Before
    public void setUp() {
        library = new Library();
    }

    @Test
    public void testAddBookShouldReturnReferenceOfAddedBook() {
        assertSame(letusc, library.addBook(letusc));
        assertTrue(library.isBookPresent(letusc.getTitle()));
    }

    @Test
    public void testAddBookShouldReturnNullIfAddedBookIsAlreadyPresent() {
        library.addBook(letusc);
        assertNull(library.addBook(letusc));
    }

    @Test
    public void testListOfBooks() {
        library.addBook(letusc);
        library.addBook(galvin);
        library.addBook(internetSec);

        List<String> bookList = library.getListOfAvailableBooks();
        assertTrue(bookList.contains(letusc.getFormattedString()));
        assertTrue(bookList.contains(galvin.getFormattedString()));
        assertTrue(bookList.contains(internetSec.getFormattedString()));
    }

    @Test
    public void testRemoveBookShouldRemoveBookFromLibrary(){
        library.addBook(letusc);
        library.removeBook(letusc.getTitle());
        assertFalse(library.isBookPresent(letusc.getTitle()));
    }

    @Test
    public void testRemoveBookShouldReturnReferenceOfRemovedBook() {
        library.addBook(letusc);
        assertSame(letusc, library.removeBook(letusc.getTitle()));
    }

    @Test
    public void testRemoveBookShouldReturnNullIfBookIsNotPresent() {
        assertNull(library.removeBook(letusc.getTitle()));
    }
}
