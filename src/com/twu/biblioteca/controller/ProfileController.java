package com.twu.biblioteca.controller;

import com.twu.biblioteca.io.InputOutputManger;
import com.twu.biblioteca.view.ProfileView;
import com.twu.biblioteca.session.SessionManager;

public class ProfileController extends Controller {
    private ProfileView view;
    public ProfileController(InputOutputManger inputOutputManger) {
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
