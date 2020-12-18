package org.example;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Monster1 extends Monster {
    public Pane pane;
    Timer timer;
    public Monster1(int x, int y, List<String> arr) {
        final int[] X = {x};
        final int[] Y = {y};
        pane = new Pane();
        pane.setPrefSize(48, 48);
        try {
            pane.getChildren().add(new Sprite(arr.subList(68, 74), 48, 48).getPane(150));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pane.setTranslateX(x);
        pane.setTranslateY(y);
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    TranslateTransition transition = new TranslateTransition(Duration.millis(800), pane);
                    int pointX = (int) pane.getTranslateX() / 48;
                    int pointY = (int) pane.getTranslateY() / 48;
                    while (true) {
                        int random = (int) Math.floor(Math.random() * 4) + 1;
                        if (random == 1 && GamePlay.array[pointY - 1][pointX] == '.') {
                            Y[0] -= 48;
                            transition.setToY(Y[0]);
                            break;
                        }
                        if (random == 2 && GamePlay.array[pointY + 1][pointX] == '.') {
                            Y[0] += 48;
                            transition.setToY(Y[0]);
                            break;
                        }
                        if (random == 3 && GamePlay.array[pointY][pointX + 1] == '.') {
                            X[0] += 48;
                            transition.setToX(X[0]);
                            try {
                                pane.getChildren().set(0, new Sprite(arr.subList(52, 60), 48, 48).getPane(150));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                        if (random == 4 && GamePlay.array[pointY][pointX - 1] == '.') {
                            X[0] -= 48;
                            transition.setToX(X[0]);
                            try {
                                pane.getChildren().set(0, new Sprite(arr.subList(60, 68), 48, 48).getPane(150));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                    }
                    transition.play();
                    if (pane.getTranslateX() / 48 == (int) Character.pointX / 48 && pane.getTranslateY() / 48 == (int) Character.pointY / 48) {
                        Character.health--;
                    }
                });
            }

        }, 0, 900);
        GamePlay.paneMonster.getChildren().add(pane);
    }
    public void stop() {
        timer.cancel();
        timer.purge();
    }
}
