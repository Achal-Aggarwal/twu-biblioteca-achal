package com.twu.biblioteca;

public class Book {
    private String title;
    private String author;
    private String publicationDate;
    private boolean checkedOut;

    public Book(String title, String author, String publicationDate) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.checkedOut = false;
    }

    @Override
    public String toString() {
        return title + "\t" + author + "\t" + publicationDate;
    }

    public String getTitle() {
        return title;
    }

    public boolean checkOut() {
        if(checkedOut){
            return false;
        }

        checkedOut = true;
        return true;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public boolean checkin() {
        checkedOut = false;
        return true;
    }
}
