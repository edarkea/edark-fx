package com.edarkea.edark.ui.component;

import com.edarkea.edark.ui.engine.ComponentEngine;
import com.edarkea.edark.ui.routers.Navigator;
import com.edarkea.edark.utils.ComponentResource;
import com.edarkea.edark.utils.ComponentValidation;
import java.io.IOException;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 *
 * @author Steeven Ayui
 */
public class MainComponent extends ComponentEngine {

    private final String PATH = "/templates/layout/";
    private ComponentResource<BorderPane> component;
    private Navigator navigator;

    private ComponentResource<BorderPane> header;

    public MainComponent() {

    }

    @Override
    public void init() throws IOException {
        this.component = new ComponentResource(getClass(), PATH + "main_content.fxml");
        this.header = new ComponentResource(getClass(), PATH + "header_content.fxml");
        this.maximized = true;
        this.resizable = true;

        BorderPane mainPane = (BorderPane) component.getRootComponent();
        mainPane.setTop(this.header.getRootComponent());

        final MenuItem miProfile = header.getSubComponent("mi_profile", MenuItem.class);
        miProfile.setGraphic(new FontIcon(AntDesignIconsOutlined.USER));
        miProfile.setUserData("onProfile");
        miProfile.setOnAction(this);

        final MenuItem miLogout = header.getSubComponent("mi_logout", MenuItem.class);
        miLogout.setGraphic(new FontIcon(AntDesignIconsOutlined.LOGOUT));
        miLogout.setOnAction(e -> {
            this.navigator.navigate("auth_component");
        });

        final MenuItem miExit = header.getSubComponent("mi_exit", MenuItem.class);
        miExit.setGraphic(new FontIcon(AntDesignIconsOutlined.CLOSE));
        miExit.setOnAction(e -> {
            System.exit(0);
        });

    }

    @Override
    public void handle(Event event) {
        String action = "";
        if (ComponentValidation.isMenuItem(event.getSource())) {
            MenuItem menuItem = (MenuItem) event.getSource();
            action = menuItem.getUserData() == null ? "" : (String) menuItem.getUserData();
        }

        switch (action) {
            case "onProfile":
                break;
            default:
                MessageComponent.showDialog(Alert.AlertType.ERROR, "Error", "Función no implementada!");
        }

    }

    @Override
    public Pane getRoot() {
        return this.component.getRootComponent();
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onRead() {
    }

    @Override
    public void onUpdate() {
    }

    @Override
    public void onDelete() {
    }

    @Override
    public void addNavigate(Navigator navigator) {
        this.navigator = navigator;
    }

}
