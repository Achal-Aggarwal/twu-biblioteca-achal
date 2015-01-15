package com.twu.biblioteca;

import org.junit.Test;

import static junit.framework.Assert.assertSame;
import static junit.framework.TestCase.assertEquals;

public class BookTest {
    String title = "Let Us C";
    String author = "Yashwant Kanetkar";
    String publicationDate = "2000";
    Book letusc = new Book(title,author,publicationDate);

    @Test
    public void testStringRepresentationOfBookWithAuthorPresent(){
        assertEquals("|" + title + "|\t|" + author + "|\t|" + publicationDate + "|", letusc.getFormattedString());
    }

    @Test
    public void testGetterAndSetterOfBook(){
        User user = new User("000-0000", "achal");
        letusc.setBorrower(user);
        assertSame(user, letusc.getBorrower());
    }
}
