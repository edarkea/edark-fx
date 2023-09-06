package com.edarkea.edark.ui;

import com.edarkea.edark.core.enums.ActionEnum;
import com.edarkea.edark.core.ui.HeaderUIModel;
import com.edarkea.edark.core.ui.MainConfigUIModel;
import com.edarkea.edark.core.ui.OptionUIModel;
import com.edarkea.edark.ui.events.HeaderUIEvent;
import com.edarkea.edark.ui.models.RootConfigModel;
import com.edarkea.edark.utils.JsonFileConverter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Steeven Ayui
 */
public class EdarkApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        JsonFileConverter converter
                = new JsonFileConverter<>();

        InputStream mainConfigStream
                = EdarkApplication
                        .loadTemplate(
                                getClass(),
                                "/templates/config/main_config.json");

        InputStream rootConfigStream
                = EdarkApplication
                        .loadTemplate(
                                getClass(),
                                "/templates/config/root_config.json");

        MainConfigUIModel mainConfig
                = (MainConfigUIModel) converter.fromJson(MainConfigUIModel.class, mainConfigStream,
                        StandardCharsets.ISO_8859_1);

        RootConfigModel rootConfig
                = (RootConfigModel) converter.fromJson(RootConfigModel.class, rootConfigStream,
                        StandardCharsets.ISO_8859_1);

        BorderPane root = new BorderPane();
        root.setPadding(rootConfig.getPadding().getInsets());
        Scene scene = new Scene(root, rootConfig.getWidth(), rootConfig.getHeight());

        //Read Header
        BorderPane headerComponent
                = EdarkApplication.loadHeaderComponent(getClass(),
                        "/templates/content/header_content.fxml",
                        mainConfig.getHeader());
        root.setTop(headerComponent);

        primaryStage.setScene(scene);
        primaryStage.setTitle(mainConfig.getHeader().getApplicationName());
        primaryStage.show();
    }

    private static InputStream loadTemplate(Class fromClass, String name) {
        return fromClass.getResourceAsStream(name);
    }

    private static BorderPane loadHeaderComponent(Class fromClass, String name, HeaderUIModel header) throws IOException {
        FXMLLoader loader = new FXMLLoader(fromClass.getResource(name));
        BorderPane pane = loader.load();

        Label appName = (Label) loader.getNamespace().get("lbl_app_name");
        appName.setText(header.getApplicationName());

        MenuButton menuButton = (MenuButton) loader.getNamespace().get("mb_user");
        menuButton.setText(header.getUser().getName());
        HeaderUIEvent events = new HeaderUIEvent();
        for (OptionUIModel option : header.getUser().getOptions()) {
            if (option.getType() == ActionEnum.ACTION) {
                MenuItem menuItem = new MenuItem(option.getName());
                menuItem.setUserData(option.getAction());
                menuItem.setOnAction(events);
                menuButton.getItems().add(menuItem);
            }

            if (option.getType() == ActionEnum.DIVIDER) {
                SeparatorMenuItem divider = new SeparatorMenuItem();
                menuButton.getItems().add(divider);
            }
        }
        return pane;
    }

}
