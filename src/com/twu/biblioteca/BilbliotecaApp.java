package com.twu.biblioteca;

public class BilbliotecaApp {
    private LibraryManager libraryManager;
    private InputOutputManger io;

    public BilbliotecaApp(LibraryManager libraryManager, InputOutputManger inputOutputManger) {
        this.libraryManager = libraryManager;
        this.io = inputOutputManger;
    }

    public int runApplication() {
        MenuController mainMenuView = new MenuController(libraryManager, io);
        mainMenuView.setAction("1", new ListOfBooksController(libraryManager, io));
        mainMenuView.setAction("2", new CheckoutBookController(libraryManager, io));
        mainMenuView.setAction("3", new CheckinBookController(libraryManager, io));
        mainMenuView.setAction("4", new ListOfMoviesController(libraryManager, io));
        mainMenuView.setAction("5", new CheckoutMovieController(libraryManager, io));
        mainMenuView.setAction("6", new CheckinMovieController(libraryManager, io));
        mainMenuView.setAction("10", new QuitMenuController(libraryManager, io));

        io.printLine("Welcome and thank you for taking time to visit Biblioteca.");
        while(mainMenuView.execute());

        return 0;

    }
    public static void main(String[] args) {
        BookLibrary bookLibrary = new BookLibrary();
        bookLibrary.addItem(new Book("Let Us C", "Yashwant Kanetkar", "2000"));
        bookLibrary.addItem(new Book("Operating System", "Galvin", "2005"));
        bookLibrary.addItem(new Book("Internet Security", "Ankit Fadia", "1995"));
        bookLibrary.addItem(new Book("Five Point Someone", "Chetan Bhagat", "2012"));

        MovieLibrary movieLibrary = new MovieLibrary();
        Movie seven = new Movie("Seven", "1995", "David Fincher", "8");
        Movie darkKnight = new Movie("The Dark Knight", "2008", "Christopher Nolan", "unrated");
        movieLibrary.addItem(seven);
        movieLibrary.addItem(darkKnight);


        new BilbliotecaApp(
                new LibraryManager(bookLibrary, movieLibrary),
                new InputOutputManger(System.in, System.out)).runApplication();
    }
}
