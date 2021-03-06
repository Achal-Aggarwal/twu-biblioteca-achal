package com.twu.biblioteca;

import com.sun.deploy.util.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckinBookControllerTest {
    LibraryManager manager;
    Book letusc = new Book("Let Us C", "Yashwant Kanetkar", "2000");
    Book galvin = new Book("Operating System", "Galvin", "2005");
    Book internetSec = new Book("Internet Security", "Ankit Fadia", "1995");
    Book fivePoint = new Book("Five Point Someone", "Chetan Bhagat", "2012");
    private ByteArrayOutputStream output;
    private InputOutputManger io;
    private CheckinBookController checkinBookVC;
    User user = new User("000-0000", "achal", "", "", "");

    @Before
    public void setUp() {
        BookLibrary bookLibrary = new BookLibrary();
        bookLibrary.addItem(letusc);
        bookLibrary.addItem(galvin);
        bookLibrary.addItem(internetSec);
        bookLibrary.addItem(fivePoint);
        manager = new LibraryManager(bookLibrary, new MovieLibrary());
        manager.registerUser(user);
    }

    private void runTestCaseWithInput(List<String> inputs) {
        String input = StringUtils.join(inputs, "\n");
        output = new ByteArrayOutputStream();
        io = new InputOutputManger(
                new ByteArrayInputStream(input.getBytes()),
                new PrintStream(output)
        );
        checkinBookVC = new CheckinBookController(manager, io);
    }

    @Test
    public void shouldCheckinLetUsCBook(){
        runTestCaseWithInput(Arrays.asList(user.getLibraryNumber(), user.getPassword(), letusc.getTitle()));
        manager.checkoutBook(letusc.getTitle());
        letusc.setBorrower(user);
        checkinBookVC.execute();
        assertFalse(manager.isBookCheckedOut(letusc.getTitle()));
    }

    @Test
    public void shouldNotCheckinLetUsCBookIfUserIsInvalid(){
        manager.setCurrentUser(user.getLibraryNumber());
        manager.checkoutBook(letusc.getTitle());
        manager.setCurrentUser(null);

        runTestCaseWithInput(Arrays.asList(user.getLibraryNumber(), "asd", letusc.getTitle()));
        checkinBookVC.execute();
        assertTrue(manager.isBookCheckedOut(letusc.getTitle()));
    }

    @Test
    public void shouldPrintSuccessfulMessageOnSuccessfulCheckin(){
        manager.setCurrentUser(user.getLibraryNumber());
        runTestCaseWithInput(Arrays.asList(letusc.getTitle()));
        manager.checkoutBook(letusc.getTitle());
        letusc.setBorrower(user);
        checkinBookVC.execute();
        assertEquals("Thank you for returning the book.\n", output.toString());
    }

    @Test
    public void shouldPrintUnSuccessfulMessageOnUnSuccessfulCheckinIfBookCheckedinAlready(){
        manager.setCurrentUser(user.getLibraryNumber());
        runTestCaseWithInput(Arrays.asList(letusc.getTitle()));
        checkinBookVC.execute();
        assertEquals("That is not a valid book to return.\n", output.toString());
    }

    @Test
    public void shouldPrintUnSuccessfulMessageOnUnSuccessfulCheckinIfBookDoesntExist(){
        manager.setCurrentUser(user.getLibraryNumber());
        runTestCaseWithInput(Arrays.asList("Programing C"));
        checkinBookVC.execute();
        assertEquals("That is not a valid book to return.\n", output.toString());
    }
}
