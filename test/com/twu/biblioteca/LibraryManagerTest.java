package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
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

    LibraryManager manager;

    SessionManager sessionManager;
    User achal = new User("000-0000", "achal", "Achal", "achal@gmail.com", "1234567890");
    User abhishek = new User("000-0001", "abhishek", "Abhishek", "abhishek@gmail.com", "0987654321");

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
        sessionManager = SessionManager.getSession();
    }

    @After
    public void tearDown() {
        SessionManager.clearSession();
    }

    @Test
    public void testCheckingOutABook() {
        sessionManager.registerUser(achal);
        sessionManager.login(achal.getLibraryNumber());
        assertTrue(manager.checkoutBook(letusc.getTitle()));
        assertTrue(manager.isBookCheckedOut(letusc.getTitle()));
    }

    @Test
    public void testCheckingOutAMovie() {
        sessionManager.registerUser(achal);
        sessionManager.login(achal.getLibraryNumber());
        assertTrue(manager.checkoutMovie(seven.getTitle()));
        assertTrue(manager.isMovieCheckedOut(seven.getTitle()));
    }

    @Test
    public void testCheckingInABook() {
        sessionManager.registerUser(achal);
        sessionManager.login(achal.getLibraryNumber());
        manager.checkoutBook(letusc.getTitle());

        assertTrue(manager.checkinBook(letusc.getTitle()));
        assertFalse(manager.isBookCheckedOut(letusc.getTitle()));
    }

    @Test
    public void testCheckingInAMovie() {
        sessionManager.registerUser(achal);
        sessionManager.login(achal.getLibraryNumber());
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
    public void testListOfIssuedBooks() {
        sessionManager.registerUser(achal);
        sessionManager.registerUser(abhishek);

        sessionManager.login(achal.getLibraryNumber());
        manager.checkoutBook(letusc.getTitle());

        sessionManager.login(abhishek.getLibraryNumber());
        manager.checkoutBook(internetSec.getTitle());

        List<String> bookList = manager.getListOfIssuedBooks();

        assertTrue(bookList.contains(letusc.getFormattedString() + " issued by " + achal.contactInformation()));
        assertTrue(bookList.contains(internetSec.getFormattedString() + " issued by " + abhishek.contactInformation()));
        assertEquals(2, bookList.size());
    }

    @Test
    public void testListOfIssuedMovies() {
        sessionManager.registerUser(achal);
        sessionManager.login(achal.getLibraryNumber());
        manager.checkoutMovie(seven.getTitle());
        List<String> movieList = manager.getListOfIssuedMovies();
        assertTrue(movieList.contains(seven.getFormattedString() + " issued by " + achal.contactInformation()));
        assertEquals(1, movieList.size());
    }

    @Test
    public void testListOfAvailableMovies() {
        List<String> movieList = manager.getListOfAvailableMovies();
        assertTrue(movieList.contains(seven.getFormattedString()));
        assertTrue(movieList.contains(darkKnight.getFormattedString()));
    }

    @Test
    public void testRegistrationOfUser(){
        sessionManager.registerUser(achal);
        assertTrue(sessionManager.isUserPresent("000-0000"));
    }

    @Test
    public void testValidationOfValidUser(){
        sessionManager.registerUser(achal);
        assertTrue(sessionManager.validateUser("000-0000", "achal"));
    }

    @Test
    public void testValidationOfInvalidUser(){
        sessionManager.registerUser(achal);
        assertFalse(sessionManager.validateUser("000-0000", "asd"));
    }

    @Test
    public void testSettingOfRegisterUserAsCurrentUser(){
        sessionManager.registerUser(achal);
        assertTrue(sessionManager.login(achal.getLibraryNumber()));
    }

    @Test
    public void testSettingOfUnregisterUserAsCurrentUser(){
        assertFalse(sessionManager.login(achal.getLibraryNumber()));
    }

    @Test
    public void testTrackingOfUserOnCheckoutOfABook(){
        sessionManager.registerUser(achal);
        sessionManager.login(achal.getLibraryNumber());
        manager.checkoutBook(letusc.getTitle());
        assertSame(achal, letusc.getBorrower());
    }

    @Test
    public void testTrackingOfUserOnCheckoutOfAMovie(){
        sessionManager.registerUser(achal);
        sessionManager.login(achal.getLibraryNumber());
        manager.checkoutMovie(seven.getTitle());
        assertSame(achal, seven.getBorrower());
    }

    @Test
    public void testValidationOfValidUserOnCheckinOfAMovie(){
        sessionManager.registerUser(achal);
        sessionManager.login(achal.getLibraryNumber());
        manager.checkoutBook(letusc.getTitle());
        assertTrue(manager.checkinBook(letusc.getTitle()));
    }

    @Test
    public void testValidationOfInvalidUserOnCheckinOfAMovie(){
        User abhishek = new User("000-0001", "abhishek", "", "", "");
        sessionManager.registerUser(achal);
        sessionManager.registerUser(abhishek);

        sessionManager.login(achal.getLibraryNumber());
        manager.checkoutBook(letusc.getTitle());

        sessionManager.login(abhishek.getLibraryNumber());
        assertFalse(manager.checkinBook(letusc.getTitle()));
    }

    @Test
    public void testUnsettingOfCurrentUser(){
        sessionManager.registerUser(achal);
        sessionManager.login(achal.getLibraryNumber());
        sessionManager.logout();
        assertNull(sessionManager.getLoggedInUser());
    }
}
