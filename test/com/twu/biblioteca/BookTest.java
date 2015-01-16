package com.twu.biblioteca;

import junit.framework.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertSame;
import static junit.framework.TestCase.assertEquals;

public class BookTest {
    String title = "Let Us C";
    String author = "Yashwant Kanetkar";
    String publicationDate = "2000";
    Book letusc = new Book(title,author,publicationDate);

    @Test
    public void testGetterOfBook(){
        assertEquals(title, letusc.getTitle());
        assertEquals(author, letusc.getAuthor());
        assertEquals(publicationDate, letusc.getPublicationDate());
    }
}
