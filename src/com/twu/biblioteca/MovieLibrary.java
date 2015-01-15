package com.twu.biblioteca;

public class MovieLibrary extends Library {
    public Movie addItem(Movie movie) {
        return (Movie) super.addIntoAvailableItem(movie);
    }

    public Movie removeFromAvailableItem(String movieTitle) {
        return (Movie) super.removeFromAvailableItem(movieTitle);
    }
}
