package com.twu.biblioteca;

public abstract class ListOfItemsController extends Controller {
    protected ListOfItemsView view;
    protected Library library;
    protected ListOfItemsController(ListOfItemsView view, Library library) {
        this.view = view;
        this.library = library;
    }

    public boolean execute() {
        view.render();
        return true;
    }

    abstract public String getTitle();
}
