package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class BookTest {
    String title = "Let Us C";
    String author = "Yashwant Kanetkar";
    String publicationDate = "2000";
    Book letusc;

    @Before
    public void setUp() {
        letusc = new Book(title,author,publicationDate);
    }

    @Test
    public void testStringRepresentationOfBookWithAuthorPresent(){
        assertEquals(title + "\t" + author + "\t" + publicationDate, letusc.toString());
    }

    @Test
    public void testBookCheckout(){
        assertTrue(letusc.checkOut());
        assertTrue(letusc.isCheckedOut());
    }

    @Test
    public void testBookCheckoutFailure(){
        assertTrue(letusc.checkOut());
        assertTrue(letusc.isCheckedOut());
        assertFalse(letusc.checkOut());
    }

    @Test
    public void testBookCheckin(){
        assertTrue(letusc.checkOut());
        assertTrue(letusc.isCheckedOut());
        assertTrue(letusc.checkin());
        assertFalse(letusc.isCheckedOut());
    }
}
