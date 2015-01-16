package com.twu.biblioteca;

public abstract class ListOfItemsController extends Controller {
    protected Library library;
    protected ListOfItemsController(Library library) {
        this.library = library;
    }
}
