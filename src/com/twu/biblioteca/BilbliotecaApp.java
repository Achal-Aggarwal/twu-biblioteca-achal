package com.twu.biblioteca;

import com.twu.biblioteca.controller.*;
import com.twu.biblioteca.io.InputOutputManger;
import com.twu.biblioteca.library.Book;
import com.twu.biblioteca.library.ItemCollection;
import com.twu.biblioteca.library.Library;
import com.twu.biblioteca.library.Movie;
import com.twu.biblioteca.session.SessionManager;
import com.twu.biblioteca.session.User;

public class BilbliotecaApp {
    private Library library;
    private InputOutputManger io;

    public BilbliotecaApp(Library library, InputOutputManger inputOutputManger) {
        this.library = library;
        this.io = inputOutputManger;
    }

    public int runApplication() {
        MenuController menuController = new MenuController(io);

        io.printLine("Welcome and thank you for taking time to visit Biblioteca.");

        menuController.setAction("0", new LoginController(io));
        menuController.setAction("1", new ListOfAvailableBooksController(io, library));
        menuController.setAction("2", new CheckoutBookController(io, library));
        menuController.setAction("3", new CheckinBookController(io, library));
        menuController.setAction("4", new ListOfAvailableMoviesController(io, library));
        menuController.setAction("8", new ListOfIssuedMoviesController(io, library));
        menuController.setAction("5", new CheckoutMovieController(io, library));
        menuController.setAction("6", new CheckinMovieController(io, library));
        menuController.setAction("9", new LogoutController(io));
        menuController.setAction("10", new ProfileController(io));
        menuController.setAction("11", new QuitMenuController(io));
        menuController.execute();

        return 0;

    }
    public static void main(String[] args) {
        ItemCollection bookLibrary = new ItemCollection();
        bookLibrary.addItem(new Book("Let Us C", "Yashwant Kanetkar", "2000"));
        bookLibrary.addItem(new Book("Operating System", "Galvin", "2005"));
        bookLibrary.addItem(new Book("Internet Security", "Ankit Fadia", "1995"));
        bookLibrary.addItem(new Book("Five Point Someone", "Chetan Bhagat", "2012"));

        ItemCollection movieLibrary = new ItemCollection();
        Movie seven = new Movie("Seven", "1995", "David Fincher", "8");
        Movie darkKnight = new Movie("Th1e Dark Knight", "2008", "Christopher Nolan", "unrated");
        movieLibrary.addItem(seven);
        movieLibrary.addItem(darkKnight);

        Library library = new Library(bookLibrary, movieLibrary);

        //Adding Librarian
        SessionManager.getSession().registerUser(new User("000-0000", "achal", "Achal", "achal@thoughtworks.com", "1234567890", true));

        SessionManager.getSession().registerUser(new User("000-0001", "abhishek", "Abhishek", "abhishek@thoughtworks.com", "0987654321"));

        new BilbliotecaApp(library, new InputOutputManger(System.in, System.out)).runApplication();
    }
}
