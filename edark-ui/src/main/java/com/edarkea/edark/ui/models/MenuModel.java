package com.edarkea.edark.ui.models;

import org.kordamp.ikonli.javafx.FontIcon;

/**
 *
 * @author Steeven Ayui
 */
public class MenuModel {

    private String name;
    private String key;
    private FontIcon icon;

    public MenuModel(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public String getKey() {
        return key;
    }


    public String getName() {
        return name;
    }

    public FontIcon getIcon() {
        return icon;
    }

    public void setIcon(FontIcon icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
