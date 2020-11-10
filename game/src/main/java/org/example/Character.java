package org.example;

import javafx.animation.KeyFrame;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Character extends Sprite {
    Pane pane2 = null;
    Pane pane3 = null;
    Pane pane4 = null;
    Pane BigPane = null;
    Button button;
    public Character(List<String> arr, int height, int width) throws FileNotFoundException {
        super(arr, height, width);
        button = new Button();
        pane2 = new Pane();
        pane3 = new Pane();
        pane4 = new Pane();
        BigPane = new Pane();
        BigPane.getChildren().add(pane);
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            int finalI = i;
            t.getKeyFrames().add(new KeyFrame(
                    Duration.millis(sum),
                    (ActionEvent event) -> {
                        pane1.getChildren().setAll(list.get(finalI));
                    }
            ));
            sum += 100;
        }
        sum = 0;

        for (int i = 4; i < 8; i++) {
            int finalI = i;
            t.getKeyFrames().add(new KeyFrame(
                    Duration.millis(sum),
                    (ActionEvent event) -> {
                        pane2.getChildren().setAll(list.get(finalI));
                    }
            ));
            sum += 100;
        }
        sum = 0;
        for (int i = 8; i < 12; i++) {
            int finalI = i;
            t.getKeyFrames().add(new KeyFrame(
                    Duration.millis(sum),
                    (ActionEvent event) -> {
                        pane3.getChildren().setAll(list.get(finalI));
                    }
            ));
            sum += 100;
        }
        sum = 0;
        for (int i = 12; i < 16; i++) {
            int finalI = i;
            t.getKeyFrames().add(new KeyFrame(
                    Duration.millis(sum),
                    (ActionEvent event) -> {
                        pane4.getChildren().setAll(list.get(finalI));
                    }
            ));
            sum += 100;
        }
        sum = 0;
        for (int i = 12; i < 16; i++) {
            int finalI = i;
            t.getKeyFrames().add(new KeyFrame(
                    Duration.millis(sum),
                    (ActionEvent event) -> {
                        pane4.getChildren().setAll(list.get(finalI));
                    }
            ));
            sum += 100;
        }
        pane.getChildren().add(button);
        pane.getChildren().add(pane1);
        button.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().toString() == "RIGHT") {
                    if (!pane.getChildren().get(pane.getChildren().size() - 1).equals(pane3)) {
                        pane.getChildren().remove(pane.getChildren().size() - 1);
                        pane.getChildren().add(pane3);
                    }
                    TranslateTransition transition = new TranslateTransition(Duration.millis(40), pane);
                    transition.setToX(pane.getTranslateX() + 12);
                    transition.play();
                    t.play();
                } else if (keyEvent.getCode().toString() == "LEFT") {
                    if (!pane.getChildren().get(pane.getChildren().size() - 1).equals(pane4)) {
                        pane.getChildren().remove(pane.getChildren().size() - 1);
                        pane.getChildren().add(pane4);
                    }
                    TranslateTransition transition = new TranslateTransition(Duration.millis(40), pane);
                    transition.setToX(pane.getTranslateX() - 12);
                    transition.play();
                    t.play();
                } else if (keyEvent.getCode().toString() == "UP") {
                    if (!pane.getChildren().get(pane.getChildren().size() - 1).equals(pane1)) {
                        pane.getChildren().remove(pane.getChildren().size() - 1);
                        pane.getChildren().add(pane1);
                    }
                    TranslateTransition transition = new TranslateTransition(Duration.millis(40), pane);
                    transition.setToY(pane.getTranslateY() - 12);
                    transition.play();
                    t.play();
                } else if (keyEvent.getCode().toString() == "DOWN") {
                    if (!pane.getChildren().get(pane.getChildren().size() - 1).equals(pane2)) {
                        pane.getChildren().remove(pane.getChildren().size() - 1);
                        pane.getChildren().add(pane2);
                    }
                    TranslateTransition transition = new TranslateTransition(Duration.millis(40), pane);
                    transition.setToY(pane.getTranslateY() + 12);
                    transition.play();
                    t.play();
                }
                if (keyEvent.getCode().toString() == "ENTER") {
                    RememberMap c = new RememberMap();
                    int BombX = (int) pane.getTranslateX();
                    int BombY = (int) pane.getTranslateY();
                    ImageView bomb = c.getImageView(BombX, BombY, "IMG/bomb.png");
                    bomb.setStyle("-fx-view-order: 6");
                    BigPane.getChildren().add(bomb);
                    Timer timer = new Timer();
                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(() -> {
                                BigPane.getChildren().remove(pane.getChildren().size() - 1);
                                Fire newFire = null;
                                try {
                                    newFire = new Fire();
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                                Pane paneBomb = newFire.getPane();
                                BigPane.getChildren().add(paneBomb);
                                paneBomb.setTranslateX(BombX - 96);
                                paneBomb.setTranslateY(BombY - 96);
                                Timer timer = new Timer();
                                timer.scheduleAtFixedRate(new TimerTask() {
                                    @Override
                                    public void run() {
                                        Platform.runLater(() -> {
                                            BigPane.getChildren().remove(pane.getChildren().size() - 1);
                                        });
                                        Toolkit.getDefaultToolkit().beep();
                                        timer.cancel();
                                        timer.purge();
                                    }
                                }, 325, 325);
                            });
                            Toolkit.getDefaultToolkit().beep();
                            timer.cancel();
                            timer.purge();
                        }
                    }, 3000, 1000);
                }
            }
        });
        button.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                t.stop();
                System.out.println("stop");
            }
        });
        t.play();
    }
    public Pane getPane() {
        return BigPane;
    }
}