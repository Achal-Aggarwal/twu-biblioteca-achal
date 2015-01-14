package com.twu.biblioteca;

import java.util.*;

public class MenuViewController extends ViewController {
    private LinkedHashMap menuItems = new LinkedHashMap();

    public MenuViewController(Library library, InputOutputManger inputOutputManger) {
        super(library, inputOutputManger);
    }

    public boolean execute() {
        io.printLine(getTitle());

        Iterator menuItemSet = menuItems.entrySet().iterator();
        while (menuItemSet.hasNext()) {
            Map.Entry menuItem = (Map.Entry)menuItemSet.next();
            io.printLine(menuItem.getKey() + ". \t" + ((ViewController) menuItem.getValue()).getTitle());
        }

        String selectedAction = io.readLine();

        if(!menuItems.containsKey(selectedAction) && selectedAction.length() > 0){
            io.printLine("Select a valid option!");
        } else if(menuItems.containsKey(selectedAction)){
            return ((ViewController) menuItems.get(selectedAction)).execute();
        }
        return true;
    }

    public void setAction(String onUserInput, ViewController viewController) {
        menuItems.put(onUserInput, viewController);
    }


    public String getTitle() {
        return "Main Menu.";
    }
}
