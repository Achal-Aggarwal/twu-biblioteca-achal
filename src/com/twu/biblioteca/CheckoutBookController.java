package com.twu.biblioteca;

public class CheckoutBookController extends Controller {
    private CheckoutBookView view;
    public CheckoutBookController(Library library, InputOutputManger inputOutputManger) {
        super(library);
        view = new CheckoutBookView(inputOutputManger);
    }

    @Override
    public boolean execute() {
        if(library.checkout(view.getBookName())){
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
