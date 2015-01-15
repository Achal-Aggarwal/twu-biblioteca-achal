package com.twu.biblioteca;

public abstract class ListOfItemsController extends Controller {
    protected ListOfItemsView view;
    protected ListOfItemsController(LibraryManager libraryManager, ListOfItemsView view) {
        super(libraryManager);
        this.view = view;
    }

    public boolean execute() {
        view.render();
        return true;
    }

    abstract public String getTitle();
}
