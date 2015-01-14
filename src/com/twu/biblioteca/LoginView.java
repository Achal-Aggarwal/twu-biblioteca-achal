package com.twu.biblioteca;

public class LoginView extends View {
    public String getUserPassword() {
        return io.readLine();
    }

    public enum Status {
        NONE, LOGIN_SUCCESSFUL, LOGIN_UNSUCCESSFUL
    }

    private Status status = Status.NONE;

    public void setStatus(Status status){
        this.status = status;
    }

    public LoginView(InputOutputManger inputOutputManger) {
        super(inputOutputManger);
    }

    @Override
    public void render() {
        if(status == Status.LOGIN_SUCCESSFUL){
            io.printLine("Welcome and thank you for taking time to visit Biblioteca.");
        } else if(status == Status.LOGIN_UNSUCCESSFUL){
            io.printLine("Wrong library number or password.");
        }
    }

    public String getLibraryNumber() {
        return io.readLine();
    }
}
