package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertSame;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class ItemCollectionTest {
    ItemCollection bookCollection;
    Book letusc = new Book("Let Us C", "Yashwant Kanetkar", "2000");
    Book galvin = new Book("Operating System", "Galvin", "2005");
    Book internetSec = new Book("Internet Security", "Ankit Fadia", "1995");
    User user = new User("000-0000", "achal", "", "", "");
    @Before
    public void setUp() {

    }

    @Test
    public void testGetListOfAvailableBooks() {
        bookCollection = new ItemCollection(new Item[]{letusc, galvin, internetSec});

        List<String> bookList = bookCollection.getListOfAvailableItems();
        assertTrue(bookList.contains(letusc.getFormattedString()));
        assertTrue(bookList.contains(galvin.getFormattedString()));
        assertTrue(bookList.contains(internetSec.getFormattedString()));
        assertEquals(3, bookList.size());
    }

    @Test
    public void testIssuingOfABook() {
        bookCollection = new ItemCollection(new Item[]{letusc, galvin, internetSec});
        assertTrue(bookCollection.issueItem(letusc.getTitle(), user));

        List<String> bookList = bookCollection.getListOfAvailableItems();
        assertFalse(bookList.contains(letusc.getFormattedString()));
    }

    @Test
    public void testIssuingOfABookThatDoesntExist() {
        bookCollection = new ItemCollection(new Item[]{galvin, internetSec});
        assertFalse(bookCollection.issueItem(letusc.getTitle(), user));
    }

    @Test
    public void testReturningOfBook(){
        bookCollection = new ItemCollection(new Item[]{letusc, galvin, internetSec});
        bookCollection.issueItem(letusc.getTitle(), user);
        bookCollection.returnItem(letusc.getTitle(), user);
        List<String> bookList = bookCollection.getListOfAvailableItems();
        assertTrue(bookList.contains(letusc.getFormattedString()));
    }

    @Test
    public void testReturningOfBookThatExistAlready(){
        bookCollection = new ItemCollection(new Item[]{letusc, galvin, internetSec});
        assertFalse(bookCollection.returnItem(letusc.getTitle(), user));
    }

    @Test
    public void testReturningOfBookByDifferentUser(){
        bookCollection = new ItemCollection(new Item[]{letusc, galvin, internetSec});
        bookCollection.issueItem(letusc.getTitle(), user);
        bookCollection.returnItem(letusc.getTitle(), new User("000-0000", "achal", "", "", ""));
    }
}
