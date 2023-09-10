package com.edarkea.edark.ui.engine;

import com.edarkea.edark.ui.routers.Navigator;
import com.edarkea.edark.utils.ComponentValidation;
import java.io.IOException;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 *
 * @author Steeven Ayui
 */
public abstract class ComponentEngine<T> implements EventHandler, ControllerInterface {

    protected boolean resizable = true;
    protected boolean maximized = true;
    protected Pane pane;

    public abstract void init() throws IOException;

    public abstract Pane getRoot();

    public abstract void addNavigate(Navigator navigator);

    @Override
    public void handle(Event event) {
        final Object source = event.getSource();
        if (ComponentValidation.isNode(source)) {
            final Node node = (Node) source;
            final String action = (String) node.getUserData();
            switch (action) {
                case "onCreate" ->
                    this.onCreate();
                case "onRead" ->
                    this.onRead();
                case "onUpdate" ->
                    this.onUpdate();
                case "onDelete" ->
                    this.onDelete();
            }

        }
    }

    public boolean getResizable() {
        return this.resizable;
    }

    public boolean getMaximized() {
        return this.maximized;
    }


}
