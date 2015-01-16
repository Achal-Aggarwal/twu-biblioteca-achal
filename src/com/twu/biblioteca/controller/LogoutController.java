package com.twu.biblioteca.controller;

import com.twu.biblioteca.io.InputOutputManger;
import com.twu.biblioteca.view.LogoutView;
import com.twu.biblioteca.session.SessionManager;

public class LogoutController extends Controller {
    private LogoutView view;
    public LogoutController(InputOutputManger inputOutputManger) {
        view = new LogoutView(inputOutputManger);
    }
    @Override
    public boolean execute() {
        SessionManager.getSession().logout();
        view.render();
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
