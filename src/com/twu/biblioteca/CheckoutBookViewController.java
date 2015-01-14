package com.twu.biblioteca;

public class CheckoutBookViewController extends ViewController{
    public CheckoutBookViewController(Library library, InputOutputManger inputOutputManger) {
        super(library, inputOutputManger);
    }

    @Override
    public boolean execute() {
        if(library.checkout(io.readLine())){
            io.printLine("Thank you! Enjoy the book");
        } else {
            io.printLine("That book is not available.");
        }

        return true;
    }

    @Override
    public String getTitle() {
        return "Checkout book.";
    }
}
