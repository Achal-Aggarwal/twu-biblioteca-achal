package com.twu.biblioteca;

public class Book extends Item {
    private String title;
    private String author;
    private String publicationDate;

    public Book(String title, String author, String publicationDate) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
    }

    public String getFormattedString() {
        return "|" + title + "|\t|" + author + "|\t|" + publicationDate + "|";
    }

    public String getTitle() {
        return title;
    }
}
