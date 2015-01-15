package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;

public class BibliotecaAppTest {
    BilbliotecaApp application;
    ByteArrayOutputStream output;
    LibraryManager manager;
    Book letusc = new Book("Let Us C", "Yashwant Kanetkar", "2000");
    Book galvin = new Book("Operating System", "Galvin", "2005");
    Book internetSec = new Book("Internet Security", "Ankit Fadia", "1995");
    Book fivePoint = new Book("Five Point Someone", "Chetan Bhagat", "2012");
    String quitAction = "11\n";

    @Before
    public void setUp() {
        BookLibrary bookLibrary;
        bookLibrary = new BookLibrary();
        bookLibrary.addItem(letusc);
        bookLibrary.addItem(galvin);
        bookLibrary.addItem(internetSec);
        bookLibrary.addItem(fivePoint);
        manager = new LibraryManager(bookLibrary, new MovieLibrary());
        manager.registerUser(new User("000-0000","achal", "", "", ""));
        manager.setCurrentUser("000-0000");
    }

    private void runApplicationWithInput(String input){
        output = new ByteArrayOutputStream();
        application = new BilbliotecaApp(manager,
                new InputOutputManger(
                        new ByteArrayInputStream(input.getBytes()),
                        new PrintStream(output)
                    )
                );
        application.runApplication();
    }

    @Test
    public void testRenderedWelcomeMessage() {
        runApplicationWithInput(quitAction);
        String welcomeMessage = "Welcome and thank you for taking time to visit Biblioteca.\n";
        String menu = "Main Menu.\n";
        assertTrue(output.toString().startsWith(welcomeMessage + menu));
    }

    @Test
    public void testRenderedListOfBooksView() {
        runApplicationWithInput("1\n" + quitAction);
        String viewTitle = "List of available books.\n";
        String listOfBooks = "";
        listOfBooks += "1. \t" + fivePoint.getFormattedString() + "\n";
        listOfBooks += "2. \t" + letusc.getFormattedString() + "\n";
        listOfBooks += "3. \t" + galvin.getFormattedString() + "\n";
        listOfBooks += "4. \t" + internetSec.getFormattedString() + "\n";
        assertTrue(output.toString().contains(viewTitle + listOfBooks));
    }

    @Test
    public void testRenderedInvalidMessageOnSelectingInvalidOption(){
        runApplicationWithInput("a\n" + quitAction);
        assertTrue(output.toString().contains("Select a valid option!\n"));
    }

    @Test
    public void testRenderedMenuAfterRenderingInvalidOptionMessage(){
        runApplicationWithInput("q\n" + quitAction);
        String outputString = output.toString();
        String invalidOptionMessage = "Select a valid option!\n";
        int offset = outputString.lastIndexOf(invalidOptionMessage);

        assertNotEquals(-1, offset);

        outputString = outputString.substring(offset + invalidOptionMessage.length());
        String menu = "Main Menu.\n";

        assertTrue(outputString.startsWith(menu));
    }

    @Test
    public void testCheckingOutOfABook() {
        runApplicationWithInput("2\n" + galvin.getTitle() + "\n" + quitAction);
        assertTrue(manager.isBookCheckedOut(galvin.getTitle()));
    }

    @Test
    public void testListOfBooksViewAfterCheckingOutABook() {
        runApplicationWithInput("1\n2\n" + galvin.getTitle() + "\n1\n" + quitAction);
        String outputString = output.toString();

        String viewTitle = "List of available books.\n";
        String listOfBooks = "";
        listOfBooks += "1. \t" + fivePoint.getFormattedString() + "\n";
        listOfBooks += "2. \t" + letusc.getFormattedString() + "\n";
        listOfBooks += "3. \t" + galvin.getFormattedString() + "\n";
        listOfBooks += "4. \t" + internetSec.getFormattedString() + "\n";

        int offset = outputString.lastIndexOf(viewTitle + listOfBooks);

        assertNotEquals(-1, offset);

        outputString = outputString.substring(offset + (viewTitle + listOfBooks).length());

        listOfBooks = "";
        listOfBooks += "1. \t" + fivePoint.getFormattedString() + "\n";
        listOfBooks += "2. \t" + letusc.getFormattedString() + "\n";
        listOfBooks += "3. \t" + internetSec.getFormattedString() + "\n";
        assertTrue(outputString.contains(viewTitle + listOfBooks));
    }

    @Test
    public void testSuccessMessageAfterCheckingOutABook() {
        runApplicationWithInput("2\n" + galvin.getTitle() + "\n" + quitAction);
        assertTrue(output.toString().contains("Thank you! Enjoy the book\n"));
    }

    @Test
    public void testUnsuccessfulMessageAfterCheckingOutABookThatDoesntExist() {
        runApplicationWithInput("2\nFooBar\n" + quitAction);
        assertTrue(output.toString().contains("That book is not available.\n"));
    }

    @Test
    public void testUnsuccessfulMessageAfterCheckingOutABookThatIsCheckedOut() {
        runApplicationWithInput("2\n" + galvin.getTitle() + "\n2\n" + galvin.getTitle() +"\n" + quitAction);
        assertTrue(output.toString().contains("That book is not available.\n"));
    }

    @Test
    public void testReturnOfABook() {
        runApplicationWithInput("2\n" + galvin.getTitle() + "\n3\n" + galvin.getTitle() + "\n" + quitAction);
        assertFalse(manager.isBookCheckedOut(galvin.getTitle()));
    }

    @Test
    public void testListOfBooksViewAfterReturningABook() {
        runApplicationWithInput("2\n" + galvin.getTitle() + "\n1\n3\n"+galvin.getTitle()+"\n1\n" + quitAction);
        String outputString = output.toString();

        String viewTitle = "List of available books.\n";
        String listOfBooks = "";
        listOfBooks += "1. \t" + fivePoint.getFormattedString() + "\n";
        listOfBooks += "2. \t" + letusc.getFormattedString() + "\n";
        listOfBooks += "3. \t" + internetSec.getFormattedString() + "\n";

        int offset = outputString.lastIndexOf(viewTitle + listOfBooks);

        assertNotEquals(-1, offset);

        outputString = outputString.substring(offset + (viewTitle + listOfBooks).length());

        listOfBooks = "";
        listOfBooks += "1. \t" + fivePoint.getFormattedString() + "\n";
        listOfBooks += "2. \t" + letusc.getFormattedString() + "\n";
        listOfBooks += "3. \t" + galvin.getFormattedString() + "\n";
        listOfBooks += "4. \t" + internetSec.getFormattedString() + "\n";
        assertTrue(outputString.contains(viewTitle + listOfBooks));
    }

    @Test
    public void testSuccessMessageAfterReturningABook() {
        runApplicationWithInput("2\n" + galvin.getTitle() + "\n3\n"+ galvin.getTitle() +"\n" + quitAction);
        assertTrue(output.toString().contains("Thank you for returning the book.\n"));
    }

    @Test
    public void testUnSuccessfulMessageAfterReturningABookThatDoesntExist() {
        runApplicationWithInput("\n3\nFoobar\n" + quitAction);
        assertTrue(output.toString().contains("That is not a valid book to return.\n"));
    }

    @Test
    public void testUnSuccessfulMessageAfterReturningABookThatIsCheckedInAlready() {
        runApplicationWithInput("\n3\n"+ galvin.getTitle() +"\n" + quitAction);
        assertTrue(output.toString().contains("That is not a valid book to return.\n"));
    }
}
