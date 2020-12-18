package org.example;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

public class bomBoss {
    public bomBoss(int X, int Y, int x, int y, boolean player) {
        RememberMap c = new RememberMap();
        int BombX = x / 48 * 48;
        int BombY = y / 48 * 48;
        ImageView bomb = c.getImageView(35, 35, X, Y, "IMG/bomb.png");
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(1000), bomb);
        scaleTransition.setToX(2f);
        scaleTransition.setToY(2f);
        scaleTransition.setCycleCount(3);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();
        TranslateTransition transition = new TranslateTransition(Duration.millis(2000), bomb);
        transition.setToX(-Y + BombX);
        transition.setToY(-X + BombY);
        transition.play();
        GamePlay.bomb.getChildren().add(bomb);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    GamePlay.bomb.getChildren().remove(bomb);
                    new MediaMusic("Sound/firework2.mp3", 0.1).audioClip.play();
                    Character.numberbombMap--;
                    Fire newFire = null;
                    try {
                        newFire = new Fire(BombX, BombY, player);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Pane paneBomb = newFire.getPane();
                    GamePlay.bomb.getChildren().add(paneBomb);
                    paneBomb.setTranslateX(BombX - 96);
                    paneBomb.setTranslateY(BombY - 96);
                    Timer timer = new Timer();
                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(() -> {
                                GamePlay.bomb.getChildren().remove(paneBomb);
                            });
                            timer.cancel();
                            timer.purge();
                        }
                    }, 350, 1);

                });
                timer.cancel();
                timer.purge();
            }
        }, 2000, 1000);
    }
}
