package org.example;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Fire{
    private Pane pane;
    int pointX;
    int pointY;
    public Fire(double x, double y) throws FileNotFoundException {
        pane = new Pane();
        pointX = (int) x / 48;
        pointY = (int) (y + 1) / 48;
        System.out.println(pointX + " " + pointY);
        List<String> arr = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            arr.add("IMG/fire" + i + ".png");
        }
        for (int i = 0; i <= 2; i++) {
            if (App.array[pointY + i][pointX] == '#') {
                break;
            }
            Sprite sprite = new Sprite(arr, 48, 48);
            Pane pane1 = sprite.getPane(50);
            pane.getChildren().add(pane1);
            pane1.setTranslateX(96);
            pane1.setTranslateY((i + 2) * 48);
            if (App.array[pointY + i][pointX] == '@') {
                App.array[pointY + i][pointX] = '.';
                Image image = new Image(new FileInputStream("IMG/Xoaphong.png"));
                ImageView k = new ImageView(image);
                App.map.getChildren().set((pointY + i) * 15 + pointX + 35, k);
                break;
            }
        }
        for (int i = 1; i <= 2; i++) {
            if (App.array[pointY - i][pointX] == '#') {
                break;
            }
            Sprite sprite = new Sprite(arr, 48, 48);
            Pane pane1 = sprite.getPane(50);
            pane.getChildren().add(pane1);
            pane1.setTranslateX(96);
            pane1.setTranslateY((-i + 2) * 48);
            if (App.array[pointY - i][pointX] == '@') {
                App.array[pointY - i][pointX] = '.';
                Image image = new Image(new FileInputStream("IMG/Xoaphong.png"));
                ImageView k = new ImageView(image);
                App.map.getChildren().set((pointY - i) * 15 + pointX + 35, k);
                break;
            }

        }
        for (int i = 0; i <= 2; i++) {
            if (App.array[pointY][pointX + i] == '#') {
                break;
            }
            Sprite sprite = new Sprite(arr, 48, 48);
            Pane pane1 = sprite.getPane(50);
            pane.getChildren().add(pane1);
            pane1.setTranslateX(48 * (i + 2));
            pane1.setTranslateY(96);
            if (App.array[pointY][pointX + i] == '@') {
                App.array[pointY][pointX + i] = '.';
                Image image = new Image(new FileInputStream("IMG/Xoaphong.png"));
                ImageView k = new ImageView(image);
                App.map.getChildren().set(pointY * 15 + pointX + i + 35, k);
                break;
            }
        }
        for (int i = 1; i <= 2; i++) {
            if (App.array[pointY][pointX - i] == '#') {
                break;
            }
            Sprite sprite = new Sprite(arr, 48, 48);
            Pane pane1 = sprite.getPane(50);
            pane.getChildren().add(pane1);
            pane1.setTranslateX(48 * (-i + 2));
            pane1.setTranslateY(96);
            if (App.array[pointY][pointX - i] == '@') {
                App.array[pointY][pointX - i] = '.';
                System.out.println(pointY * 15 + pointX - i);
                Image image = new Image(new FileInputStream("IMG/Xoaphong.png"));
                ImageView k = new ImageView(image);
                App.map.getChildren().set(pointY * 15 + pointX - i + 35, k);
                break;
            }
        }
    }
    public Pane getPane() {
        return pane;
    }
}
