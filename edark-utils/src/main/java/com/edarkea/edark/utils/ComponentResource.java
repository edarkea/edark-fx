package com.edarkea.edark.utils;

import java.io.IOException;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author Steeven Ayui
 */
public class ComponentResource<T> {

    private final FXMLLoader loader;
    private final T root;

    public ComponentResource(Class<? extends Class> source, String path) throws IOException {
        this.loader = new FXMLLoader(source.getResource(path));
        root = loader.load();
    }

    public <K> K getSubComponent(String id, Class<K> type) {
        Object obj = loader.getNamespace().get(id);
        if (type.isInstance(obj)) {
            return type.cast(obj);
        }
        throw new IllegalArgumentException("Invalid Cast Object");
    }
    
    public T getRootComponent(){
        return root;
    }
}
