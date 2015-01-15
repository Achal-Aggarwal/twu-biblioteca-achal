package com.twu.biblioteca;

public class BilbliotecaApp {
    private LibraryManager libraryManager;
    private InputOutputManger io;

    public BilbliotecaApp(LibraryManager libraryManager, InputOutputManger inputOutputManger) {
        this.libraryManager = libraryManager;
        this.io = inputOutputManger;
    }

    public int runApplication() {
        MenuController mainMenuController = new MenuController(libraryManager, io);
        LoginController loginController = new LoginController(libraryManager, io);

        io.printLine("Welcome and thank you for taking time to visit Biblioteca.");

        mainMenuController.setAction("1", new ListOfAvailableBooksController(libraryManager, io));
        mainMenuController.setAction("2", new CheckoutBookController(libraryManager, io));
        mainMenuController.setAction("3", new CheckinBookController(libraryManager, io));
        mainMenuController.setAction("4", new ListOfAvailableMoviesController(libraryManager, io));
        mainMenuController.setAction("5", new CheckoutMovieController(libraryManager, io));
        mainMenuController.setAction("6", new CheckinMovieController(libraryManager, io));
        mainMenuController.setAction("10", new QuitMenuController(libraryManager, io));
        mainMenuController.execute();

        loginController.setAction(mainMenuController);
        loginController.execute();
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

        LibraryManager libraryManager = new LibraryManager(bookLibrary, movieLibrary);
        libraryManager.registerUser(new User("000-0000", "achal"));

        new BilbliotecaApp(
                libraryManager,
                new InputOutputManger(System.in, System.out)).runApplication();
    }
}
