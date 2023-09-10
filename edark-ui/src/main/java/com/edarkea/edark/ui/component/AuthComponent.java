package com.edarkea.edark.ui.component;

import atlantafx.base.controls.PasswordTextField;
import com.edarkea.edark.icons.EdarkIcon;
import com.edarkea.edark.icons.IconLoader;
import com.edarkea.edark.icons.IconSize;
import com.edarkea.edark.ui.config.AppSettings;
import com.edarkea.edark.ui.engine.ComponentEngine;
import com.edarkea.edark.ui.routers.Navigator;
import com.edarkea.edark.utils.ComponentResource;
import java.io.IOException;
import java.time.LocalDate;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 *
 * @author Steeven Ayui
 */
public class AuthComponent extends ComponentEngine {

    private final String PATH = "/templates/auth/login_form.fxml";
    private ComponentResource<BorderPane> component;
    public Button btnLogin;
    final private AppSettings appSettings;
    private Navigator navigator;

    public AuthComponent(final AppSettings appSettings) throws IOException {
        this.appSettings = appSettings;
    }

    @Override
    public void init() throws IOException {
        this.component = new ComponentResource(getClass(), PATH);
        this.maximized = false;
        this.resizable = false;
        
        final ImageView imgUser = component.getSubComponent("img_user", ImageView.class);
        imgUser.setImage(IconLoader.getImageIcon(IconSize.X128, EdarkIcon.user));

        final Label imgUserName = component.getSubComponent("icn_username", Label.class);
        imgUserName.setGraphic(new FontIcon(AntDesignIconsOutlined.USER));

        final Label imgPassword = component.getSubComponent("icn_password", Label.class);
        imgPassword.setGraphic(new FontIcon(AntDesignIconsOutlined.LOCK));

        this.btnLogin = component.getSubComponent("btn_login", Button.class);
        this.btnLogin.setGraphic(new FontIcon(AntDesignIconsOutlined.LOGIN));
        this.btnLogin.setUserData("onLogin");
        this.btnLogin.setOnMouseClicked(event -> {
            this.navigator.navigate("main_component");
            this.handle(event);
        });

        final PasswordTextField ptf = component.getSubComponent("txtPassword", PasswordTextField.class);
        final Label labelPassword = component.getSubComponent("btn_showpasswod", Label.class);
        labelPassword.setGraphic(new FontIcon(AntDesignIconsOutlined.EYE_INVISIBLE));
        labelPassword.setOnMouseClicked(e -> {
            ptf.setRevealPassword(!ptf.getRevealPassword());
            labelPassword.setGraphic(
                    ptf.getRevealPassword()
                    ? new FontIcon(AntDesignIconsOutlined.EYE)
                    : new FontIcon(AntDesignIconsOutlined.EYE_INVISIBLE));
        });
        final Label appName = component.getSubComponent("lbl_desciption", Label.class);
        final LocalDate date = LocalDate.now();
        appName.setText(
                this.appSettings.copyRight.replace("{{year}}",
                        String.valueOf(date.getYear())));

    }

    public Pane getRoot() {
        return this.component.getRootComponent();
    }

    public void addNavigate(Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void handle(Event event) {
        super.handle(event);
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

}
