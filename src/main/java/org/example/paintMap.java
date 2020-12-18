package org.example;

import javafx.scene.layout.Pane;

public class paintMap {
    char[][] arr;
    private Pane Map = new Pane();

    public paintMap(int level) {
        arr = new char[11][];
        for (int i = 0; i < 11; i++) {
            arr[i] = new char[15];
        }
        arr = new ReadFileText(level).getArray();
        char [][]brr = new ReadFileText(level).getBrray();
        RememberMap a = new RememberMap();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                Map.getChildren().add(a.getImageView(96, 96, j * 96 , i * 96 + 19, "IMG/floor.png"));
            }
        }
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 15; j++) {
                if (arr[i][j] == '#')
                    Map.getChildren().add(a.getImageView(48, 67, j * 48, i * 48, "IMG/stone_1.png"));
                else if (arr[i][j] == '@') {
                    Map.getChildren().add(a.getImageView(48, 67, j * 48, i * 48, "IMG/brick.png"));
                }
                else if(brr[i][j] == 'd'){
                    Map.getChildren().add(a.getImageView(48, 48, j * 48, i * 48, "IMG/doorclosed.png"));
                }
                else if(brr[i][j] == 'p') {
                    Map.getChildren().add(a.getImageView(48, 48, j * 48, i * 48, "IMG/phao.png"));
                }
                else {
                    Map.getChildren().add(a.getImageView(48,48,j * 48, j * 48, "IMG/Xoaphong.png"));
                }


            }
        }
    }
    public Pane getMap() {
        return Map;
    }
}
