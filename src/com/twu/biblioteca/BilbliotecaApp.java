package com.twu.biblioteca;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class BilbliotecaApp {
    private Library library;
    private PrintStream output;
    private Scanner input;

    public BilbliotecaApp(Library library, PrintStream output, InputStream inputStream){
        this.library = library;
        this.output = output;
        this.input = new Scanner(inputStream);
    }

    public int runApplication() {
        output.println("Welcome and thank you for taking time to visit Biblioteca.");
        MenuViewController mainMenuView = new MenuViewController(library, output, input);
        mainMenuView.setAction("1", new ListOfBooksViewController(library, output, input));
        mainMenuView.setAction("2", new CheckoutBookViewController(library, output, input));
        mainMenuView.setAction("3", new CheckinBookViewController(library, output, input));
        mainMenuView.setAction("4", new QuitMenuViewController(library, output, input));

        while(mainMenuView.execute());

        return 0;

    }
    public static void main(String[] args) {
        Library library = new Library();
        library.addBook(new Book("Let Us C", "Yashwant Kanetkar", "2000"));
        library.addBook(new Book("Operating System", "Galvin", "2005"));
        library.addBook(new Book("Internet Security", "Ankit Fadia", "1995"));
        library.addBook(new Book("Five Point Someone", "Chetan Bhagat", "2012"));
        new BilbliotecaApp(library, System.out, System.in).runApplication();
    }
}
