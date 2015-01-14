package com.twu.biblioteca;

/**
 * Created by achalaggarwal on 1/14/15.
 */
public class CheckoutBookView extends View {
    public enum Status {
        NONE, CHECKOUT_SUCCESSFUL, CHECKOUT_UNSUCCESSFUL
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
            io.printLine("Thank you! Enjoy the book");
        } else if(status == Status.CHECKOUT_UNSUCCESSFUL){
            io.printLine("That book is not available.");
        }
    }

    public String getBookName() {
        return io.readLine();
    }
}
