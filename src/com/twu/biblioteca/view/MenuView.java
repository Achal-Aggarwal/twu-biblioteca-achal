package com.twu.biblioteca.view;

import com.twu.biblioteca.io.InputOutputManger;

import java.util.LinkedHashMap;
import java.util.Map;

public class MenuView extends View {
    private LinkedHashMap<String,String> menuItems = new LinkedHashMap<String,String>();
    public MenuView(InputOutputManger inputOutputManger) {
        super(inputOutputManger);
    }

    @Override
    public void render() {
        io.printLine("Main Menu.");
        for (Map.Entry<String,String> menuItem : menuItems.entrySet()) {
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
