package com.edarkea.edark.ui.models;

import javafx.geometry.Insets;

/**
 *
 * @author Steeven Ayui
 */
public class RootConfigModel {

    private double width;
    private double height;

    private Padding padding;

    public RootConfigModel() {
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Padding getPadding() {
        return padding;
    }

    public void setPadding(Padding padding) {
        this.padding = padding;
    }

    public class Padding {

        private double bottom;
        private double left;
        private double right;
        private double top;

        public Padding() {
        }

        public Insets getInsets() {
            return new Insets(top, right, bottom, left);
        }

        public double getBottom() {
            return bottom;
        }

        public void setBottom(double bottom) {
            this.bottom = bottom;
        }

        public double getLeft() {
            return left;
        }

        public void setLeft(double left) {
            this.left = left;
        }

        public double getRight() {
            return right;
        }

        public void setRight(double right) {
            this.right = right;
        }

        public double getTop() {
            return top;
        }

        public void setTop(double top) {
            this.top = top;
        }
        
        
        
    }
}
