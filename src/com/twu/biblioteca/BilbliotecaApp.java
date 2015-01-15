package com.twu.biblioteca;

public class BilbliotecaApp {
    private LibraryManager libraryManager;
    private InputOutputManger io;

    public BilbliotecaApp(LibraryManager libraryManager, InputOutputManger inputOutputManger) {
        this.libraryManager = libraryManager;
        this.io = inputOutputManger;
    }

    public int runApplication() {
        MenuController mainMenuController = new MenuController(io);

        io.printLine("Welcome and thank you for taking time to visit Biblioteca.");

        mainMenuController.setAction("0", new LoginController(io));
        mainMenuController.setAction("1", new ListOfAvailableBooksController(io, libraryManager));
        mainMenuController.setAction("2", new CheckoutBookController(io, libraryManager));
        mainMenuController.setAction("3", new CheckinBookController(io, libraryManager));
        mainMenuController.setAction("4", new ListOfAvailableMoviesController(io, libraryManager));
        mainMenuController.setAction("8", new ListOfIssuedMoviesController(io, libraryManager));
        mainMenuController.setAction("5", new CheckoutMovieController(io, libraryManager));
        mainMenuController.setAction("6", new CheckinMovieController(io, libraryManager));
        mainMenuController.setAction("9", new LogoutController(io));
        mainMenuController.setAction("10", new ProfileController(io));
        mainMenuController.setAction("11", new QuitMenuController(io));
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
        SessionManager.getSession().registerUser(new Librarian("000-0000", "achal", "Achal", "achal@thoughtworks.com", "1234567890"));
        SessionManager.getSession().registerUser(new User("000-0001", "abhishek", "Abhishek", "abhishek@thoughtworks.com", "0987654321"));

        new BilbliotecaApp(
                libraryManager,
                new InputOutputManger(System.in, System.out)).runApplication();
    }
}
