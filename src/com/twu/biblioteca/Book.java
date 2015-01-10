package com.twu.biblioteca;

/**
 * Created by achalaggarwal on 1/10/15.
 */
public class Book {
    private String title;

    public Book(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
