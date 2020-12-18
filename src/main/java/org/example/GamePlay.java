package org.example;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GamePlay {
    static public Pane map;
    public static char [][] array;
    public static Pane bomb;
    public static Pane paneX;
    public static Pane paneY;
    public static int numberMap = 1;
    public static Label label1;
    public static Label label2;
    public static Label label3;
    public static Label label4;
    public static MediaMusic mediaMusic;
    private Pane pane;
    static Pane pane2;
    static DataOutputStream dataOutputStream;
    static DataInputStream dataInputStream;
    static Pane paneMonster;
    static List<Monster> quaivat;
    public static char [][] brray;
    public static boolean Internet;
    public GamePlay(boolean Internet, int lever) throws IOException {
        paneMonster = new Pane();
        this.Internet = Internet;
        array = new ReadFileText(lever).getArray();
        brray = new ReadFileText(lever).getBrray();
        numberMap = lever;
        if(Internet){
            ServerSocket serverSocket = new ServerSocket(9999);
            Socket socket = serverSocket.accept();
            System.out.println("Ket noi thanh cong!");
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        }
        bomb = new Pane();
        pane = new Pane();
        mediaMusic = new MediaMusic("Sound/music.mp3", 0.1);
       mediaMusic.audioClip.play();
        mediaMusic.audioClip.setCycleCount(AudioClip.INDEFINITE);
        List<String> arr = new ArrayList<>();
        for(int i = 1; i <= 7; i++) {
            arr.add("IMG/up" + i + ".png");
        }
        for(int i = 1; i <= 7; i++) {
            arr.add("IMG/down" + i + ".png");
        }
        for(int i = 1; i <= 7; i++) {
            arr.add("IMG/right" + i + ".png");
        }
        for(int i = 1; i <= 7; i++) {
            arr.add("IMG/left" + i + ".png");
        }
        for(int i = 1; i <= 6; i++) {
            arr.add("IMG/competitorUp" + i + ".png");
        }
        for(int i = 1; i <= 6; i++) {
            arr.add("IMG/competitorDown" + i + ".png");
        }
        for(int i = 1; i <= 6; i++) {
            arr.add("IMG/competitorRight" + i + ".png");
        }
        for(int i = 1; i <= 6; i++) {
            arr.add("IMG/competitorLeft" + i + ".png");
        }
        for(int i = 1; i <= 8; i++) {
            arr.add("IMG/move" + i + ".png");
        }
        for(int i = 1; i <= 8; i++) {
            arr.add("IMG/moveleft" + i + ".png");
        }
        for(int i = 1; i <= 6; i++) {
            arr.add("IMG/bombleft" + i + ".png");
        }
        for(int i = 1; i <= 6; i++) {
            arr.add("IMG/bombright" + i + ".png");
        }
        label1 = new Label("1");
        label1.setFont(new Font("Cambria", 30));
        label1.setTextFill(Color.WHITE);
        label1.setTranslateX(80);
        label1.setTranslateY(80);
        label2 = new Label("1");
        label2.setFont(new Font("Cambria", 30));
        label2.setTextFill(Color.WHITE);
        label2.setTranslateX(80);
        label2.setTranslateY(130);
        label3 = new Label("1");
        label3.setFont(new Font("Cambria", 30));
        label3.setTextFill(Color.WHITE);
        label3.setTranslateX(80);
        label3.setTranslateY(180);
        label4 = new Label("1");
        label4.setFont(new Font("Cambria", 30));
        label4.setTextFill(Color.WHITE);
        label4.setTranslateX(80);
        label4.setTranslateY(230);
        paintMap a = new paintMap(lever);
        map = a.getMap();
        Character character = null;
        character = new Character(arr, 48, 48);
        RememberMap nen = new RememberMap();
        paneX = character.getPane();
        pane2 = new Pane(map, bomb, paneX);
        quaivat = new ArrayList<>();
        if(Internet == false){
            for(int i = 0; i < 11; i++) {
                for(int j = 0; j < 13; j++) {
                    if(brray[i][j] == '1'){
                        quaivat.add(new Monster1(j * 48, i * 48, arr));
                    }
                    if(brray[i][j] == '3') {
                        quaivat.add(new Monster3(j * 48 , i * 48));
                    }
                    if(brray[i][j] == '2'){
                        quaivat.add(new Monster2(j * 48, i * 48, arr));
                    }
                }
            }
        }
        System.out.println("quai vat: " + quaivat.size());
        pane2.getChildren().add(paneMonster);
        if(Internet) {
            Competitor competitor = null;
            competitor = new Competitor(arr, 48, 48);
            paneY = competitor.getPane();
            pane2.getChildren().add(paneY);
        }
        Pane pane3 = new Pane(nen.getImageView(896, 590, 0,0,"IMG/background2.png"), new RememberMap().getImageView(40, 40, 30, 80, "IMG/powerup_heart.png"),
                new RememberMap().getImageView(40,40, 30, 130, "IMG/tangbomb.png"), new RememberMap().getImageView(40, 40, 30, 180, "IMG/speedup.png"),
                new RememberMap().getImageView(40, 40, 30, 230, "IMG/itemBombCount.png"));
        pane2.setTranslateX(140);
        pane2.setTranslateY(18);
        pane.getChildren().addAll(pane3, pane2, label1, label2, label3, label4);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    label1.setText(String.valueOf(Character.health));
                    if(Character.health == 0){
                        for(int i = 0; i < quaivat.size(); i++) {
                            quaivat.get(i).stop();
                        }
                        quaivat = null;
                        paneMonster.getChildren().removeAll();
                        GameOver();
                        timer.cancel();
                        timer.purge();
                    }
                    if(Internet){
                        if(Competitor.health == 0) {
                            Win();
                            timer.cancel();
                            timer.purge();
                        }
                    }
                });
            }
        }, 0, 1);
    }
    public Pane getPane() {
        return pane;
    }
    public static void destroyPane() {
        paneMonster = null;
    }
    public static void GameOver() {
        paneX.getChildren().set(0, new RememberMap().getImageView(48, 48, Character.getLocation().getX(), Character.getLocation().getY(), "IMG/grave.png"));
        MediaMusic die = new MediaMusic("Sound/die.mp3", 0.02);
        mediaMusic.audioClip.stop();
        die.audioClip.play();
        Controller.parent.getChildren().get(0).setStyle("-fx-opacity: 0.7");
        Controller.parent.getChildren().add(new RememberMap().getImageView(573, 374, 163, 100, "IMG/gameOver.png"));
        Character.health = 1;
        Character.numberBomb = 1;
        Character.speedCharacter = 6;
        Character.fireBomb = 1;
        Character.pointY = 48;
        Character.pointX = 48;
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()-> {
                    try {
                        Controller.parent.getChildren().clear();
                        Controller.parent.getChildren().add(new FXMLLoader().load(getClass().getResource("Map.fxml")));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                timer.cancel();
                timer.purge();
            }
        },11000, 1);
    }
    public static void Win() {
        Controller.parent.getChildren().get(0).setStyle("-fx-opacity: 0.7");
        Controller.parent.getChildren().add(new RememberMap().getImageView(573, 374, 163, 100, "IMG/win.png"));
        mediaMusic.audioClip.stop();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()-> {
                    try {
                        Controller.parent.getChildren().clear();
                        Controller.parent.getChildren().add(new FXMLLoader().load(getClass().getResource("sample.fxml")));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                timer.cancel();
                timer.purge();
            }
        },6000, 1);
    }
}
