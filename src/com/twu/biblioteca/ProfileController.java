package com.twu.biblioteca;

public class ProfileController extends Controller {
    private ProfileView view;
    public ProfileController(LibraryManager libraryManger, InputOutputManger inputOutputManger) {
        super(libraryManger);
        view = new ProfileView(inputOutputManger);
    }

    @Override
    public boolean execute() {
        view.setUser(SessionManager.getSession().getLoggedInUser());
        view.render();

        return true;
    }

    @Override
    public String getTitle() {
        return "Profile.";
    }

    @Override
    public boolean isHidden() {
        return SessionManager.getSession().getLoggedInUser() == null;
    }
}
