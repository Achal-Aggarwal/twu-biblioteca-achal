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

    public String getFormattedString() {
        return "|" + title + "|\t|" + author + "|\t|" + publicationDate + "|";
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
        if(!checkedOut){
            return false;
        }
        checkedOut = false;
        return true;
    }
}
