package com.twu.biblioteca;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class MenuView extends View {
    private LinkedHashMap menuItems = new LinkedHashMap();
    public MenuView(InputOutputManger inputOutputManger) {
        super(inputOutputManger);
    }

    @Override
    public void render() {
        io.printLine("Main Menu.");
        Iterator menuItemSet = menuItems.entrySet().iterator();
        while (menuItemSet.hasNext()) {
            Map.Entry menuItem = (Map.Entry)menuItemSet.next();
            io.printLine(menuItem.getKey() + ". \t" + menuItem.getValue());
        }
    }

    public String getSelectedAction(){
        return io.readLine();
    }


    public void addMenuItem(String key, String menuTitle) {
        menuItems.put(key, menuTitle);
    }

    public void invalidOptionSelected() {
        io.printLine("Select a valid option!");
    }

    public void clearMenuItems() {
        menuItems.clear();
    }
}
