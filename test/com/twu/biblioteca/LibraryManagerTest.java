package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertSame;
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

    User achal = new User("000-0000", "achal");

    LibraryManager manager;

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

        manager = new LibraryManager(bookLibrary, movieLibrary);
    }

    @Test
    public void testCheckingOutABook() {
        assertTrue(manager.checkoutBook(letusc.getTitle()));
        assertTrue(manager.isBookCheckedOut(letusc.getTitle()));
    }

    @Test
    public void testCheckingOutAMovie() {
        assertTrue(manager.checkoutMovie(seven.getTitle()));
        assertTrue(manager.isMovieCheckedOut(seven.getTitle()));
    }

    @Test
    public void testCheckingInABook() {
        manager.checkoutBook(letusc.getTitle());

        assertTrue(manager.checkinBook(letusc.getTitle()));
        assertFalse(manager.isBookCheckedOut(letusc.getTitle()));
    }

    @Test
    public void testCheckingInAMovie() {
        manager.checkoutMovie(seven.getTitle());

        assertTrue(manager.checkinMovie(seven.getTitle()));
        assertFalse(manager.isMovieCheckedOut(seven.getTitle()));
    }

    @Test
    public void testListOfAvailableBooks() {

        List<String> bookList = manager.getListOfAvailableBooks();
        assertTrue(bookList.contains(letusc.getFormattedString()));
        assertTrue(bookList.contains(galvin.getFormattedString()));
        assertTrue(bookList.contains(internetSec.getFormattedString()));
        assertTrue(bookList.contains(fivePoint.getFormattedString()));
    }

    @Test
    public void testListOfAvailableMovies() {

        List<String> movieList = manager.getListOfAvailableMovies();
        assertTrue(movieList.contains(seven.getFormattedString()));
        assertTrue(movieList.contains(darkKnight.getFormattedString()));
    }

    @Test
    public void testRegistrationOfUser(){
        manager.registerUser(achal);
        assertTrue(manager.isUserPresent("000-0000"));
    }

    @Test
    public void testValidationOfValidUser(){
        manager.registerUser(achal);
        assertTrue(manager.isUserValid("000-0000", "achal"));
    }

    @Test
    public void testValidationOfInvalidUser(){
        manager.registerUser(achal);
        assertFalse(manager.isUserValid("000-0000", "asd"));
    }

    @Test
    public void testSettingOfRegisterUserAsCurrentUser(){
        manager.registerUser(achal);
        assertTrue(manager.setCurrentUser(achal));
    }

    @Test
    public void testSettingOfUnregisterUserAsCurrentUser(){
        assertFalse(manager.setCurrentUser(achal));
    }

    @Test
    public void testTrackingOfUserOnCheckoutOfABook(){
        manager.registerUser(achal);
        manager.setCurrentUser(achal);
        manager.checkoutBook(letusc.getTitle());
        assertSame(achal, letusc.getBorrower());
    }

    @Test
    public void testTrackingOfUserOnCheckoutOfAMovie(){
        manager.registerUser(achal);
        manager.setCurrentUser(achal);
        manager.checkoutMovie(seven.getTitle());
        assertSame(achal, seven.getBorrower());
    }

    @Test
    public void testValidationOfValidUserOnCheckinOfAMovie(){
        manager.registerUser(achal);
        manager.setCurrentUser(achal);
        manager.checkoutBook(letusc.getTitle());
        assertTrue(manager.checkinBook(letusc.getTitle()));
    }

    @Test
    public void testValidationOfInvalidUserOnCheckinOfAMovie(){
        User abhishek = new User("000-0000", "abhishek");
        manager.registerUser(achal);
        manager.registerUser(abhishek);

        manager.setCurrentUser(achal);
        manager.checkoutBook(letusc.getTitle());

        manager.setCurrentUser(abhishek);
        assertFalse(manager.checkinBook(letusc.getTitle()));
    }
}
