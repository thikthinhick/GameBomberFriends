package org.example;
import javafx.animation.KeyFrame;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Competitor extends Sprite {
    Pane pane2;
    Pane pane3;
    Pane pane4;
    static int health = 3;
    int speedCompetitor = 6;
    static int fireBomb = 1;
    static Pane BigPane = null;
    private static double pointX = 528;
    private static double pointY = 48;

    public Competitor(List<String> arr, int height, int width) throws FileNotFoundException {
        super(arr, height, width);
        BigPane = new Pane();
        pane2 = new Pane();
        pane3 = new Pane();
        pane4 = new Pane();
        pane.setTranslateX(528);
        pane.setTranslateY(48);
        BigPane.getChildren().add(pane);
        int sum = 50;
        for (int i = 28; i < 34; i++) {
            int finalI = i;
            t.getKeyFrames().add(new KeyFrame(
                    Duration.millis(sum),
                    (ActionEvent event) -> {
                        pane1.getChildren().setAll(list.get(finalI));
                    }
            ));
            sum += 50;
        }
        sum = 50;

        for (int i = 34; i < 40; i++) {
            int finalI = i;
            t.getKeyFrames().add(new KeyFrame(
                    Duration.millis(sum),
                    (ActionEvent event) -> {
                        pane2.getChildren().setAll(list.get(finalI));
                    }
            ));
            sum += 50;
        }
        sum = 50;
        for (int i = 40; i < 46; i++) {
            int finalI = i;
            t.getKeyFrames().add(new KeyFrame(
                    Duration.millis(sum),
                    (ActionEvent event) -> {
                        pane3.getChildren().setAll(list.get(finalI));
                    }
            ));
            sum += 50;
        }
        sum = 50;
        for (int i = 46; i < 52; i++) {
            int finalI = i;
            t.getKeyFrames().add(new KeyFrame(
                    Duration.millis(sum),
                    (ActionEvent event) -> {
                        pane4.getChildren().setAll(list.get(finalI));
                    }
            ));
            sum += 50;
        }
        pane.getChildren().add(pane1);
        Check check = new Check();
        t.play();
        final boolean[] right = {false};
        final boolean[] left = {false};
        final boolean[] up = {true};
        final boolean[] down = {false};
        final boolean[] enter = {false};
        Timer timer1 = new Timer();
        timer1.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    String str = GamePlay.dataInputStream.readUTF();
                    System.out.println(str);
                    if (str.equals("right")) {
                        right[0] = true;
                        left[0] = false;
                        down[0] = false;
                        up[0] = false;
                        if (check.getCheck((int) pointX + speedCompetitor, (int) pointY, 1)) {
                            TranslateTransition transition = new TranslateTransition(Duration.millis(100), pane);
                            transition.setToX(pointX + speedCompetitor);
                            pointX += speedCompetitor;
                            transition.play();
                        }
                        else{
                            if(GamePlay.array[(int)pointY / 48 + 1][((int)pointX / 48 + 1)] == '.' && GamePlay.array[(int)pointY / 48 + 1][(int)pointX / 48] == '.'){
                                TranslateTransition transition = new TranslateTransition(Duration.millis(10), pane);
                                transition.setToY(pointY + 2);
                                pointY += 2;
                                transition.play();
                            }
                            else if(GamePlay.array[(int)pointY / 48][(int)pointX / 48 + 1] == '.' && GamePlay.array[(int)pointY / 48][(int)pointX / 48 + 1] == '.') {
                                TranslateTransition transition = new TranslateTransition(Duration.millis(10), pane);
                                transition.setToY(pointY - 2);
                                pointY -= 2;
                                transition.play();
                            }
                        }
                    } else if (str.equals("left")) {
                        right[0] = false;
                        left[0] = true;
                        down[0] = false;
                        up[0] = false;
                        if (check.getCheck((int) pointX - speedCompetitor, (int) pointY, 2)) {
                            TranslateTransition transition = new TranslateTransition(Duration.millis(100), pane);
                            transition.setToX(pointX - speedCompetitor);
                            pointX -= speedCompetitor;
                            transition.play();
                        }
                        else{
                            if(GamePlay.array[(int)pointY / 48][(int)pointX / 48] == '.' && GamePlay.array[(int)pointY/ 48][(int)pointX / 48 - 1] == '.') {
                                TranslateTransition transition = new TranslateTransition(Duration.millis(10), pane);
                                transition.setToY(pointY - 2);
                                pointY -= 2;
                                transition.play();
                            }
                            else if(GamePlay.array[(int)pointY / 48 + 1][(int)pointX / 48] == '.' && GamePlay.array[(int)pointY / 48 + 1][(int)pointX / 48 - 1] == '.') {
                                TranslateTransition transition = new TranslateTransition(Duration.millis(10), pane);
                                transition.setToY(pointY + 2);
                                pointY += 2;
                                transition.play();
                            }
                        }
                    } else if (str.equals("up")) {
                        right[0] = false;
                        left[0] = false;
                        down[0] = false;
                        up[0] = true;
                        if (check.getCheck((int) pointX, (int) pointY - speedCompetitor, 3)) {
                            TranslateTransition transition = new TranslateTransition(Duration.millis(100), pane);
                            transition.setToY(pointY - speedCompetitor);
                            pointY -= speedCompetitor;
                            transition.play();
                        }
                        else{
                            if(GamePlay.array[(int)pointY / 48][(int)pointX / 48] == '.' && GamePlay.array[(int)pointY / 48 - 1][(int)pointX / 48] == '.') {
                                TranslateTransition transition = new TranslateTransition(Duration.millis(10), pane);
                                transition.setToX(pointX - 2);
                                pointX -= 2;
                                transition.play();
                            }
                            else if(GamePlay.array[(int)pointY / 48][(int)pointX / 48 + 1] == '.' && GamePlay.array[(int)pointY / 48 - 1][(int)pointX / 48 + 1] == '.') {
                                TranslateTransition transition = new TranslateTransition(Duration.millis(10), pane);
                                transition.setToX(pointX + 2);
                                pointX += 2;
                                transition.play();
                            }
                        }
                    } else if (str.equals("down")) {
                        right[0] = false;
                        left[0] = false;
                        down[0] = true;
                        up[0] = false;
                        if (check.getCheck((int) pointX, (int) pointY + speedCompetitor, 4)) {
                            TranslateTransition transition = new TranslateTransition(Duration.millis(100), pane);
                            transition.setToY(pointY + speedCompetitor);
                            pointY += speedCompetitor;
                            transition.play();
                        }
                        else{
                            if(GamePlay.array[(int)pointY / 48][(int)pointX / 48] == '.' && GamePlay.array[(int)pointY / 48 + 1][(int)pointX / 48] == '.') {
                                TranslateTransition transition = new TranslateTransition(Duration.millis(10), pane);
                                transition.setToX(pointX - 2);
                                pointX -= 2;
                                transition.play();
                            }
                            else if(GamePlay.array[(int)pointY / 48][(int)pointX / 48 + 1] == '.' && GamePlay.array[(int)pointY / 48 + 1][(int)pointX / 48 + 1] == '.') {
                                TranslateTransition transition = new TranslateTransition(Duration.millis(10), pane);
                                transition.setToX(pointX + 2);
                                pointX += 2;
                                transition.play();
                            }
                        }
                    } else if (str.equals("space")) {
                        enter[0] = true;
                    } else if (str.equals("Released")) {
                        right[0] = false;
                        left[0] = false;
                        down[0] = false;
                        up[0] = false;
                        enter[0] = false;
                        t.stop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 10);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                            if (right[0] == true) {
                                pane.getChildren().set(0, pane3);
                                t.play();
                                char itemGame = GamePlay.brray[(int) pointY / 48][(int) pointX / 48];
                                if (itemGame == 'H' || itemGame == 'I' || itemGame == 'S' || itemGame == 'K' || itemGame == 'B') {
                                    GamePlay.map.getChildren().set((int) pointY / 48 * 15 + (int) pointX / 48 + 35, new Pane());
                                    GamePlay.brray[(int) pointY / 48][(int) pointX / 48] = (char) 0;
                                    if (itemGame == 'H') health++;
                                    else if (itemGame == 'S') {
                                        int speed = (speedCompetitor == 6 ? 2 : 4);
                                        speedCompetitor += speed;
                                        pointX += speed;
                                    }
                                    else if (itemGame == 'I') fireBomb++;
                                }
                            } else if (left[0] == true) {
                                pane.getChildren().set(0, pane4);
                                t.play();
                                char itemGame = GamePlay.brray[(int) pointY / 48][(int) pointX / 48];
                                if (itemGame == 'H' || itemGame == 'I' || itemGame == 'S' || itemGame == 'K' || itemGame == 'B') {
                                    GamePlay.map.getChildren().set((int) pointY / 48 * 15 + (int) pointX / 48 + 35, new Pane());
                                    GamePlay.brray[(int) pointY / 48][(int) pointX / 48] = (char) 0;
                                    if (itemGame == 'H') health++;
                                    else if (itemGame == 'I') fireBomb++;
                                    else if (itemGame == 'S') {
                                        int speed = (speedCompetitor == 6 ? 2 : 4);
                                        speedCompetitor += speed;
                                        pointX -= speed;
                                    }
                                }
                            } else if (up[0] == true) {
                                pane.getChildren().set(0, pane1);
                                t.play();
                                char itemGame = GamePlay.brray[(int) pointY / 48][(int) pointX / 48];
                                if (itemGame == 'H' || itemGame == 'I' || itemGame == 'S' || itemGame == 'K' || itemGame == 'B') {
                                    GamePlay.map.getChildren().set((int) pointY / 48 * 15 + (int) pointX / 48 + 35, new Pane());
                                    GamePlay.brray[(int) pointY / 48][(int) pointX / 48] = (char) 0;
                                    if (itemGame == 'H') health++;
                                    else if (itemGame == 'I') fireBomb++;
                                    else if (itemGame == 'S') {
                                        int speed = (speedCompetitor == 6 ? 2 : 4);
                                        speedCompetitor += speed;
                                        pointY -= speed;
                                    }
                                }
                            } else if (down[0] == true) {
                                pane.getChildren().set(0, pane2);
                                t.play();
                                char itemGame = GamePlay.brray[(int) pointY / 48][(int) pointX / 48];
                                if (itemGame == 'H' || itemGame == 'I' || itemGame == 'S' || itemGame == 'K' || itemGame == 'B') {
                                   GamePlay.map.getChildren().set((int) pointY / 48 * 15 + (int) pointX / 48 + 35, new Pane());
                                   GamePlay.brray[(int) pointY / 48][(int) pointX / 48] = (char) 0;
                                    //unfinished
                                    if (itemGame == 'H') health++;
                                    else if (itemGame == 'I') fireBomb++;
                                    else if (itemGame == 'S'){
                                        int speed = (speedCompetitor == 6 ? 2 : 4);
                                        speedCompetitor += speed;
                                        pointY += speed;
                                    }
                                }
                            } else if (enter[0] == true) {
                                bomb x = new bomb((int) pane.getTranslateX() + 24, (int) pane.getTranslateY() + 24, false);
                                enter[0] = false;
                            }
                        }
                );
            }
        }, 0, 10);
    }
    public static Vector3 getLocation() {
        return new Vector3((int) pointX, (int) pointY);
    }
    public static Pane getPane() {
        return BigPane;
    }
}
