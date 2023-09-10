package com.edarkea.edark.ui.routers;

import com.edarkea.edark.ui.engine.ComponentEngine;
import com.edarkea.edark.ui.engine.ComponentKey;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Steeven Ayui
 */
public class Navigator {

    private final Map<String, ComponentEngine> components = new HashMap();
    private ComponentEngine currentComponent;
    private final Stage primaryStage;

    public Navigator(Stage primaryStage, ComponentKey key) {
        this.primaryStage = primaryStage;
        this.currentComponent = key.component;
        this.registerComponent(key);
    }

    public void registerComponent(ComponentKey... keys) {
        for (ComponentKey key : keys) {
            this.components.put(key.key, key.component);
        }
    }

    public void setupComponent() {
        try {
            this.currentComponent.init();
            Scene scene = new Scene(currentComponent.getRoot());
            this.primaryStage.setResizable(currentComponent.getResizable());
            this.primaryStage.setScene(scene);

            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
            primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);

            //this.primaryStage.setMaximized(currentComponent.getMaximized());

        } catch (IOException ex) {
            System.out.println("Error to load component: " + ex.getMessage());
        }
    }

    public void navigate(String key) {
        if (components.get(key) != null) {
            this.currentComponent = components.get(key);
            this.setupComponent();
        }
    }
}
