package com.twu.biblioteca.view;

import com.twu.biblioteca.io.InputOutputManger;

public class CheckoutBookView extends View {
    public enum Status {
        NONE, CHECKOUT_SUCCESSFUL, LOGIN_REQUIRED, CHECKOUT_UNSUCCESSFUL
    }

    private Status status = Status.NONE;

    public CheckoutBookView(InputOutputManger inputOutputManger) {
        super(inputOutputManger);
    }

    public void setStatus(Status status){
        this.status = status;
    }

    @Override
    public void render() {
        if(status == Status.CHECKOUT_SUCCESSFUL){
            printMessage("Thank you! Enjoy the book");
        } else if(status == Status.CHECKOUT_UNSUCCESSFUL){
            printMessage("That book is not available.");
        }
    }

    public String getBookName() {
        io.printString("Enter book name : ");
        return io.readLine();
    }
}
