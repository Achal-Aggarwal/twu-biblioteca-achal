package com.twu.biblioteca.controller;

import com.twu.biblioteca.io.InputOutputManger;
import com.twu.biblioteca.view.MenuView;

import java.util.*;

public class MenuController extends Controller {
    private LinkedHashMap<String, Controller> menuItems = new LinkedHashMap();
    private MenuView view;
    public MenuController(InputOutputManger inputOutputManger) {
        view = new MenuView(inputOutputManger);
    }

    public boolean execute() {
        boolean returnValue = true;
        while(returnValue) {
            for (Map.Entry<String,Controller> menuItem : menuItems.entrySet()) {
                Controller controller =  menuItem.getValue();
                if (controller.isHidden()) {
                    continue;
                }
                view.addMenuItem(menuItem.getKey(), controller.getTitle());
            }

            view.render();
            view.clearMenuItems();

            String selectedAction = view.getSelectedAction();

            if ((!menuItems.containsKey(selectedAction) && selectedAction.length() > 0) ||
                    selectedAction.length() == 0) {
                view.invalidOptionSelected();
            } else if(menuItems.containsKey(selectedAction) && menuItems.get(selectedAction).isHidden()) {
                view.invalidOptionSelected();
            } else {
                returnValue = menuItems.get(selectedAction).execute();
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
