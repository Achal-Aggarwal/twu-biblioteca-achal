package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertTrue;

public class ListOfAvailableBooksControllerTest {

    ItemCollection bookLibrary;
    Book letusc = new Book("Let Us C", "Yashwant Kanetkar", "2000");
    Book galvin = new Book("Operating System", "Galvin", "2005");
    Book internetSec = new Book("Internet Security", "Ankit Fadia", "1995");
    Book fivePoint = new Book("Five Point Someone", "Chetan Bhagat", "2012");

    @Before
    public void setUp() {
        bookLibrary = new ItemCollection();
        bookLibrary.addItem(letusc);
        bookLibrary.addItem(galvin);
        bookLibrary.addItem(internetSec);
        bookLibrary.addItem(fivePoint);
    }

    private String formatBookInfo(Book book){
        String string = book.getTitle() + "|\t|";
        string += book.getAuthor() + "|\t|";
        string += book.getPublicationDate() + "|";

        return string + "\n";
    }

    @Test
    public void shouldPrintListOfAvailableBooks(){
        String input = "\n";
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        ListOfAvailableBooksController listOfAvailableBooksVC =
                new ListOfAvailableBooksController(
                        new InputOutputManger(
                                new ByteArrayInputStream(input.getBytes()),
                                new PrintStream(output)
                        ), new Library(bookLibrary, new ItemCollection())
                );

        listOfAvailableBooksVC.execute();

        String viewTitle = "List of available books.\n";
        String listOfBooks = "";
        listOfBooks += formatBookInfo(fivePoint);
        listOfBooks += formatBookInfo(letusc);
        listOfBooks += formatBookInfo(galvin);
        listOfBooks += formatBookInfo(internetSec);
        assertTrue(output.toString().contains(viewTitle + listOfBooks));
    }
}
