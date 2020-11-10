package org.example;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RememberMap {
    public RememberMap() {
    }
    public ImageView getImageView(int x, int y, String url) {
        try {
            Image image = new Image(new FileInputStream(url));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(40);
            imageView.setFitHeight(56);
            imageView.setX(x);
            imageView.setY(y);
            return imageView;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
