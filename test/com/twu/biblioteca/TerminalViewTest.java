package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;

public class TerminalViewTest {
    TerminalView terminal;
    ByteArrayOutputStream output;
    String letusc = "Let Us C";
    String galvin = "Galvin";
    String internetSec = "Internet Security";
    String fivePoint = "Five Point Someone";

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
    public void testRenderedWelcomeMessage() throws UnsupportedEncodingException {
        String welcomeMessage = "Welcome and thank you for taking time to visit Biblioteca.\n";
        assertEquals(welcomeMessage, output.toString());
    }

    @Test
    public void testRenderedListOfBooks() {
        String welcomeMessage = "Welcome and thank you for taking time to visit Biblioteca.\n";
        String listOfBooks = "Sr. \tBook\n";
        listOfBooks += "1. \t" + letusc + "\n";
        listOfBooks += "2. \t" + galvin + "\n";
        listOfBooks += "3. \t" + internetSec + "\n";
        listOfBooks += "4. \t" + fivePoint + "\n";
        assertEquals(welcomeMessage + listOfBooks, output.toString());
    }
}
