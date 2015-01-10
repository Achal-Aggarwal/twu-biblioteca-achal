package com.twu.biblioteca;

public class Book {
    private String title;
    private String author;
    private String publicationDate;

    public Book(String title, String author, String publicationDate) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
    }

    @Override
    public String toString() {
        return title + "\t" + author + "\t" + publicationDate;
    }
}
