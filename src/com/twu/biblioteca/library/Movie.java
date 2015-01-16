package com.twu.biblioteca.library;

import com.twu.biblioteca.library.Item;

public class Movie implements Item {
    private String name;
    private String year;
    private String director;
    private String rating;

    public Movie(String name, String year, String director, String movieRating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = movieRating;
    }

    public String getTitle() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public String getRating() {
        return rating;
    }
}
