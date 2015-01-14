package com.twu.biblioteca;

public class MovieLibrary extends Library {
    public Movie addItem(Movie movie) {
        return (Movie) super.addItem(movie);
    }

    public Movie removeItem(String movieTitle) {
        return (Movie) super.removeItem(movieTitle);
    }
}
