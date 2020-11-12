package org.example;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Sprite {
    protected Pane pane;
    protected Pane pane1;
    protected Timeline t;
    protected List<ImageView> list;
    public Sprite(List<String> arr, int height, int width) throws FileNotFoundException {
        pane = new Pane();
        pane1 = new Pane();
        t = new Timeline();
        t.setCycleCount(Timeline.INDEFINITE);
        list = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            Image image = new Image(new FileInputStream(arr.get(i)));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(width);
            imageView.setFitHeight(height);
            list.add(imageView);
        }
    }

    public Pane getPane(int time) {
        pane.getChildren().add(pane1);
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            int finalI = i;
            t.getKeyFrames().add(new KeyFrame(
                    Duration.millis(sum),
                    (ActionEvent event) -> {
                        pane1.getChildren().setAll(list.get(finalI));
                    }
            ));
            sum += time;
        }
        t.play();
        return pane;
    }
}
