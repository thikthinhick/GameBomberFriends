package org.example;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setTitle("Game BomberMan");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
