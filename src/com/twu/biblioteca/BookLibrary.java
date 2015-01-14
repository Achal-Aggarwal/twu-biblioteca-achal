package com.twu.biblioteca;

public class BookLibrary extends Library {
    public Book addItem(Book book) {
        return (Book) super.addItem(book);
    }

    public Book removeItem(String bookTitle) {
        return (Book) super.removeItem(bookTitle);
    }
}
