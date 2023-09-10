package com.edarkea.edark.ui;

import atlantafx.base.theme.NordLight;
import com.edarkea.edark.ui.component.AuthComponent;
import com.edarkea.edark.ui.component.MainComponent;
import com.edarkea.edark.ui.config.AppSettings;
import com.edarkea.edark.ui.engine.ComponentEngine;
import com.edarkea.edark.ui.engine.ComponentKey;
import com.edarkea.edark.ui.routers.Navigator;
import com.edarkea.edark.utils.JsonFileConverter;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Steeven Ayui
 */
public class EdarkApplication extends Application {

    private Navigator navigator;
    private AppSettings appSettings;

    public EdarkApplication() {
        try {
            JsonFileConverter converter
                    = new JsonFileConverter<>();

            InputStream configStream
                    = EdarkApplication
                            .loadTemplate(
                                    getClass(),
                                    "/templates/config/appsettings.json");

            appSettings
                    = (AppSettings) converter.fromJson(AppSettings.class, configStream,
                            StandardCharsets.ISO_8859_1);

        } catch (Exception ex) {
            System.out.println("Error to Start Application " + ex.getMessage());
        }
    }

    private void loadComponentEngine(Stage primaryStage, String key, ComponentEngine component) {
        var keyComponent = new ComponentKey(key, component);
        navigator = new Navigator(primaryStage, keyComponent);
        component.addNavigate(navigator);
        navigator.setupComponent();
    }

    private void registerComponent(String key, ComponentEngine component) {
        this.navigator.registerComponent(
                new ComponentKey(key, component)
        );
        component.addNavigate(navigator);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Application.setUserAgentStylesheet(new NordLight().getUserAgentStylesheet());
        loadComponentEngine(primaryStage, "auth_component", new AuthComponent(appSettings));
        registerComponent("main_component", new MainComponent());

        primaryStage.setTitle(appSettings.applicationName);
        primaryStage.getIcons().add(new Image(appSettings.pathLogo));
        primaryStage.show();

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);

    }

    private static InputStream loadTemplate(Class fromClass, String name) {
        return fromClass.getResourceAsStream(name);
    }
    
}
