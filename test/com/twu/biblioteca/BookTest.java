package com.twu.biblioteca;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by achalaggarwal on 1/10/15.
 */
public class BookTest {
    @Test
    public void testStringRepresentationOfBookWithAuthorPresent(){
        String title = "Let Us C";
        String author = "Yashwant Kanetkar";
        String publicationDate = "2000";
        Book letusc = new Book(title,author,publicationDate);
        assertEquals(title + "\t" + author + "\t" + publicationDate, letusc.toString());
    }
}
