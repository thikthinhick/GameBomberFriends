package org.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuMap implements Initializable {
    @FXML
    public void button1(MouseEvent e) {
        try {
            Controller.parent.getChildren().clear();
            Controller.parent.getChildren().add(new GamePlay(false, 1).getPane());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    @FXML
    public void button2(MouseEvent e) {
        try {
            Controller.parent.getChildren().set(0, new GamePlay(false, 2).getPane());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    @FXML
    public void button3(MouseEvent e) {
        try {
            Controller.parent.getChildren().set(0, new GamePlay(false, 3).getPane());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    @FXML
    public void button4(MouseEvent e) {
        try {
            Controller.parent.getChildren().set(0, new GamePlay(false, 4).getPane());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    @FXML
    public void button5(MouseEvent e) {

    }
    @FXML
    public void button6(MouseEvent e) {

    }
    @FXML
    public void button7(MouseEvent e) {

    }
    @FXML
    public void button8(MouseEvent e) {

    }
    @FXML
    public void buttonExit(MouseEvent e) {
        try {
            Controller.parent.getChildren().set(0, new FXMLLoader().load(getClass().getResource("sample.fxml")));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
