package com.twu.biblioteca.controller;

import com.twu.biblioteca.view.CheckinBookView;
import com.twu.biblioteca.io.InputOutputManger;
import com.twu.biblioteca.library.Library;

public class CheckinBookController extends Controller {
    private CheckinBookView view;
    private LoginController loginController;
    private Library library;

    public CheckinBookController(InputOutputManger inputOutputManger, Library libraryManger) {
        view = new CheckinBookView(inputOutputManger);
        this.library = libraryManger;
        loginController = new LoginController(inputOutputManger);
    }

    @Override
    public boolean execute() {
        boolean login_successful = loginController.execute();
        if(!login_successful){
            view.setStatus(CheckinBookView.Status.LOGIN_REQUIRED);
        } else if(library.checkinBook(view.getBookName())){
            view.setStatus(CheckinBookView.Status.CHECKIN_SUCCESSFUL);
        } else {
            view.setStatus(CheckinBookView.Status.CHECKIN_UNSUCCESSFUL);
        }

        view.render();

        return true;
    }

    @Override
    public String getTitle() {
        return "Checkin book.";
    }
}
