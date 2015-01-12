package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
        library.addBook(letusc);
        library.addBook(galvin);
        library.addBook(internetSec);
        library.addBook(fivePoint);
    }

    @Test
    public void testAddBook() {
        assertTrue(library.isPresent(letusc));
    }

    @Test
    public void testListOfBooks() {
        List<String> bookList = library.getListOfAvailableBooks();
        assertTrue(bookList.contains(letusc.getFormattedString()));
        assertTrue(bookList.contains(galvin.getFormattedString()));
        assertTrue(bookList.contains(internetSec.getFormattedString()));
        assertTrue(bookList.contains(fivePoint.getFormattedString()));
    }

    @Test
    public void testCheckoutOfABook(){
        assertFalse(library.isBookCheckedOut(letusc.getTitle()));
        assertTrue(library.checkout(letusc.getTitle()));
        assertTrue(library.isBookCheckedOut(letusc.getTitle()));
    }

    @Test
    public void testCheckoutOfABookThatDoesntExist(){
        assertTrue(library.isBookCheckedOut("Foobar"));
        assertFalse(library.checkout("Foobar"));
    }

    @Test
    public void testCheckoutOfABookThatIsCheckedout(){
        assertFalse(library.isBookCheckedOut(letusc.getTitle()));
        assertTrue(library.checkout(letusc.getTitle()));
        assertFalse(library.checkout(letusc.getTitle()));
    }

    @Test
    public void testListOfBooksAfterCheckingOutABook(){
        List<String> bookList = library.getListOfAvailableBooks();
        assertTrue(bookList.contains(galvin.getFormattedString()));
        assertTrue(library.checkout(galvin.getTitle()));
        bookList = library.getListOfAvailableBooks();
        assertFalse(bookList.contains(galvin.getFormattedString()));
    }

    @Test
    public void testCheckinOfABook(){
        library.isBookCheckedOut(letusc.getTitle());
        library.checkout(letusc.getTitle());
        assertTrue(library.isBookCheckedOut(letusc.getTitle()));
        assertTrue(library.checkin(letusc.getTitle()));
        assertFalse(library.isBookCheckedOut(letusc.getTitle()));
    }

    @Test
    public void testCheckinOfABookThatDoesntExist(){
        assertTrue(library.isBookCheckedOut("Foobar"));
        assertFalse(library.checkin("Foobar"));
    }

    @Test
    public void testCheckinOfABookThatIsCheckedinAlready(){
        assertFalse(library.isBookCheckedOut(letusc.getTitle()));
        assertFalse(library.checkin(letusc.getTitle()));
    }
}
