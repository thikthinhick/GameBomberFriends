package org.example;

import javafx.scene.layout.Pane;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
public class Fire {
    private Pane pane;
    int pointX;
    int pointY;
    RememberMap tomb;
    public Fire(double x, double y, boolean player) throws FileNotFoundException{
        pane = new Pane();
        pointX = (int) x / 48;
        pointY = (int) (y + 1) / 48;
        tomb = new RememberMap();
        List<String> arr = new ArrayList<>();
        int length;
        if(player) length = Character.fireBomb;
        else length = Competitor.fireBomb;
        for (int i = 1; i <= 10; i++) {
            arr.add("IMG/fire" + i + ".png");
        }
        for (int i = 0; i <= length; i++) {
            if (GamePlay.array[pointY + i][pointX] == '#') {
                break;
            }
            Sprite sprite = new Sprite(arr, 48, 48);
            Pane pane1 = sprite.getPane(45);
            pane.getChildren().add(pane1);
            pane1.setTranslateX(96);
            pane1.setTranslateY((i + 2) * 48);

            if (pointX == Character.getLocation().getX() / 48 && (pointY + i) == Character.getLocation().getY() / 48)
                Character.health--;
            if(pointX == Competitor.getLocation().getX() / 48 && (pointY + i) == Competitor.getLocation().getY() / 48) {
                Competitor.health--;
            }
            for(int j = 0; j < GamePlay.paneMonster.getChildren().size(); j++) {
                if((int)GamePlay.paneMonster.getChildren().get(j).getTranslateX()  / 48 == pointX && (int)GamePlay.paneMonster.getChildren().get(j).getTranslateY() / 48 == pointY + i) {
                    GamePlay.quaivat.get(j).stop();
                    GamePlay.paneMonster.getChildren().remove(j);
                    GamePlay.quaivat.remove(j);
                }
            }
            if (GamePlay.array[pointY + i][pointX] == '@') {
                GamePlay.array[pointY + i][pointX] = '.';
                Pane item = new Pane();
                char z = GamePlay.brray[pointY + i][pointX];
                GamePlay.brray[pointY + 1][pointX] = (char) ((int) z - 32);
                String url = "";
                if ((int) z != 0) {
                    if (z == 'h') url = "IMG/health.png";
                    else if (z == 's') url = "IMG/speedup.png";
                    else if (z == 'k') url = "IMG/key.png";
                    else if (z == 'b') url = "IMG/bombamount.png";
                    else url = "IMG/bombpower.png";
                    item.getChildren().add(new ScaleAnimation(42, 42, pointX * 48, (pointY + i) * 48, url).getScaleAnimation());
                }
                GamePlay.map.getChildren().set((pointY + i) * 15 + pointX + 35, item);
                break;
            }
        }
        for (int i = 1; i <= length; i++) {
            if (GamePlay.array[pointY - i][pointX] == '#') {
                break;
            }
            Sprite sprite = new Sprite(arr, 48, 48);
            Pane pane1 = sprite.getPane(45);
            pane.getChildren().add(pane1);
            pane1.setTranslateX(96);
            pane1.setTranslateY((-i + 2) * 48);
            if (pointX == Character.getLocation().getX() / 48 && (pointY - i) == Character.getLocation().getY() / 48)
                Character.health--;
            if(pointX == Competitor.getLocation().getX() / 48 && (pointY - i) == Competitor.getLocation().getY() / 48)
                Competitor.health --;
            for(int j = 0; j < GamePlay.paneMonster.getChildren().size(); j++) {
                if((int)GamePlay.paneMonster.getChildren().get(j).getTranslateX()  / 48 == pointX && (int) GamePlay.paneMonster.getChildren().get(j).getTranslateY() / 48 == pointY - i) {
                    GamePlay.quaivat.get(j).stop();
                    GamePlay.paneMonster.getChildren().remove(j);
                    GamePlay.quaivat.remove(j);
                }
            }
            if (GamePlay.array[pointY - i][pointX] == '@') {
                GamePlay.array[pointY - i][pointX] = '.';
                Pane item = new Pane();
                char z = GamePlay.brray[pointY - i][pointX];
                GamePlay.brray[pointY - 1][pointX] = (char) ((int) z - 32);
                String url = "";
                if ((int) z != 0) {
                    if (z == 'h') url = "IMG/health.png";
                    else if (z == 's') url = "IMG/speedup.png";
                    else if (z == 'k') url = "IMG/key.png";
                    else if (z == 'b') url = "IMG/bombamount.png";
                    else url = "IMG/bombpower.png";
                    item.getChildren().add(new ScaleAnimation(42, 42, pointX * 48, (pointY - i) * 48, url).getScaleAnimation());
                }

                GamePlay.map.getChildren().set((pointY - i) * 15 + pointX + 35, item);
                break;
            }

        }
        for (int i = 1; i <= length; i++) {
            if (GamePlay.array[pointY][pointX + i] == '#') {
                break;
            }
            Sprite sprite = new Sprite(arr, 48, 48);
            Pane pane1 = sprite.getPane(45);
            pane.getChildren().add(pane1);
            pane1.setTranslateX(48 * (i + 2));
            pane1.setTranslateY(96);
            if ((pointX + i) == Character.getLocation().getX() / 48 && pointY == Character.getLocation().getY() / 48)
                Character.health--;
            if((pointX + i) == Competitor.getLocation().getX() / 48 && pointY == Competitor.getLocation().getY() / 48)
                Competitor.health--;
            for(int j = 0; j < GamePlay.paneMonster.getChildren().size(); j++) {
                if((int)GamePlay.paneMonster.getChildren().get(j).getTranslateX()  / 48 == pointX + i && (int) GamePlay.paneMonster.getChildren().get(j).getTranslateY() / 48 == pointY) {
                    GamePlay.quaivat.get(j).stop();
                    GamePlay.paneMonster.getChildren().remove(j);
                    GamePlay.quaivat.remove(j);
                }
            }
            if (GamePlay.array[pointY][pointX + i] == '@') {
                GamePlay.array[pointY][pointX + i] = '.';
                Pane item = new Pane();
                char z = GamePlay.brray[pointY][pointX + 1];
                String url = "";
                if ((int) z != 0) {
                    if (z == 'h') url = "IMG/health.png";
                    else if (z == 's') url = "IMG/speedup.png";
                    else if (z == 'k') url = "IMG/key.png";
                    else if (z == 'b') url = "IMG/bombamount.png";
                    else url = "IMG/bombpower.png";
                    GamePlay.brray[pointY][pointX + 1] = (char) ((int) z - 32);
                    item.getChildren().add(new ScaleAnimation(42, 42, (pointX + i) * 48, pointY * 48, url).getScaleAnimation());
                }

                GamePlay.map.getChildren().set(pointY * 15 + pointX + i + 35, item);
                break;
            }
        }
        for (int i = 1; i <= length; i++) {
            if (GamePlay.array[pointY][pointX - i] == '#') {
                break;
            }
            Sprite sprite = new Sprite(arr, 48, 48);
            Pane pane1 = sprite.getPane(45);
            pane.getChildren().add(pane1);
            pane1.setTranslateX(48 * (-i + 2));
            pane1.setTranslateY(96);
            if ((pointX - i) == Character.getLocation().getX() / 48 && pointY == Character.getLocation().getY() / 48)
                Character.health--;
            if((pointX - i) == Competitor.getLocation().getX() / 48 && pointY == Competitor.getLocation().getY() / 48)
                Competitor.health--;
            for(int j = 0; j < GamePlay.paneMonster.getChildren().size(); j++) {
                if((int)GamePlay.paneMonster.getChildren().get(j).getTranslateX()  / 48 == pointX - i && (int)GamePlay.paneMonster.getChildren().get(j).getTranslateY() / 48 == pointY) {
                    GamePlay.quaivat.get(j).stop();
                    GamePlay.paneMonster.getChildren().remove(j);
                    GamePlay.quaivat.remove(j);
                }
            }
            if (GamePlay.array[pointY][pointX - i] == '@') {
                GamePlay.array[pointY][pointX - i] = '.';
                Pane item = new Pane();
                char z = GamePlay.brray[pointY][pointX - 1];
                GamePlay.brray[pointY][pointX - 1] = (char) ((int) z - 32);
                String url = "";
                if ((int) z != 0) {
                    if (z == 'h') url = "IMG/health.png";
                    else if (z == 's') url = "IMG/speedup.png";
                    else if (z == 'k') url = "IMG/key.png";
                    else if (z == 'b') url = "IMG/bombamount.png";
                    else url = "IMG/bombpower.png";
                    item.getChildren().add(new ScaleAnimation(42, 42, (pointX - i) * 48, pointY * 48, url).getScaleAnimation());
                }

                GamePlay.map.getChildren().set(pointY * 15 + pointX - i + 35, item);
                break;
            }
        }
        if(GamePlay.Internet && Competitor.health == 0){
            GamePlay.paneY.getChildren().set(0, tomb.getImageView(48, 48, Competitor.getLocation().getX(), Competitor.getLocation().getY(), "IMG/grave.png"));
        }
    }
    public Pane getPane() {
        return pane;
    }
}
