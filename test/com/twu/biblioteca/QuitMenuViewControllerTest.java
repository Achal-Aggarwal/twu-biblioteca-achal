package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertFalse;

/**
 * Created by achalaggarwal on 1/13/15.
 */
public class QuitMenuViewControllerTest {
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
    public void shouldDisplayQuitMessage(){
        String input = "\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        QuitMenuViewController quitVC =
                new QuitMenuViewController(library,
                        new InputOutputManger(
                            new ByteArrayInputStream(input.getBytes()),
                            new PrintStream(output)
                        )
                );

        assertFalse(quitVC.execute());
        //assertEquals("Do not forget to return issues books.\n", output.toString());
    }
}
