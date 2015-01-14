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

    MovieLibrary movieLibrary;
    Movie seven = new Movie("Seven", "1995", "David Fincher", "8");
    Movie darkKnight = new Movie("The Dark Knight", "2008", "Christopher Nolan", "unrated");


    @Before
    public void setUp() {
        bookLibrary = new BookLibrary();
        bookLibrary.addItem(letusc);
        bookLibrary.addItem(galvin);
        bookLibrary.addItem(internetSec);
        bookLibrary.addItem(fivePoint);

        movieLibrary = new MovieLibrary();
        movieLibrary.addItem(seven);
        movieLibrary.addItem(darkKnight);
    }

    @Test
    public void testCheckingOutABook() {
        LibraryManager manager = new LibraryManager(bookLibrary, movieLibrary);
        assertTrue(manager.checkoutBook(letusc.getTitle()));
        assertTrue(manager.isBookCheckedOut(letusc.getTitle()));
    }

    @Test
    public void testCheckingOutAMovie() {
        LibraryManager manager = new LibraryManager(bookLibrary, movieLibrary);
        assertTrue(manager.checkoutMovie(seven.getTitle()));
        assertTrue(manager.isMovieCheckedOut(seven.getTitle()));
    }

    @Test
    public void testCheckingInABook() {
        LibraryManager manager = new LibraryManager(bookLibrary, movieLibrary);
        manager.checkoutBook(letusc.getTitle());

        assertTrue(manager.checkinBook(letusc.getTitle()));
        assertFalse(manager.isBookCheckedOut(letusc.getTitle()));
    }

    @Test
    public void testCheckingInAMovie() {
        LibraryManager manager = new LibraryManager(bookLibrary, movieLibrary);
        manager.checkoutMovie(seven.getTitle());

        assertTrue(manager.checkinMovie(seven.getTitle()));
        assertFalse(manager.isMovieCheckedOut(seven.getTitle()));
    }

    @Test
    public void testListOfAvailableBooks() {
        LibraryManager manager = new LibraryManager(bookLibrary, movieLibrary);

        List<String> bookList = manager.getListOfAvailableBooks();
        assertTrue(bookList.contains(letusc.getFormattedString()));
        assertTrue(bookList.contains(galvin.getFormattedString()));
        assertTrue(bookList.contains(internetSec.getFormattedString()));
        assertTrue(bookList.contains(fivePoint.getFormattedString()));
    }

    @Test
    public void testListOfAvailableMovies() {
        LibraryManager manager = new LibraryManager(bookLibrary, movieLibrary);

        List<String> movieList = manager.getListOfAvailableMovies();
        assertTrue(movieList.contains(seven.getFormattedString()));
        assertTrue(movieList.contains(darkKnight.getFormattedString()));
    }
}
