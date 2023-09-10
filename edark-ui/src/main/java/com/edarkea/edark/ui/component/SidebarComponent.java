package com.edarkea.edark.ui.component;

import com.edarkea.edark.ui.engine.ComponentEngine;
import com.edarkea.edark.ui.models.MenuModel;
import com.edarkea.edark.ui.routers.Navigator;
import com.edarkea.edark.utils.ComponentResource;
import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.kordamp.ikonli.antdesignicons.AntDesignIconsFilled;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 *
 * @author Steeven Ayui
 */
public class SidebarComponent extends ComponentEngine {
    
    private final String PATH = "/templates/layout/sider_content.fxml";
    private ComponentResource<BorderPane> component;
    private Navigator navigator;
    
    public SidebarComponent() throws IOException {
        this.maximized = true;
        this.resizable = true;
        init();
    }
    
    @Override
    public void init() throws IOException {
        this.component = new ComponentResource(getClass(), PATH);
        final TreeView menu = this.component.getSubComponent("menuid", TreeView.class);

        //Creating tree items
        TreeItem root1 = new TreeItem("Programming Languages");
        root1.setGraphic(new FontIcon(AntDesignIconsFilled.ANDROID));
        TreeItem item1 = new TreeItem("Java");
        item1.setValue(new MenuModel("Hola soy un menu","key"));
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
        
        menu.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                TreeItem<String> selectedItem = (TreeItem<String>) newValue;
                System.out.println("Selected Text : " + selectedItem.getValue());
            }
        });

        //Creating a TreeView item
        menu.setRoot(base);
    }
    
    @Override
    public Pane getRoot() {
        return this.component.getRootComponent();
    }
    
    @Override
    public void addNavigate(Navigator navigator) {
        this.navigator = navigator;
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
