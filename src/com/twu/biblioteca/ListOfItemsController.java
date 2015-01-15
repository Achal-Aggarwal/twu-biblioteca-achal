package com.twu.biblioteca;

public abstract class ListOfItemsController extends Controller {
    protected ListOfItemsView view;
    protected LibraryManager libraryManager;
    protected ListOfItemsController(ListOfItemsView view, LibraryManager libraryManager) {
        this.view = view;
        this.libraryManager = libraryManager;
    }

    public boolean execute() {
        view.render();
        return true;
    }

    abstract public String getTitle();
}
