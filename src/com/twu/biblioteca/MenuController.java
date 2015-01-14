package com.twu.biblioteca;

import java.util.*;

public class MenuController extends Controller {
    private LinkedHashMap menuItems = new LinkedHashMap();
    private MenuView view;
    public MenuController(LibraryManager libraryManager, InputOutputManger inputOutputManger) {
        super(libraryManager);
        view = new MenuView(inputOutputManger);
    }

    public boolean execute() {
        Iterator menuItemSet = menuItems.entrySet().iterator();
        while (menuItemSet.hasNext()) {
            Map.Entry menuItem = (Map.Entry)menuItemSet.next();
            view.addMenuItem((String) menuItem.getKey(), ((Controller) menuItem.getValue()).getTitle());
        }

        view.render();

        String selectedAction = view.getSelectedAction();

        if(!menuItems.containsKey(selectedAction) && selectedAction.length() > 0){
            view.invalidOptionSelected();
        } else if(menuItems.containsKey(selectedAction)){
            return ((Controller) menuItems.get(selectedAction)).execute();
        }
        return true;
    }

    public void setAction(String onUserInput, Controller viewController) {
        menuItems.put(onUserInput, viewController);
    }


    public String getTitle() {
        return "Main Menu.";
    }
}
