package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        Scene scene = new Scene(controller.getParent(), 896, 590);
        scene.getStylesheets().add(getClass().getResource("Application.css").toExternalForm());
        primaryStage.setTitle("Server");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}