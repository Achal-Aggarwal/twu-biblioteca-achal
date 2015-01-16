package com.twu.biblioteca;

public class BilbliotecaApp {
    private Library library;
    private InputOutputManger io;

    public BilbliotecaApp(Library library, InputOutputManger inputOutputManger) {
        this.library = library;
        this.io = inputOutputManger;
    }

    public int runApplication() {
        MenuController mainMenuController = new MenuController(io);

        io.printLine("Welcome and thank you for taking time to visit Biblioteca.");

        mainMenuController.setAction("0", new LoginController(io));
        mainMenuController.setAction("1", new ListOfAvailableBooksController(io, library));
        mainMenuController.setAction("2", new CheckoutBookController(io, library));
        mainMenuController.setAction("3", new CheckinBookController(io, library));
        mainMenuController.setAction("4", new ListOfAvailableMoviesController(io, library));
        mainMenuController.setAction("8", new ListOfIssuedMoviesController(io, library));
        mainMenuController.setAction("5", new CheckoutMovieController(io, library));
        mainMenuController.setAction("6", new CheckinMovieController(io, library));
        mainMenuController.setAction("9", new LogoutController(io));
        mainMenuController.setAction("10", new ProfileController(io));
        mainMenuController.setAction("11", new QuitMenuController(io));
        mainMenuController.execute();

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
        SessionManager.getSession().registerUser(new User("000-0000", "achal", "Achal", "achal@thoughtworks.com", "1234567890", true));
        SessionManager.getSession().registerUser(new User("000-0001", "abhishek", "Abhishek", "abhishek@thoughtworks.com", "0987654321"));

        new BilbliotecaApp(
                library,
                new InputOutputManger(System.in, System.out)).runApplication();
    }
}
