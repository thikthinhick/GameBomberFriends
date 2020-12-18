package org.example;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
public class Monster2 extends Monster{
    public Pane pane;
    Timer timer;
    public Monster2(int x, int y, List<String> arr) {
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
                    int l = new LocationMonster((int) Character.pointX, (int) Character.pointY, (int) pane.getTranslateY() / 48 * 15 + (int) pane.getTranslateX() / 48).getLocations();
                    TranslateTransition transition = new TranslateTransition(Duration.millis(800), pane);
                    if (l != 0) {
                        int i = l / 15;
                        int j = l % 15;
                        if((int)pane.getTranslateX() + 10 < j * 48) {
                            try {
                                pane.getChildren().set(0, new Sprite(arr.subList(74, 80), 48, 48).getPane(150));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                        if((int)pane.getTranslateX() - 10 > j * 48){
                            try {
                                pane.getChildren().set(0, new Sprite(arr.subList(68, 74), 48, 48).getPane(150));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                        transition.setToX(j * 48);
                        transition.setToY(i * 48);
                        X[0] = (int)pane.getTranslateX();
                        Y[0] = (int)pane.getTranslateY();
                    } else {
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
                                    pane.getChildren().set(0, new Sprite(arr.subList(74, 80), 48, 48).getPane(150));
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                            if (random == 4 && GamePlay.array[pointY][pointX - 1] == '.') {
                                X[0] -= 48;
                                transition.setToX(X[0]);
                                try {
                                    pane.getChildren().set(0, new Sprite(arr.subList(68, 74), 48, 48).getPane(150));
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                        }
                    }
                    transition.play();
                    if (pane.getTranslateX() / 48 == (int) Character.pointX / 48 && pane.getTranslateY() / 48 == (int) Character.pointY / 48) {
                       Character.health--;
                    }
                });
            }

        },0, 900);
        GamePlay.paneMonster.getChildren().add(pane);
    }
    public void stop() {
        timer.cancel();
        timer.purge();
    }
}
