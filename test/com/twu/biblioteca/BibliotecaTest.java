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
        String letusc = "Let Us C";
        app.addBook(letusc);
        assertTrue(app.isPresent(letusc));
    }

    @Test
    public void testListOfBooks() {
        BibliotecaApp app = new BibliotecaApp();
        String letusc = "Let Us C";
        app.addBook(letusc);
        String galvin = "Galvin";
        app.addBook(galvin);
        String internetSec = "Internet Security";
        app.addBook(internetSec);
        String fivePoint = "Five Point Someone";
        app.addBook(fivePoint);

        List<String> bookList = app.getListOfBooks();
        assertTrue(bookList.contains(letusc));
        assertTrue(bookList.contains(galvin));
        assertTrue(bookList.contains(internetSec));
        assertTrue(bookList.contains(fivePoint));
    }
}
