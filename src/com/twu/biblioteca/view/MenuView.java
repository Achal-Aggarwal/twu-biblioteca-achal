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
        io.printLine(io.formatLine("Main Menu.", 50));
        io.printLine(io.buildLine('-', 50));
        for (Map.Entry<String,String> menuItem : menuItems.entrySet()) {
            io.printLine(io.formatLine(new String[]{menuItem.getKey(), menuItem.getValue()}, 5, ""));
        }
    }

    public String getSelectedAction(){
        io.printString("Enter your selected option : ");
        return io.readLine();
    }


    public void addMenuItem(String key, String menuTitle) {
        menuItems.put(key, menuTitle);
    }

    public void invalidOptionSelected() {
        printMessage("Select a valid option!");
        io.printLine(io.buildLine('=', 50));
    }

    public void clearMenuItems() {
        menuItems.clear();
    }
}
