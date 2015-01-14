package com.twu.biblioteca;

public class Movie extends Item {
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

    public String getTitle() {
        return name;
    }

    public String getFormattedString() {
        return "|" + name + "|\t|" + year + "|\t|" + director + "|\t|" + movieRating + "|";
    }
}
