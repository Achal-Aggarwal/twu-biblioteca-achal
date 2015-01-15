package com.twu.biblioteca;

public class LogoutController extends Controller {
    public LogoutController(LibraryManager libraryManger, InputOutputManger inputOutputManger) {
        super(libraryManger);
    }

    @Override
    public boolean execute() {
        SessionManager.getSession().logout();

        return true;
    }

    @Override
    public String getTitle() {
        return "Logout.";
    }

    @Override
    public boolean isHidden() {
        return SessionManager.getSession().getLoggedInUser() == null;
    }
}
