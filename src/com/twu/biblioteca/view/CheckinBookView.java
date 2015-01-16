package com.twu.biblioteca.view;

import com.twu.biblioteca.io.InputOutputManger;

public class CheckinBookView extends View {
    public enum Status {
        NONE, CHECKIN_SUCCESSFUL, LOGIN_REQUIRED, CHECKIN_UNSUCCESSFUL
    }

    private Status status = Status.NONE;

    public CheckinBookView(InputOutputManger inputOutputManger) {
        super(inputOutputManger);
    }

    public void setStatus(Status status){
        this.status = status;
    }

    @Override
    public void render() {
        if(status == Status.CHECKIN_SUCCESSFUL){
            io.printLine("Thank you for returning the book.");
        } else if(status == Status.CHECKIN_UNSUCCESSFUL){
            io.printLine("That is not a valid book to return.");
        }
    }

    public String getBookName() {
        return io.readLine();
    }
}
