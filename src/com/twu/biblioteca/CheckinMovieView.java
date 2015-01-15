package com.twu.biblioteca;

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
            io.printLine("Thank you for returning the movie.");
        } else if(status == Status.CHECKIN_UNSUCCESSFUL){
            io.printLine("That is not a valid movie to return.");
        }
    }

    public String getMovieName() {
        return io.readLine();
    }
}
