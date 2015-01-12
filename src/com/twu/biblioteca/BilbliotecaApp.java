package com.twu.biblioteca;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class BilbliotecaApp {
    private Library library;
    private PrintStream output;
    private Scanner input;

    public BilbliotecaApp(Library library, PrintStream output, InputStream inputStream){
        this.library = library;
        this.output = output;
        this.input = new Scanner(inputStream);
        this.input.useDelimiter("\n");
    }

    private void listOfBooksView(){
        List<String> books = library.getListOfAvailableBooks();
        output.println("List of books available.");
        for (int i = 0; i < books.size(); i++) {
            output.println((i+1)+ ". \t" + books.get(i));
        }
    }

    private void checkinBookView() {
        if(library.checkin(input.next())){
            output.println("Thank you for returning the book.");
        } else {
            output.println("That is not a valid book to return.");
        }
    }

    private void checkoutBookView() {
        if(library.checkout(input.next())){
            output.println("Thank you! Enjoy the book");
        } else {
            output.println("That book is not available.");
        }
    }

    private String menuView() {
        output.println("--Menu--");
        output.println("1. \tList Books");
        output.println("2. \tCheckout Book");
        output.println("3. \tCheckin Book");
        output.println("4. \tQuit");
        return input.next();
    }

    public int runApplication() {
        output.println("Welcome and thank you for taking time to visit Biblioteca.");

        do {
            String selectedOption = menuView();

            if (selectedOption.equals("1")) {
                listOfBooksView();
            } else if (selectedOption.equals("2")) {
                checkoutBookView();
            } else if (selectedOption.equals("3")) {
                checkinBookView();
            } else if (selectedOption.equals("4")) {
                break;
            } else {
                output.println("Select a valid option!");
            }
        }while(true);

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
