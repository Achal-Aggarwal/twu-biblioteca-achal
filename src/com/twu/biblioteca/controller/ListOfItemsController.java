package com.twu.biblioteca.controller;

import com.twu.biblioteca.library.Library;

public abstract class ListOfItemsController extends Controller {
    protected Library library;
    protected ListOfItemsController(Library library) {
        this.library = library;
    }
}
