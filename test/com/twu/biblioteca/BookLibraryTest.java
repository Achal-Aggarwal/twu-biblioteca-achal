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

    @Before
    public void setUp() {
        bookLibrary = new BookLibrary();
    }

    @Test
    public void testAddBookShouldReturnReferenceOfAddedBook() {
        assertSame(letusc, bookLibrary.addItem(letusc));
        assertTrue(bookLibrary.isItemPresent(letusc.getTitle()));
    }

    @Test
    public void testAddBookShouldReturnNullIfAddedBookIsAlreadyPresent() {
        bookLibrary.addItem(letusc);
        assertNull(bookLibrary.addItem(letusc));
    }

    @Test
    public void testListOfBooks() {
        bookLibrary.addItem(letusc);
        bookLibrary.addItem(galvin);
        bookLibrary.addItem(internetSec);

        List<String> bookList = bookLibrary.getListOfAvailableItems();
        assertTrue(bookList.contains(letusc.getFormattedString()));
        assertTrue(bookList.contains(galvin.getFormattedString()));
        assertTrue(bookList.contains(internetSec.getFormattedString()));
    }

    @Test
    public void testRemoveBookShouldRemoveBookFromLibrary(){
        bookLibrary.addItem(letusc);
        bookLibrary.removeItem(letusc.getTitle());
        assertFalse(bookLibrary.isItemPresent(letusc.getTitle()));
    }

    @Test
    public void testRemoveBookShouldReturnReferenceOfRemovedBook() {
        bookLibrary.addItem(letusc);
        assertSame(letusc, bookLibrary.removeItem(letusc.getTitle()));
    }

    @Test
    public void testRemoveBookShouldReturnNullIfBookIsNotPresent() {
        assertNull(bookLibrary.removeItem(letusc.getTitle()));
    }
}
