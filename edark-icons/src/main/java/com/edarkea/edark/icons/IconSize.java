package com.edarkea.edark.icons;

/**
 *
 * @author Steeven Ayui
 */
public enum IconSize {

    X16(16f),
    X20(20f),
    X24(24f),
    X32(32f),
    X40(40f), 
    X48(48f),
    X64(64f),
    X128(128f),;

    private final float size;

    IconSize(float size) {
        this.size = size;
    }

    public float getSize() {
        return size;
    }

}
