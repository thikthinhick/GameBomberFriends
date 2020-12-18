package org.example;
import javafx.application.Platform;
import javafx.scene.layout.Pane;

import java.util.Timer;
import java.util.TimerTask;

public class Monster3 extends Monster{
    Timer timer;
    public Monster3(int X, int Y) {
        Pane pane = new Pane(new RememberMap().getImageView(48, 48, X, Y, "IMG/phao.png"));
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {

                    bomBoss x = new bomBoss(X, Y,(int)Character.pointX + 24, (int)Character.pointY + 24, true);
                });
            }
        }, 2000, 10000);
        GamePlay.pane2.getChildren().add(pane);
    }
    public void stop() {
        timer.cancel();
        timer.purge();
    }
}
