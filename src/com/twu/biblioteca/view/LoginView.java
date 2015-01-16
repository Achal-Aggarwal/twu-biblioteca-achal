package com.twu.biblioteca.view;

import com.twu.biblioteca.io.InputOutputManger;

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
        } else if(status == Status.LOGIN_UNSUCCESSFUL){
            io.printLine("Wrong library number or password.");
        }
    }

    public String getLibraryNumber() {
        return io.readLine();
    }
}
