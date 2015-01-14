package com.twu.biblioteca;

import java.util.HashMap;
import java.util.List;

public class LibraryManager {
    private HashMap issuedBooks = new HashMap();
    private BookLibrary bookLibrary;
    private MovieLibrary movieLibrary;
    private HashMap issuedMovies = new HashMap();

    public LibraryManager(BookLibrary bookLibrary, MovieLibrary movieLibrary) {
        this.bookLibrary = bookLibrary;
        this.movieLibrary = movieLibrary;
    }

    public boolean checkoutBook(String bookTitle) {
        Book book = (Book) issuedBooks.get(bookTitle);
        if(book != null){
            return false;
        }

        book = bookLibrary.removeBook(bookTitle);
        if(book == null){
            return false;
        }

        issuedBooks.put(bookTitle, book);

        return true;
    }

    public boolean isBookCheckedOut(String bookTitle) {
        return issuedBooks.containsKey(bookTitle);
    }

    public boolean checkinBook(String bookTitle) {
        Book book = (Book) issuedBooks.get(bookTitle);
        if(book == null){
            return false;
        }

        bookLibrary.addBook(book);
        issuedBooks.remove(bookTitle);

        return true;
    }

    public List<String> getListOfAvailableBooks() {
        return bookLibrary.getListOfAvailableBooks();
    }

    public boolean checkoutMovie(String movieName) {
        Movie movie = (Movie) issuedMovies.get(movieName);
        if(movie != null){
            return false;
        }

        movie = movieLibrary.removeMovie(movieName);
        if(movie == null){
            return false;
        }

        issuedMovies.put(movieName, movie);

        return true;
    }

    public boolean isMovieCheckedOut(String movieName) {
        return issuedMovies.containsKey(movieName);
    }

    public boolean checkinMovie(String movieName) {
        Movie movie = (Movie) issuedMovies.get(movieName);
        if(movie == null){
            return false;
        }

        movieLibrary.addMovie(movie);
        issuedMovies.remove(movieName);

        return true;
    }

    public List<String> getListOfAvailableMovies() {
        return movieLibrary.getListOfAvailableMovies();
    }
}
