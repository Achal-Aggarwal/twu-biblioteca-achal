package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TerminalViewTest {
    TerminalView terminal;
    ByteArrayOutputStream output;
    Book letusc = new Book("Let Us C", "Yashwant Kanetkar", "2000");
    Book galvin = new Book("Operating System", "Galvin", "2005");
    Book internetSec = new Book("Internet Security", "Ankit Fadia", "1995");
    Book fivePoint = new Book("Five Point Someone", "Chetan Bhagat", "2012");

    @Before
    public void setUp() throws UnsupportedEncodingException {
        BibliotecaApp app = new BibliotecaApp();
        app.addBook(letusc);
        app.addBook(galvin);
        app.addBook(internetSec);
        app.addBook(fivePoint);

        terminal = new TerminalView(app);
        output = new ByteArrayOutputStream();
        terminal.runApplication(new PrintStream(output, true, "UTF-8"));
    }

    @Test
    public void testRenderedWelcomeMessageAndListOfBooks() {
        String welcomeMessage = "Welcome and thank you for taking time to visit Biblioteca.\n";
        String listOfBooks = "Sr. \tBook\tAuthor\tPublicationDate\n";
        listOfBooks += "1. \t" + letusc + "\n";
        listOfBooks += "2. \t" + galvin + "\n";
        listOfBooks += "3. \t" + internetSec + "\n";
        listOfBooks += "4. \t" + fivePoint + "\n";
        assertEquals(welcomeMessage + listOfBooks, output.toString());
    }
}
