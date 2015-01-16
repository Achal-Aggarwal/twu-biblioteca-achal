package com.twu.biblioteca.controller;

import com.twu.biblioteca.view.CheckoutMovieView;
import com.twu.biblioteca.io.InputOutputManger;
import com.twu.biblioteca.library.Library;

public class CheckoutMovieController extends Controller {
    private CheckoutMovieView view;
    private LoginController loginController;
    private Library library;
    public CheckoutMovieController(InputOutputManger inputOutputManger, Library library) {
        this.library = library;
        view = new CheckoutMovieView(inputOutputManger);
        loginController = new LoginController(inputOutputManger);
    }

    @Override
    public boolean execute() {
        boolean login_successful = loginController.execute();
        if(!login_successful){
            view.setStatus(CheckoutMovieView.Status.LOGIN_REQUIRED);
        } else if(library.checkoutMovie(view.getMovieName())){
            view.setStatus(CheckoutMovieView.Status.CHECKOUT_SUCCESSFUL);
        } else {
            view.setStatus(CheckoutMovieView.Status.CHECKOUT_UNSUCCESSFUL);
        }

        view.render();

        return true;
    }

    @Override
    public String getTitle() {
        return "Checkout movie.";
    }
}
