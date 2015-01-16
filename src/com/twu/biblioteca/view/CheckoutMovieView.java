package com.twu.biblioteca.view;

import com.twu.biblioteca.io.InputOutputManger;

public class CheckoutMovieView extends View {
    public enum Status {
        NONE, CHECKOUT_SUCCESSFUL, LOGIN_REQUIRED, CHECKOUT_UNSUCCESSFUL
    }

    private Status status = Status.NONE;

    public CheckoutMovieView(InputOutputManger inputOutputManger) {
        super(inputOutputManger);
    }

    public void setStatus(Status status){
        this.status = status;
    }

    @Override
    public void render() {
        if(status == Status.CHECKOUT_SUCCESSFUL){
            io.printLine("Thank you! Enjoy the movie");
        } else if(status == Status.CHECKOUT_UNSUCCESSFUL){
            io.printLine("That movie is not available.");
        }
    }

    public String getMovieName() {
        return io.readLine();
    }
}
