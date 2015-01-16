package com.twu.biblioteca.library;

import com.twu.biblioteca.library.Movie;
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
    public void testGetterOfBook(){
        assertEquals(name, seven.getTitle());
        assertEquals(year, seven.getYear());
        assertEquals(director, seven.getDirector());
        assertEquals(movieRating, seven.getRating());
    }
}
