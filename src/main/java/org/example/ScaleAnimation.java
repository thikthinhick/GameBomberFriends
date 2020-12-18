package org.example;

import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class ScaleAnimation {
    private Pane pane;
    public ScaleAnimation(int sizeX, int sizeY, int x, int y, String url ) {
        pane = new Pane();
        ImageView item = new RememberMap().getImageView(sizeX, sizeY, x, y, url);
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(1000), item);
        scaleTransition.setToX(1.2f);
        scaleTransition.setToY(1.2f);
        scaleTransition.setCycleCount(Animation.INDEFINITE);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();
        pane.getChildren().add(item);
    }
    public Pane getScaleAnimation() {
        return pane;
    }
}
