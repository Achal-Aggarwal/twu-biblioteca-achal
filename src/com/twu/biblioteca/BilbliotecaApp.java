package com.twu.biblioteca;

public class BilbliotecaApp {
    private Library library;
    private InputOutputManger io;

    public BilbliotecaApp(Library library, InputOutputManger inputOutputManger) {
        this.library = library;
        this.io = inputOutputManger;
    }

    public int runApplication() {
        MenuController mainMenuView = new MenuController(library, io);
        mainMenuView.setAction("1", new ListOfBooksController(library, io));
        mainMenuView.setAction("2", new CheckoutBookController(library, io));
        mainMenuView.setAction("3", new CheckinBookController(library, io));
        mainMenuView.setAction("4", new QuitMenuController(library, io));

        io.printLine("Welcome and thank you for taking time to visit Biblioteca.");
        while(mainMenuView.execute());

        return 0;

    }
    public static void main(String[] args) {
        Library library = new Library();
        library.addBook(new Book("Let Us C", "Yashwant Kanetkar", "2000"));
        library.addBook(new Book("Operating System", "Galvin", "2005"));
        library.addBook(new Book("Internet Security", "Ankit Fadia", "1995"));
        library.addBook(new Book("Five Point Someone", "Chetan Bhagat", "2012"));
        new BilbliotecaApp(library, new InputOutputManger(System.in, System.out)).runApplication();
    }
}
