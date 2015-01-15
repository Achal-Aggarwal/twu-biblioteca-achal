package com.twu.biblioteca;

import org.junit.Test;

import static junit.framework.Assert.assertSame;
import static junit.framework.TestCase.assertEquals;

public class MovieTest {
    String name = "Seven";
    String year = "1995";
    String director = "David Fincher";
    String movieRating = "8";
    Movie seven = new Movie(name, year, director, movieRating);


    @Test
    public void testStringRepresentationOfBookWithAuthorPresent(){
        assertEquals("|" + name + "|\t|" + year + "|\t|" + director + "|\t|" + movieRating + "|", seven.getFormattedString());
    }

    @Test
    public void testNameGetter(){
        assertEquals(name, seven.getTitle());
    }

    @Test
    public void testGetterAndSetterOfBook(){
        User user = new User("000-0000", "achal");
        seven.setBorrower(user);
        assertSame(user, seven.getBorrower());
    }
}
