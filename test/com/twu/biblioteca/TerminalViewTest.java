package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class TerminalViewTest {
    TerminalView terminal;
    ByteArrayOutputStream output;
    BibliotecaApp app;
    Book letusc = new Book("Let Us C", "Yashwant Kanetkar", "2000");
    Book galvin = new Book("Operating System", "Galvin", "2005");
    Book internetSec = new Book("Internet Security", "Ankit Fadia", "1995");
    Book fivePoint = new Book("Five Point Someone", "Chetan Bhagat", "2012");

    @Before
    public void setUp() {
        app = new BibliotecaApp();
        app.addBook(letusc);
        app.addBook(galvin);
        app.addBook(internetSec);
        app.addBook(fivePoint);
    }

    private void runApplicationWithInput(String input){
        output = new ByteArrayOutputStream();
        terminal = new TerminalView(app, new PrintStream(output),
                new ByteArrayInputStream(input.getBytes()));
        terminal.runApplication();
    }

    @Test
    public void testRenderedWelcomeMessageAndMenu() {
        runApplicationWithInput("0");
        String welcomeMessage = "Welcome and thank you for taking time to visit Biblioteca.\n";
        String menu = "1. \tList Books\n";
        assertEquals(welcomeMessage + menu, output.toString());
    }
    @Test
    public void testRenderedListOfBooksView() {
        runApplicationWithInput("1");
        String viewTitle = "List of books available.\n";
        String listOfBooks = "Sr. \tBook\tAuthor\tPublicationDate\n";
        listOfBooks += "1. \t" + letusc + "\n";
        listOfBooks += "2. \t" + galvin + "\n";
        listOfBooks += "3. \t" + internetSec + "\n";
        listOfBooks += "4. \t" + fivePoint + "\n";
        assertTrue(output.toString().contains(viewTitle + listOfBooks));
    }
}
