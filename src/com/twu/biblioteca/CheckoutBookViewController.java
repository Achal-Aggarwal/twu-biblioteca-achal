package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by achalaggarwal on 1/13/15.
 */
public class CheckoutBookViewController extends ViewController{
    public CheckoutBookViewController(Library library, PrintStream output, Scanner input) {
        super(library, output, input);
    }

    @Override
    public boolean execute() {
        if(library.checkout(input.nextLine())){
            output.println("Thank you! Enjoy the book");
        } else {
            output.println("That book is not available.");
        }

        return true;
    }

    @Override
    public String getTitle() {
        return "Checkout book.";
    }
}
