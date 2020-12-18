package org.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class menuScene implements Initializable {

    @FXML
    AnchorPane mainPane;
    @FXML
    public void Action1(MouseEvent e) throws IOException {
        Controller.parent.getChildren().clear();
        Controller.parent.getChildren().add(new FXMLLoader().load(getClass().getResource("Map.fxml")));
    }
    @FXML
    public void Action2(MouseEvent e) {
        try {
            Controller.parent.getChildren().set(0, new GamePlay(true, 2).getPane());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    @FXML
    public void Action3(MouseEvent e) {
        System.out.println("Hello Action2");
    }
    @FXML
    public void Action4(MouseEvent e) {
        System.out.println("Hello Action2");
    }
    @FXML
    public void Action5(MouseEvent e) {
        System.out.println("Hello Action2");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
