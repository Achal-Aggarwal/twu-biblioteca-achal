package com.twu.biblioteca;

public class LogoutController extends Controller {
    public LogoutController(LibraryManager libraryManger, InputOutputManger inputOutputManger) {
        super(libraryManger);
    }

    @Override
    public boolean execute() {
        libraryManager.unSetCurrentUser();

        return true;
    }

    @Override
    public String getTitle() {
        return "Logout.";
    }

    @Override
    public boolean isHidden() {
        return libraryManager.getCurrentUser() == null;
    }
}
