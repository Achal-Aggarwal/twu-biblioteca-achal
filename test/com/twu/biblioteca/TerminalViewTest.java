package com.twu.biblioteca;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;

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
        runApplicationWithInput("3");
        String welcomeMessage = "Welcome and thank you for taking time to visit Biblioteca.\n";
        String menu = "--Menu--\n";
        menu += "1. \tList Books\n";
        assertTrue(output.toString().startsWith(welcomeMessage + menu));
    }

    @Test
    public void testRenderedListOfBooksView() {
        runApplicationWithInput("1\n3");
        String viewTitle = "List of books available.\n";
        String listOfBooks = "Sr. \tBook\tAuthor\tPublicationDate\n";
        listOfBooks += "1. \t" + fivePoint + "\n";
        listOfBooks += "2. \t" + letusc + "\n";
        listOfBooks += "3. \t" + galvin + "\n";
        listOfBooks += "4. \t" + internetSec + "\n";
        assertTrue(output.toString().contains(viewTitle + listOfBooks));
    }

    @Test
    public void testRenderedInvalidMessageOnSelectingInvalidOption(){
        runApplicationWithInput("a\n3");
        assertTrue(output.toString().contains("Select a valid option!\n"));
    }

    @Test
    public void testRenderedMenuAfterRenderingInvalidOptionMessage(){
        runApplicationWithInput("q\n3");
        String outputString = output.toString();
        String invalidOptionMessage = "Select a valid option!\n";
        int offset = outputString.lastIndexOf(invalidOptionMessage);

        assertNotEquals(-1, offset);

        outputString = outputString.substring(offset + invalidOptionMessage.length());
        String menu = "--Menu--\n";
        menu += "1. \tList Books\n";
        menu += "2. \tCheckout Book\n";
        menu += "3. \tQuit\n";

        assertTrue(outputString.contains(menu));
    }

    @Test
    public void testCheckingOutOfABook() {
        runApplicationWithInput("2\n" + galvin.getTitle() + "\n3");
        assertTrue(galvin.isCheckedOut());
    }

    @Test
    public void testListOfBooksViewAfterCheckingOutABook() {
        runApplicationWithInput("1\n2\n" + galvin.getTitle() + "\n1\n3");
        String outputString = output.toString();

        String viewTitle = "List of books available.\n";
        String listOfBooks = "Sr. \tBook\tAuthor\tPublicationDate\n";
        listOfBooks += "1. \t" + fivePoint + "\n";
        listOfBooks += "2. \t" + letusc + "\n";
        listOfBooks += "3. \t" + galvin + "\n";
        listOfBooks += "4. \t" + internetSec + "\n";

        int offset = outputString.lastIndexOf(viewTitle + listOfBooks);

        assertNotEquals(-1, offset);

        outputString = outputString.substring(offset + (viewTitle + listOfBooks).length());

        listOfBooks = "Sr. \tBook\tAuthor\tPublicationDate\n";
        listOfBooks += "1. \t" + fivePoint + "\n";
        listOfBooks += "2. \t" + letusc + "\n";
        listOfBooks += "3. \t" + internetSec + "\n";
        assertTrue(outputString.contains(viewTitle + listOfBooks));
    }
}
