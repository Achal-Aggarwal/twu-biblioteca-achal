package com.twu.biblioteca;


import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BibliotecaTest {

    @Test
    public void testWelcomeMessage() {
        BibliotecaApp app = new BibliotecaApp();
        assertEquals("Welcome and thank you for taking time to visit Biblioteca.", app.welcomeMessage());
    }

    @Test
    public void testAddBook() {
        BibliotecaApp app = new BibliotecaApp();
        Book letusc = new Book("Let Us C");
        app.addBook(letusc);
        assertTrue(app.isPresent(letusc));
    }

    @Test
    public void testListOfBooks() {
        BibliotecaApp app = new BibliotecaApp();
        Book letusc = new Book("Let Us C");
        app.addBook(letusc);
        Book galvin = new Book("Galvin");
        app.addBook(galvin);
        Book internetSec = new Book("Internet Security");
        app.addBook(internetSec);
        Book fivePoint = new Book("Five Point Someone");
        app.addBook(fivePoint);

        List<Book> bookList = app.getListOfBooks();
        assertTrue(bookList.contains(letusc));
        assertTrue(bookList.contains(galvin));
        assertTrue(bookList.contains(internetSec));
        assertTrue(bookList.contains(fivePoint));
    }
}
