package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertSame;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class MovieLibraryTest {
    MovieLibrary movieLibrary;
    Movie seven = new Movie("Seven", "1995", "David Fincher", "8");
    Movie darkKnight = new Movie("The Dark Knight", "2008", "Christopher Nolan", "unrated");

    @Before
    public void setUp() {
        movieLibrary = new MovieLibrary();
    }

    @Test
    public void testAddMovieShouldReturnReferenceOfAddedMovie() {
        assertSame(seven, movieLibrary.addItem(seven));
        assertTrue(movieLibrary.isItemPresent(seven.getTitle()));
    }

    @Test
    public void testAddMovieShouldReturnNullIfAddedMovieIsAlreadyPresent() {
        movieLibrary.addItem(seven);
        assertNull(movieLibrary.addItem(seven));
    }

    @Test
    public void testListOfMovies() {
        movieLibrary.addItem(seven);
        movieLibrary.addItem(darkKnight);

        List<String> movieList = movieLibrary.getListOfAvailableItems();
        assertTrue(movieList.contains(seven.getFormattedString()));
        assertTrue(movieList.contains(darkKnight.getFormattedString()));
    }

    @Test
    public void testRemoveMovieShouldRemoveMovieFromLibrary(){
        movieLibrary.addItem(seven);
        movieLibrary.removeItem(seven.getTitle());
        assertFalse(movieLibrary.isItemPresent(seven.getTitle()));
    }

    @Test
    public void testRemoveMovieShouldReturnReferenceOfRemovedMovie() {
        movieLibrary.addItem(seven);
        assertSame(seven, movieLibrary.removeItem(seven.getTitle()));
    }

    @Test
    public void testRemoveBookShouldReturnNullIfBookIsNotPresent() {
        assertNull(movieLibrary.removeItem(seven.getTitle()));
    }
}
