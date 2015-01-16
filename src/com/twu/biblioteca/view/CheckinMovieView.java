package com.twu.biblioteca.view;

import com.twu.biblioteca.io.InputOutputManger;

public class CheckinMovieView extends View {
    public enum Status {
        NONE, CHECKIN_SUCCESSFUL, LOGIN_REQUIRED, CHECKIN_UNSUCCESSFUL
    }

    private Status status = Status.NONE;

    public CheckinMovieView(InputOutputManger inputOutputManger) {
        super(inputOutputManger);
    }

    public void setStatus(Status status){
        this.status = status;
    }

    @Override
    public void render() {
        if(status == Status.CHECKIN_SUCCESSFUL){
            printMessage("Thank you for returning the movie.");
        } else if(status == Status.CHECKIN_UNSUCCESSFUL){
            printMessage("That is not a valid movie to return.");
        }
    }

    public String getMovieName() {
        io.printString("Enter movie name : ");
        return io.readLine();
    }
}
