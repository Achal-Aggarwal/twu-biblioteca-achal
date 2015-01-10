package com.twu.biblioteca;


import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BibliotecaTest {
    Book letusc = new Book("Let Us C", "Yashwant Kanetkar", "2000");
    Book galvin = new Book("Operating System", "Galvin", "2005");
    Book internetSec = new Book("Internet Security", "Ankit Fadia", "1995");
    Book fivePoint = new Book("Five Point Someone", "Chetan Bhagat", "2012");

    @Test
    public void testWelcomeMessage() {
        BibliotecaApp app = new BibliotecaApp();
        assertEquals("Welcome and thank you for taking time to visit Biblioteca.", app.welcomeMessage());
    }

    @Test
    public void testAddBook() {
        BibliotecaApp app = new BibliotecaApp();
        app.addBook(letusc);
        assertTrue(app.isPresent(letusc));
    }

    @Test
    public void testListOfBooks() {
        BibliotecaApp app = new BibliotecaApp();
        app.addBook(letusc);
        app.addBook(galvin);
        app.addBook(internetSec);
        app.addBook(fivePoint);

        List<Book> bookList = app.getListOfBooks();
        assertTrue(bookList.contains(letusc));
        assertTrue(bookList.contains(galvin));
        assertTrue(bookList.contains(internetSec));
        assertTrue(bookList.contains(fivePoint));
    }
}
