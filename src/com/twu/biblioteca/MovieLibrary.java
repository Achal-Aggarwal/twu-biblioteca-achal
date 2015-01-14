package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by achalaggarwal on 1/14/15.
 */
public class MovieLibrary {

    private HashMap movies = new HashMap();

    public boolean isMoviePresent(String bookTitle) {
        return movies.containsKey(bookTitle);
    }

    public Movie addMovie(Movie movie) {
        if(isMoviePresent(movie.getName())){
            return null;
        }

        movies.put(movie.getName(), movie);

        return movie;
    }

    public List<String> getListOfAvailableMovies() {

        List<String> availableMovies = new ArrayList<String>();
        for (Object movie : movies.values()) {
            availableMovies.add(((Movie) movie).getFormattedString());
        }

        return availableMovies;
    }

    public Movie removeMovie(String movieName) {
        if(!isMoviePresent(movieName)){
            return null;
        }

        return (Movie) movies.remove(movieName);
    }
}
