package com.twu.biblioteca;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class BookTest {
    String title = "Let Us C";
    String author = "Yashwant Kanetkar";
    String publicationDate = "2000";
    Book letusc;

    @Test
    public void testStringRepresentationOfBookWithAuthorPresent(){
        letusc = new Book(title,author,publicationDate);
        assertEquals("|" + title + "|\t|" + author + "|\t|" + publicationDate + "|", letusc.getFormattedString());
    }
}
