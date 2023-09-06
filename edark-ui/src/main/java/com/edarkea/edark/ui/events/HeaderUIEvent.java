package com.edarkea.edark.ui.events;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

/**
 *
 * @author Steeven Ayui
 */
public class HeaderUIEvent implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        MenuItem item = (MenuItem) event.getSource();
        switch ((String) item.getUserData()) {
            case "onProfile":
                this.onProfile();
                break;
            case "onLogout":
                this.onLogout();
                break;
            case "onExit":
                this.onExit();
                break;
            default:
                System.err.println("Action Error");
        }
    }

    public void onProfile() {
        System.out.println("onProfile");
    }

    public void onLogout() {
        System.out.println("onLogout");
    }

    public void onExit() {
        System.exit(0);
    }

}
