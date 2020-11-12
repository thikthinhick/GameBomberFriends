package org.example;

import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Fire{
    private Pane pane;

    public Fire() throws FileNotFoundException {
        pane = new Pane();
        List<String> arr = new ArrayList<>();
        for(int i = 1; i <= 10; i++) {
            arr.add("IMG/fire" + i + ".png");
        }
        for(int i = 0; i <= 4; i ++) {
            Sprite sprite = new Sprite(arr, 48, 48);
            Pane pane1 = sprite.getPane(50);
            pane.getChildren().add(pane1);
            pane1.setTranslateX(96);
            pane1.setTranslateY(i * 48);
        }
        for(int i = 0; i <= 4; i ++) {
            Sprite sprite = new Sprite(arr, 48, 48);
            Pane pane1 = sprite.getPane(50);
            pane.getChildren().add(pane1);
            pane1.setTranslateX(48 * i);
            pane1.setTranslateY(96);
        }
    }
    public Pane getPane(){
        return pane;
    }
}
