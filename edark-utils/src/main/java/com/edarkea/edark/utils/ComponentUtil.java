package com.edarkea.edark.utils;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

/**
 *
 * @author Steeven Ayui
 */
public class ComponentUtil {

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

}
