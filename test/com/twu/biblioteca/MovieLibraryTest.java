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
        assertSame(seven, movieLibrary.addMovie(seven));
        assertTrue(movieLibrary.isMoviePresent(seven.getName()));
    }

    @Test
    public void testAddMovieShouldReturnNullIfAddedMovieIsAlreadyPresent() {
        movieLibrary.addMovie(seven);
        assertNull(movieLibrary.addMovie(seven));
    }

    @Test
    public void testListOfMovies() {
        movieLibrary.addMovie(seven);
        movieLibrary.addMovie(darkKnight);

        List<String> movieList = movieLibrary.getListOfAvailableMovies();
        assertTrue(movieList.contains(seven.getFormattedString()));
        assertTrue(movieList.contains(darkKnight.getFormattedString()));
    }

    @Test
    public void testRemoveMovieShouldRemoveMovieFromLibrary(){
        movieLibrary.addMovie(seven);
        movieLibrary.removeMovie(seven.getName());
        assertFalse(movieLibrary.isMoviePresent(seven.getName()));
    }

    @Test
    public void testRemoveMovieShouldReturnReferenceOfRemovedMovie() {
        movieLibrary.addMovie(seven);
        assertSame(seven, movieLibrary.removeMovie(seven.getName()));
    }

    @Test
    public void testRemoveBookShouldReturnNullIfBookIsNotPresent() {
        assertNull(movieLibrary.removeMovie(seven.getName()));
    }
}
