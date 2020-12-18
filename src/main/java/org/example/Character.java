package org.example;

import javafx.animation.KeyFrame;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
public class Character extends Sprite {
    Pane pane2;
    Pane pane3;
    Pane pane4;
    public static int numberbombMap;
    static int health = 3;
    static int numberBomb = 1;
    static int speedCharacter = 6;
    static int fireBomb = 1;
    static boolean key = false;
    static Pane BigPane = null;
    Button button;
    static double pointX = 48;
    static double pointY = 48;
    public Character(List<String> arr, int height, int width) throws FileNotFoundException {
        super(arr, height, width);
        numberbombMap = 0;
        BigPane = new Pane();
        button = new Button();
        pane2 = new Pane();
        pane3 = new Pane();
        pane4 = new Pane();
        pane.setTranslateX(48);
        pane.setTranslateY(48);
        BigPane.getChildren().add(pane);
        int sum = 50;
        for (int i = 0; i < 7; i++) {
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

        for (int i = 7; i < 14; i++) {
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
        for (int i = 14; i < 21; i++) {
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
        for (int i = 21; i < 28; i++) {
            int finalI = i;
            t.getKeyFrames().add(new KeyFrame(
                    Duration.millis(sum),
                    (ActionEvent event) -> {
                        pane4.getChildren().setAll(list.get(finalI));
                    }
            ));
            sum += 50;
        }
        pane.getChildren().add(button);
        button.setStyle("-fx-opacity: 0");
        pane.getChildren().add(pane1);
        Check check = new Check();
        button.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().toString() == "RIGHT") {
                    if(GamePlay.Internet){
                        try {
                            GamePlay.dataOutputStream.writeUTF("right");
                            GamePlay.dataOutputStream.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (check.getCheck((int)pointX + speedCharacter, (int) pointY, 1)) {
                        pointX += speedCharacter;
                        TranslateTransition transition = new TranslateTransition(Duration.millis(50), pane);
                        transition.setToX(pointX + speedCharacter);

                        transition.play();
                        char itemGame = GamePlay.brray[(int)pointY / 48][((int)pointX + 46)/48];
                        if(itemGame == 'H' || itemGame == 'I' || itemGame == 'S' || itemGame == 'K' || itemGame == 'B') {
                            GamePlay.map.getChildren().set((int)pointY / 48 * 15 + (int)pointX/48 + 36, new Pane());
                            GamePlay.brray[(int)pointY / 48][((int)pointX + 46)/48] = (char)0;
                            if(itemGame == 'H') health++;
                            else if(itemGame == 'S'){
                                int speed = speedCharacter == 6 ? 2 :  4;
                                GamePlay.label3.setText(String.valueOf((speedCharacter+=speed)/4));
                                pointX += speed;
                            }
                            else if(itemGame == 'I') GamePlay.label2.setText(String.valueOf(++fireBomb));
                            else if(itemGame == 'B') GamePlay.label4.setText(String.valueOf(++numberBomb));
                            else key = true;
                        }
                    }
                    else{
                        if(GamePlay.array[(int)pointY / 48 + 1][((int)pointX / 48 + 1)] == '.' && GamePlay.array[(int)pointY / 48 + 1][(int)pointX / 48] == '.'){
                            pointY += 2;
                        }
                        else if(GamePlay.array[(int)pointY / 48][(int)pointX / 48 + 1] == '.' && GamePlay.array[(int)pointY / 48][(int)pointX / 48 + 1] == '.') {
                            pointY -= 2;
                        }
                        pane.setTranslateY(pointY);
                    }
                    if (!pane.getChildren().get(pane.getChildren().size() - 1).equals(pane3)) {
                        pane.getChildren().remove(pane.getChildren().size() - 1);
                        pane.getChildren().add(pane3);
                    }
                    t.play();
                } else if (keyEvent.getCode().toString() == "LEFT") {
                    if(GamePlay.Internet) {
                        try {
                            GamePlay.dataOutputStream.writeUTF("left");
                            GamePlay.dataOutputStream.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (check.getCheck((int) pointX - speedCharacter, (int) pointY, 2)) {
                        TranslateTransition transition = new TranslateTransition(Duration.millis(50), pane);
                        transition.setToX(pointX - speedCharacter);
                        pointX -= speedCharacter;
                        transition.play();
                        char itemGame = GamePlay.brray[(int)pointY / 48][(int)pointX/48];
                        if(itemGame == 'H' || itemGame == 'I' || itemGame == 'S' || itemGame == 'K' || itemGame == 'B') {
                            GamePlay.map.getChildren().set((int)pointY / 48 * 15 + (int)pointX/48 + 35, new Pane());
                            GamePlay.brray[(int)pointY / 48][(int)pointX/48] = (char)0;
                            if(itemGame == 'H') health++;
                            else if(itemGame == 'S'){
                                int speed = speedCharacter == 6 ? 2 :  4;
                                GamePlay.label3.setText(String.valueOf((speedCharacter+=speed)/4));
                                pointX -= speed;
                            }
                            else if(itemGame == 'I') GamePlay.label2.setText(String.valueOf(++fireBomb));
                            else if(itemGame == 'B') GamePlay.label4.setText(String.valueOf(++numberBomb));
                            else key = true;
                        }
                    }
                    else {
                        if(GamePlay.array[(int)pointY / 48][(int)pointX / 48] == '.' && GamePlay.array[(int)pointY/ 48][(int)pointX / 48 - 1] == '.') {
                            pointY -= 2;
                        }
                        else if(GamePlay.array[(int)pointY / 48 + 1][(int)pointX / 48] == '.' && GamePlay.array[(int)pointY / 48 + 1][(int)pointX / 48 - 1] == '.') {
                            pointY += 2;
                        }
                        pane.setTranslateY(pointY);
                    }
                    if (!pane.getChildren().get(pane.getChildren().size() - 1).equals(pane4)) {
                        pane.getChildren().remove(pane.getChildren().size() - 1);
                        pane.getChildren().add(pane4);
                    }
                    t.play();
                } else if (keyEvent.getCode().toString() == "UP") {
                    if(GamePlay.Internet) {
                        try {
                            GamePlay.dataOutputStream.writeUTF("up");
                            GamePlay.dataOutputStream.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (check.getCheck((int) pointX, (int) pointY - speedCharacter, 3)) {
                        TranslateTransition transition = new TranslateTransition(Duration.millis(50), pane);
                        transition.setToY(pointY - speedCharacter);
                        pointY -= speedCharacter;
                        transition.play();
                        char itemGame = GamePlay.brray[(int)pointY / 48][(int)pointX/48];
                        if(itemGame == 'H' || itemGame == 'I' || itemGame == 'S' || itemGame == 'K' || itemGame == 'B') {
                            GamePlay.map.getChildren().set((int)pointY / 48 * 15 +  (int)pointX/48 + 35, new Pane());
                            GamePlay.brray[(int)pointY / 48][(int)pointX/48] = (char)0;
                            //unfinished
                            if(itemGame == 'H') health++;
                            else if(itemGame == 'S'){
                                int speed = speedCharacter == 6 ? 2 :  4;
                                GamePlay.label3.setText(String.valueOf((speedCharacter+=speed)/4));
                                pointY -= speed;
                            }
                            else if(itemGame == 'I') GamePlay.label2.setText(String.valueOf(++fireBomb));
                            else if(itemGame == 'B') GamePlay.label4.setText(String.valueOf(++numberBomb));
                            else key = true;
                        }
                    }
                    else {
                        if(GamePlay.array[(int)pointY / 48][(int)pointX / 48] == '.' && GamePlay.array[(int)pointY / 48 - 1][(int)pointX / 48] == '.') {
                            pointX -= 2;
                        }
                        else if(GamePlay.array[(int)pointY / 48][(int)pointX / 48 + 1] == '.' && GamePlay.array[(int)pointY / 48 - 1][(int)pointX / 48 + 1] == '.') {
                            pointX += 2;
                        }
                        pane.setTranslateX(pointX);
                    }
                    if (!pane.getChildren().get(pane.getChildren().size() - 1).equals(pane1)) {
                        pane.getChildren().remove(pane.getChildren().size() - 1);
                        pane.getChildren().add(pane1);
                    }
                    t.play();
                } else if (keyEvent.getCode().toString() == "DOWN") {
                    if(GamePlay.Internet) {
                        try {
                            GamePlay.dataOutputStream.writeUTF("down");
                            GamePlay.dataOutputStream.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (check.getCheck((int) pointX, (int) pointY + speedCharacter, 4)) {
                        TranslateTransition transition = new TranslateTransition(Duration.millis(50), pane);
                        transition.setToY(pointY + speedCharacter);
                        pointY += speedCharacter;
                        transition.play();
                        char itemGame = GamePlay.brray[((int)pointY + 46) / 48][(int)pointX/48];
                        if(itemGame == 'H' || itemGame == 'I' || itemGame == 'S' || itemGame == 'K' || itemGame == 'B') {
                            GamePlay.map.getChildren().set((int)pointY / 48 * 15 + (int)pointX/48 + 50, new Pane());
                            GamePlay.brray[((int)pointY + 46) / 48][(int)pointX/48] = (char)0;
                            if(itemGame == 'H') health++;
                            else if(itemGame == 'S') {
                                int speed = speedCharacter == 6 ? 2 :  4;
                                GamePlay.label3.setText(String.valueOf((speedCharacter+=speed)/4));
                                pointY += speed;
                            }
                            else if(itemGame == 'I') GamePlay.label2.setText(String.valueOf(++fireBomb));
                            else if(itemGame == 'B') GamePlay.label4.setText(String.valueOf(++numberBomb));
                            else key = true;
                        }
                    }
                    else{
                        if(GamePlay.array[(int)pointY / 48][(int)pointX / 48] == '.' && GamePlay.array[(int)pointY / 48 + 1][(int)pointX / 48] == '.') {
                            pointX -= 2;
                        }
                        else if(GamePlay.array[(int)pointY / 48][(int)pointX / 48 + 1] == '.' && GamePlay.array[(int)pointY / 48 + 1][(int)pointX / 48 + 1] == '.') {
                            pointX += 2;
                        }
                        pane.setTranslateX(pointX);
                    }
                    if (!pane.getChildren().get(pane.getChildren().size() - 1).equals(pane2)) {
                        pane.getChildren().remove(pane.getChildren().size() - 1);
                        pane.getChildren().add(pane2);
                    }
                    t.play();
                }
                if (keyEvent.getCode().toString() == "SPACE") {
                    if(numberbombMap <= numberBomb){
                        bomb x = new bomb((int)pane.getTranslateX() + 24, (int)pane.getTranslateY() + 24, true);
                        if(GamePlay.Internet) {
                            try {
                                GamePlay.dataOutputStream.writeUTF("space");
                                GamePlay.dataOutputStream.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    numberbombMap++;
                } else if (keyEvent.getCode().toString() == "DELETE") {
                    if(key && GamePlay.brray[(int)pointY / 48][(int)pointX / 48] == 'd'){
                        Controller.parent.getChildren().clear();
                        try {
                            Controller.parent.getChildren().add(new GamePlay(false, 2).getPane());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        GamePlay.array = new ReadFileText(2).getArray();
                        GamePlay.brray = new ReadFileText(2).getBrray();
                        Character.pointX = 48;
                        Character.pointY = 48;
                        Character.health = 1;
                        GamePlay.mediaMusic.audioClip.stop();
                        GamePlay.mediaMusic.audioClip.play();
                    }
                }
                else if(keyEvent.getCode().toString() == "END") {
                    if(key){
                        key = false;
                        health = 3;
                        fireBomb = 1;
                        speedCharacter = 6;
                        numberBomb = 1;
                        GamePlay.Win();
                    }
                }
            }
        });
        button.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(GamePlay.Internet) {
                    try {
                        GamePlay.dataOutputStream.writeUTF("Released");
                        GamePlay.dataOutputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                t.stop();
            }
        });
        t.play();
    }
    public static Vector3 getLocation() {
        return new Vector3((int)pointX, (int)pointY);
    }
    public Pane getPane() {
        return BigPane;
    }
}
