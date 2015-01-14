package com.twu.biblioteca;

/**
 * Created by achalaggarwal on 1/14/15.
 */
public class Movie {
    private String name;
    private String year;
    private String director;
    private String movieRating;

    public Movie(String name, String year, String director, String movieRating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.movieRating = movieRating;
    }

    public String getName() {
        return name;
    }

    public String getFormattedString() {
        return "|" + name + "|\t|" + year + "|\t|" + director + "|\t|" + movieRating + "|";
    }
}
