package org.example;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;


import java.io.File;
import java.io.IOException;
import java.net.URI;

public class Controller {
    static Pane parent;
    public Controller() {
        parent = new Pane();
        try {
            parent.getChildren().add(new FXMLLoader().load(getClass().getResource("sample.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Pane getParent(){
        return parent;
    }
}
