package com.edarkea.edark.ui.controllers;

import atlantafx.base.controls.PasswordTextField;
import com.edarkea.edark.icons.EdarkIcon;
import com.edarkea.edark.icons.IconLoader;
import com.edarkea.edark.icons.IconSize;
import com.edarkea.edark.utils.ComponentUtil;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsOutlined;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 *
 * @author Steeven Ayui
 */
public class LoginController implements EventHandler {

    private boolean isShowPassword;
    private final FontIcon eye = new FontIcon(AntDesignIconsOutlined.EYE);
    private final FontIcon eyeOff = new FontIcon(AntDesignIconsOutlined.EYE_INVISIBLE);;
    private PasswordTextField password;
    private Label labelPassword;

    public LoginController() throws Exception {
        isShowPassword = false;
    }

    @Override
    public void handle(Event event) {
        final Object source = event.getSource();
        if (ComponentUtil.isNode(source)) {
            final Node node = (Node) source;
            String action = (String) node.getUserData();
            switch (action) {
                case "onLogin":
                    this.onLogin();
                    break;
                case "onShowPassword":
                    this.onShowPassword();
                    break;
            }

        }
    }

    private void onLogin() {
        System.out.println("onLogin");
    }

    private void onShowPassword() {
        if (this.password != null) {
            this.isShowPassword = !this.isShowPassword;
            if (this.isShowPassword) {
                labelPassword.setGraphic(eyeOff);
                this.password.setRevealPassword(true);
            } else {
                labelPassword.setGraphic(eye);
                this.password.setRevealPassword(false);
            }
        }
    }

    public void setPasswordTextField(PasswordTextField password) {
        this.password = password;
    }

    public void setLabelPassword(Label password) {
        this.labelPassword = password;
    }

}
