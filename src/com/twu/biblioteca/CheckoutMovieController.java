package com.twu.biblioteca;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class CheckoutMovieController extends Controller {
    private CheckoutMovieView view;
    private LoginController loginController;

    public CheckoutMovieController(LibraryManager libraryManager, InputOutputManger inputOutputManger) {
        super(libraryManager);
        view = new CheckoutMovieView(inputOutputManger);
        loginController = new LoginController(libraryManager, inputOutputManger);
    }

    @Override
    public boolean execute() {
        boolean login_successful = loginController.execute();
        if(!login_successful){
            view.setStatus(CheckoutMovieView.Status.LOGIN_REQUIRED);
        } else if(libraryManager.checkoutMovie(view.getMovieName())){
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
