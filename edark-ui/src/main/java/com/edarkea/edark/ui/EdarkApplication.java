package com.edarkea.edark.ui;

import com.edarkea.edark.core.ui.MainConfigUIModel;
import com.edarkea.edark.ui.models.RootConfigModel;
import com.edarkea.edark.utils.JsonFileConverter;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javafx.application.Application;
import javafx.scene.Scene;
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
        JsonFileConverter<MainConfigUIModel> mainConfigConverter
                = new JsonFileConverter<>(MainConfigUIModel.class);

        JsonFileConverter<RootConfigModel> rootConfigConverter
                = new JsonFileConverter<>(RootConfigModel.class);

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
                = mainConfigConverter.fromJson(mainConfigStream,
                        StandardCharsets.ISO_8859_1);

        RootConfigModel rootConfig
                = rootConfigConverter.fromJson(rootConfigStream,
                        StandardCharsets.ISO_8859_1);

        BorderPane root = new BorderPane();
        root.setPadding(rootConfig.getPadding().getInsets());
        Scene scene = new Scene(root, rootConfig.getWidth(), rootConfig.getHeight());

        primaryStage.setScene(scene);
        primaryStage.setTitle(mainConfig.getHeader().getApplicationName());
        primaryStage.show();
    }

    private static InputStream loadTemplate(Class fromClass, String name) {
        return fromClass.getResourceAsStream(name);
    }

}
