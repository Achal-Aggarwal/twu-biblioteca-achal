package com.twu.biblioteca;

public class CheckoutBookController extends Controller {
    private CheckoutBookView view;
    public CheckoutBookController(LibraryManager libraryManager, InputOutputManger inputOutputManger) {
        super(libraryManager);
        view = new CheckoutBookView(inputOutputManger);
    }

    @Override
    public boolean execute() {
        if(libraryManager.checkoutBook(view.getBookName())){
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
