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
        boolean returnValue = true;
        while(returnValue) {
            Iterator menuItemSet = menuItems.entrySet().iterator();
            while (menuItemSet.hasNext()) {
                Map.Entry menuItem = (Map.Entry)menuItemSet.next();
                String onInput = (String) menuItem.getKey();
                Controller controller = (Controller) menuItem.getValue();
                if(controller.isHidden()){
                    continue;
                }
                view.addMenuItem(onInput, controller.getTitle());
            }

            view.render();
            view.clearMenuItems();
            String selectedAction = view.getSelectedAction();
            if ((!menuItems.containsKey(selectedAction) && selectedAction.length() > 0) ||
                    selectedAction.length() == 0) {
                view.invalidOptionSelected();
            } else {
                returnValue = ((Controller) menuItems.get(selectedAction)).execute();
            }

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
