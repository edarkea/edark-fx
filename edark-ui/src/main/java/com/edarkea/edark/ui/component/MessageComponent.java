package com.edarkea.edark.ui.component;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsFilled;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 *
 * @author Steeven Ayui
 */
public class MessageComponent {

    public static void showDialog(AlertType type, String tittle, String message) {
        Alert alert = new Alert(type);
        alert.setHeaderText(tittle);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
