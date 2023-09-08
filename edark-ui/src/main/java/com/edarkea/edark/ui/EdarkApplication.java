package com.edarkea.edark.ui;

import atlantafx.base.theme.CupertinoLight;
import com.edarkea.edark.core.enums.ActionEnum;
import com.edarkea.edark.core.ui.FooterUIModel;
import com.edarkea.edark.core.ui.HeaderUIModel;
import com.edarkea.edark.core.ui.MainConfigUIModel;
import com.edarkea.edark.core.ui.OptionUIModel;
import com.edarkea.edark.core.ui.UserUIModel;
import com.edarkea.edark.icons.EdarkIcon;
import com.edarkea.edark.icons.IconLoader;
import com.edarkea.edark.icons.IconSize;
import com.edarkea.edark.ui.controllers.HeaderUIController;
import com.edarkea.edark.ui.models.RootConfigModel;
import com.edarkea.edark.utils.JsonFileConverter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
        Application.setUserAgentStylesheet(new CupertinoLight().getUserAgentStylesheet());
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
                        "/templates/layout/header_content.fxml",
                        mainConfig.getHeader());
        root.setTop(headerComponent);
        
        HBox footerComponent
                = EdarkApplication.loadFooterComponent(getClass(),
                        "/templates/layout/footer_content.fxml",
                        mainConfig.getFooter());
        
        root.setBottom(footerComponent);
        
        BorderPane siderComponent
                = EdarkApplication.loadSidebarComponent(getClass(),
                        "/templates/layout/sider_content.fxml",
                        mainConfig.getHeader().getUser());
        
        root.setLeft(siderComponent);
        
        BorderPane centerComponent
                = EdarkApplication.loadCenterComponent(
                        getClass(),
                        "/templates/layout/center_content.fxml");
        
        root.setCenter(centerComponent);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle(mainConfig.getHeader().getApplicationName());
        primaryStage.setMaximized(true);
        primaryStage.getIcons().add(new Image("/img/logoes.png"));
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
        HeaderUIController events = new HeaderUIController();
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
    
    private static HBox loadFooterComponent(Class fromClass, String name, FooterUIModel footer) throws IOException {
        FXMLLoader loader = new FXMLLoader(fromClass.getResource(name));
        HBox pane = loader.load();
        
        Label appName = (Label) loader.getNamespace().get("lbl_desciption");
        LocalDate date = LocalDate.now();
        appName.setText(
                footer.getDescription().replace("{{year}}",
                        String.valueOf(date.getYear())));
        
        return pane;
    }
    
    private static BorderPane loadSidebarComponent(Class fromClass, String name, UserUIModel user) throws IOException, Exception {
        FXMLLoader loader = new FXMLLoader(fromClass.getResource(name));
        BorderPane pane = loader.load();
        
        Label userFullName = (Label) loader.getNamespace().get("lbl_full_name");
        userFullName.setText(user.getName());
        Label userRoleName = (Label) loader.getNamespace().get("lbl_role_name");
        userRoleName.setText(user.getRole());
        
        ImageView imageView = (ImageView) loader.getNamespace().get("img_profile");
        imageView.setImage(IconLoader.getImageIcon(IconSize.X48, EdarkIcon.user));

        TreeItem root1 = new TreeItem("Programming Languages");
        TreeItem item1 = new TreeItem("Java");
        TreeItem item2 = new TreeItem("Python");
        TreeItem item3 = new TreeItem("C++");
        //Adding elements to root1
        root1.getChildren().addAll(item1, item2, item3);
        TreeItem root2 = new TreeItem("NoSQL Databases");
        TreeItem item4 = new TreeItem("Neo4j");
        TreeItem item5 = new TreeItem("HBase");
        TreeItem item6 = new TreeItem("Cassandra");
        //Adding elements to root2
        root2.getChildren().addAll(item4, item5, item6);
        TreeItem root3 = new TreeItem("Bigdata & Analytics");
        TreeItem item7 = new TreeItem("Hadoop");
        TreeItem item8 = new TreeItem("Mahout");
        TreeItem item9 = new TreeItem("Hive");
        //Adding elements to root2
        root3.getChildren().addAll(item7, item8, item9);
        //list View for educational qualification
        TreeItem<String> base = new TreeItem<String>("Technologies");
        base.setExpanded(true);
        base.getChildren().addAll(root1, root2, root3);
        
        TreeView view = (TreeView) loader.getNamespace().get("menuid");
        view.setRoot(base);
        
        return pane;
    }
    
    private static BorderPane loadCenterComponent(Class fromClass, String name) throws IOException {
        FXMLLoader loader = new FXMLLoader(fromClass.getResource(name));
        BorderPane pane = loader.load();
        
        TabPane appName = (TabPane) loader.getNamespace().get("tabid");
        appName.getTabs().clear();
        Tab welcome = loadWelcomeComponent(fromClass, "/templates/layout/welcome_content.fxml");
        appName.getTabs().add(welcome);
        return pane;
    }
    
    private static Tab loadWelcomeComponent(Class fromClass, String name) throws IOException {
        FXMLLoader loader = new FXMLLoader(fromClass.getResource(name));
        BorderPane pane = loader.load();
        Tab tab = new Tab();
        tab.setText("Bienvenido/a");
        tab.setContent(pane);
        return tab;
    }
    
}
