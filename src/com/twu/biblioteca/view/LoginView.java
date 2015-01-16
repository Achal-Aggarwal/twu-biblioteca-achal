package com.twu.biblioteca.view;

import com.twu.biblioteca.io.InputOutputManger;
import com.twu.biblioteca.session.SessionManager;

public class LoginView extends View {


    public enum Status {
        NONE, LOGIN_SUCCESSFUL, LOGIN_UNSUCCESSFUL;
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
            printMessage("Welcome " + SessionManager.getSession().getLoggedInUser().getName() + ".");
        } else if(status == Status.LOGIN_UNSUCCESSFUL){
            printMessage("Wrong library number or password.");
        }
    }

    public String getLibraryNumber() {
        io.printString("Enter your library number(xxx-xxxx) : ");
        return io.readLine();
    }

    public String getUserPassword() {
        io.printString("Enter your password : ");
        return io.readLine();
    }
}
