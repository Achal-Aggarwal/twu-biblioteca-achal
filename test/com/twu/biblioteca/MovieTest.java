package com.twu.biblioteca;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MovieTest {
    String name = "Seven";
    String year = "1995";
    String director = "David Fincher";
    String movieRating = "8";
    Movie seven;

    @Test
    public void testStringRepresentationOfBookWithAuthorPresent(){
        seven = new Movie(name, year, director, movieRating);
        assertEquals("|" + name + "|\t|" + year + "|\t|" + director + "|\t|" + movieRating + "|", seven.getFormattedString());
    }

    @Test
    public void testNameGetter(){
        seven = new Movie(name, year, director, movieRating);
        assertEquals(name, seven.getName());
    }
}
