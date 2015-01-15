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

        io.printLine("Welcome and thank you for taking time to visit Biblioteca.");

        mainMenuController.setAction("0", new LoginController(libraryManager, io));
        mainMenuController.setAction("1", new ListOfAvailableBooksController(libraryManager, io));
        mainMenuController.setAction("7", new ListOfIssuedBooksController(libraryManager, io));
        mainMenuController.setAction("2", new CheckoutBookController(libraryManager, io));
        mainMenuController.setAction("3", new CheckinBookController(libraryManager, io));
        mainMenuController.setAction("4", new ListOfAvailableMoviesController(libraryManager, io));
        mainMenuController.setAction("8", new ListOfIssuedMoviesController(libraryManager, io));
        mainMenuController.setAction("5", new CheckoutMovieController(libraryManager, io));
        mainMenuController.setAction("6", new CheckinMovieController(libraryManager, io));
        mainMenuController.setAction("9", new LogoutController(libraryManager, io));
        mainMenuController.setAction("11", new ProfileController(libraryManager, io));
        mainMenuController.setAction("10", new QuitMenuController(libraryManager, io));
        mainMenuController.execute();

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
        Movie darkKnight = new Movie("Th1e Dark Knight", "2008", "Christopher Nolan", "unrated");
        movieLibrary.addItem(seven);
        movieLibrary.addItem(darkKnight);

        LibraryManager libraryManager = new LibraryManager(bookLibrary, movieLibrary);
        libraryManager.registerUser(new Librarian("000-0000", "achal", "", "", ""));
        libraryManager.registerUser(new User("000-0001", "abhishek", "", "", ""));

        new BilbliotecaApp(
                libraryManager,
                new InputOutputManger(System.in, System.out)).runApplication();
    }
}
