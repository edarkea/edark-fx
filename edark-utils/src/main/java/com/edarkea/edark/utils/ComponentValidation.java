package com.edarkea.edark.utils;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;

/**
 *
 * @author Steeven Ayui
 */
public class ComponentValidation {

    public static boolean isButton(Object source) {
        return source instanceof Button;
    }

    public static boolean isMenuItem(Object source) {
        return source instanceof MenuItem;
    }

    public static boolean isLabel(Object source) {
        return source instanceof Label;
    }

    public static boolean isNode(Object source) {
        return source instanceof Node;
    }
    
    public static boolean isTreeItem(Object source) {
        return source instanceof TreeItem;
    }

}
