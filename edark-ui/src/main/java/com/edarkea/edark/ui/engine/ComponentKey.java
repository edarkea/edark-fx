package com.edarkea.edark.ui.engine;

/**
 *
 * @author Steeven Ayui
 */
public class ComponentKey {

    public String key;
    public ComponentEngine component;

    public ComponentKey(String key, ComponentEngine component) {
        this.key = key;
        this.component = component;
    }
}
