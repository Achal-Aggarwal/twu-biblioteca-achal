package com.twu.biblioteca;

public class BookLibrary extends Library {
    public Book addItem(Book book) {
        return (Book) super.addIntoAvailableItem(book);
    }

    public Book removeFromAvailableItem(String bookTitle) {
        return (Book) super.removeFromAvailableItem(bookTitle);
    }
}
