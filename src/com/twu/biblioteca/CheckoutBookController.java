package com.twu.biblioteca;

public class CheckoutBookController extends Controller {
    private CheckoutBookView view;
    private Library library;
    LoginController loginController;

    public CheckoutBookController(InputOutputManger inputOutputManger, Library library) {
        view = new CheckoutBookView(inputOutputManger);
        this.library = library;
        loginController = new LoginController(inputOutputManger);
    }

    @Override
    public boolean execute() {
        boolean login_successful = loginController.execute();
        if(!login_successful){
            view.setStatus(CheckoutBookView.Status.LOGIN_REQUIRED);
        } else if(library.checkoutBook(view.getBookName())){
            view.setStatus(CheckoutBookView.Status.CHECKOUT_SUCCESSFUL);
        } else {
            view.setStatus(CheckoutBookView.Status.CHECKOUT_UNSUCCESSFUL);
        }

        view.render();

        return true;
    }

    @Override
    public String getTitle() {
        return "Checkout book.";
    }
}
