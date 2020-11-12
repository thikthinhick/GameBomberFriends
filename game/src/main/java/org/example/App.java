package org.example;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class App extends Application {
    static public Pane map;
    public static char [][] array = new ReadFileText().getArray();
    public static void main(String[] args) {
       launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hello World");
        Pane pane = new Pane();
        StackPane layout = new StackPane();
        List<String> arr = new ArrayList<>();
        for(int i = 1; i <= 4; i++) {
            arr.add("IMG/up" + i + ".png");
        }
        for(int i = 1; i <= 4; i++) {
            arr.add("IMG/down" + i + ".png");
        }
        for(int i = 1; i <= 4; i++) {
            arr.add("IMG/right" + i + ".png");
        }
        for(int i = 1; i <= 4; i++) {
            arr.add("IMG/left" + i + ".png");
        }
        paintMap a = new paintMap();
        map = a.getMap();
        System.out.println(map.getChildren().size());
        Character character = new Character(arr, 48, 48);
        pane.getChildren().addAll(map , character.getPane());
        layout.getChildren().add(pane);
        Scene scene = new Scene(layout, 720, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}