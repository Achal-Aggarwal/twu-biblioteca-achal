package com.twu.biblioteca;

public class ProfileController extends Controller {
    private ProfileView view;
    public ProfileController(LibraryManager libraryManger, InputOutputManger inputOutputManger) {
        super(libraryManger);
        view = new ProfileView(inputOutputManger);
    }

    @Override
    public boolean execute() {
        view.setUser(libraryManager.getCurrentUser());
        view.render();

        return true;
    }

    @Override
    public String getTitle() {
        return "Profile.";
    }

    @Override
    public boolean isHidden() {
        return libraryManager.getCurrentUser() == null;
    }
}
