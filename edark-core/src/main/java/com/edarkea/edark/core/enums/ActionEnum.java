package com.edarkea.edark.core.enums;

/**
 *
 * @author Steeven Ayui
 */
public enum ActionEnum {
    ACTION("action"), 
    LINK("link"), 
    DIVIDER("divider"), 
    OTHER("other"),;

    private final String text;

    ActionEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
