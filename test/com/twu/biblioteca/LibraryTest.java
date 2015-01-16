package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.*;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class LibraryTest {
    ItemCollection bookCollection;
    Book letusc = new Book("Let Us C", "Yashwant Kanetkar", "2000");
    Book galvin = new Book("Operating System", "Galvin", "2005");
    Book internetSec = new Book("Internet Security", "Ankit Fadia", "1995");
    Book fivePoint = new Book("Five Point Someone", "Chetan Bhagat", "2012");

    ItemCollection movieCollection;
    Movie seven = new Movie("Seven", "1995", "David Fincher", "8");
    Movie darkKnight = new Movie("The Dark Knight", "2008", "Christopher Nolan", "unrated");

    Library library;

    SessionManager sessionManager;
    User achal = new User("000-0000", "achal", "Achal", "achal@gmail.com", "1234567890");
    User abhishek = new User("000-0001", "abhishek", "Abhishek", "abhishek@gmail.com", "0987654321");

    @Before
    public void setUp() {
        bookCollection = new ItemCollection();
        bookCollection.addItem(letusc);
        bookCollection.addItem(galvin);
        bookCollection.addItem(internetSec);
        bookCollection.addItem(fivePoint);

        movieCollection = new ItemCollection();
        movieCollection.addItem(seven);
        movieCollection.addItem(darkKnight);

        library = new Library(bookCollection, movieCollection);
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
        assertTrue(library.checkoutBook(letusc.getTitle()));
        assertSame(achal, letusc.getBorrower());
    }

    @Test
    public void testCheckingOutAMovie() {
        sessionManager.registerUser(achal);
        sessionManager.login(achal.getLibraryNumber());
        assertTrue(library.checkoutMovie(seven.getTitle()));
        assertSame(achal, seven.getBorrower());
    }

    @Test
    public void testCheckingInABook() {
        sessionManager.registerUser(achal);
        sessionManager.login(achal.getLibraryNumber());
        library.checkoutBook(letusc.getTitle());

        assertTrue(library.checkinBook(letusc.getTitle()));
        assertNull(letusc.getBorrower());
    }

    @Test
    public void testCheckingInAMovie() {
        sessionManager.registerUser(achal);
        sessionManager.login(achal.getLibraryNumber());
        library.checkoutMovie(seven.getTitle());

        assertTrue(library.checkinMovie(seven.getTitle()));
        assertNull(seven.getBorrower());
    }

    @Test
    public void testListOfAvailableBooks() {
        List<Item> bookList = library.getListOfAvailableBooks();
        assertTrue(bookList.contains(letusc));
        assertTrue(bookList.contains(galvin));
        assertTrue(bookList.contains(internetSec));
        assertTrue(bookList.contains(fivePoint));
    }

    @Test
    public void testListOfIssuedBooks() {
        sessionManager.registerUser(achal);
        sessionManager.registerUser(abhishek);

        sessionManager.login(achal.getLibraryNumber());
        library.checkoutBook(letusc.getTitle());

        sessionManager.login(abhishek.getLibraryNumber());
        library.checkoutBook(internetSec.getTitle());

        List<Issue> bookIssueList = library.getListOfIssuedBooks();

        assertSame(letusc, bookIssueList.get(0).getIssuedItem());
        assertSame(achal, bookIssueList.get(0).getIssuer());

        assertSame(internetSec, bookIssueList.get(1).getIssuedItem());
        assertSame(abhishek, bookIssueList.get(1).getIssuer());

        assertEquals(2, bookIssueList.size());
    }

    @Test
    public void testListOfIssuedMovies() {
        sessionManager.registerUser(achal);
        sessionManager.login(achal.getLibraryNumber());
        library.checkoutMovie(seven.getTitle());
        List<Issue> movieIssueList = library.getListOfIssuedMovies();

        assertSame(seven, movieIssueList.get(0).getIssuedItem());
        assertSame(achal, movieIssueList.get(0).getIssuer());

        assertEquals(1, movieIssueList.size());
    }

    @Test
    public void testListOfAvailableMovies() {
        List<Item> movieList = library.getListOfAvailableMovies();
        assertTrue(movieList.contains(seven));
        assertTrue(movieList.contains(darkKnight));
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
        library.checkoutBook(letusc.getTitle());
        assertSame(achal, letusc.getBorrower());
    }

    @Test
    public void testTrackingOfUserOnCheckoutOfAMovie(){
        sessionManager.registerUser(achal);
        sessionManager.login(achal.getLibraryNumber());
        library.checkoutMovie(seven.getTitle());
        assertSame(achal, seven.getBorrower());
    }

    @Test
    public void testValidationOfValidUserOnCheckinOfAMovie(){
        sessionManager.registerUser(achal);
        sessionManager.login(achal.getLibraryNumber());
        library.checkoutBook(letusc.getTitle());
        assertTrue(library.checkinBook(letusc.getTitle()));
    }

    @Test
    public void testValidationOfInvalidUserOnCheckinOfAMovie(){
        User abhishek = new User("000-0001", "abhishek", "", "", "");
        sessionManager.registerUser(achal);
        sessionManager.registerUser(abhishek);

        sessionManager.login(achal.getLibraryNumber());
        library.checkoutBook(letusc.getTitle());

        sessionManager.login(abhishek.getLibraryNumber());
        assertFalse(library.checkinBook(letusc.getTitle()));
    }

    @Test
    public void testUnsettingOfCurrentUser(){
        sessionManager.registerUser(achal);
        sessionManager.login(achal.getLibraryNumber());
        sessionManager.logout();
        assertNull(sessionManager.getLoggedInUser());
    }
}
