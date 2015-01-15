package com.twu.biblioteca;

public class CheckoutBookController extends Controller {
    private CheckoutBookView view;
    LoginController loginController;
    public CheckoutBookController(LibraryManager libraryManager, InputOutputManger inputOutputManger) {
        super(libraryManager);
        view = new CheckoutBookView(inputOutputManger);
        loginController = new LoginController(libraryManager, inputOutputManger);
    }

    @Override
    public boolean execute() {
        boolean login_successful = loginController.execute();
        if(!login_successful){
            view.setStatus(CheckoutBookView.Status.LOGIN_REQUIRED);
        } else if(libraryManager.checkoutBook(view.getBookName())){
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
