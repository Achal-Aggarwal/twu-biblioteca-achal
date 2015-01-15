package com.twu.biblioteca;

public class CheckoutMovieController extends Controller {
    private CheckoutMovieView view;
    private LoginController loginController;
    private LibraryManager libraryManager;
    public CheckoutMovieController(InputOutputManger inputOutputManger, LibraryManager libraryManager) {
        this.libraryManager = libraryManager;
        view = new CheckoutMovieView(inputOutputManger);
        loginController = new LoginController(inputOutputManger);
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
