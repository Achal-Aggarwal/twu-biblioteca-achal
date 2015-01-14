package com.twu.biblioteca;

public class CheckoutMovieController extends Controller {
    private CheckoutMovieView view;
    public CheckoutMovieController(LibraryManager libraryManager, InputOutputManger inputOutputManger) {
        super(libraryManager);
        view = new CheckoutMovieView(inputOutputManger);
    }

    @Override
    public boolean execute() {
        if(libraryManager.checkoutMovie(view.getMovieName())){
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
