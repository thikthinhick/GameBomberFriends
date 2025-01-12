package org.example;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RememberMap {
    ImageView imageView;
    public RememberMap() {
    }
    public ImageView getImageView(int sizeX, int sizeY, int x, int y, String url) {
        try {
            Image image = new Image(new FileInputStream(url));
            imageView = new ImageView(image);
            imageView.setFitWidth(sizeX);
            imageView.setFitHeight(sizeY);
            imageView.setX(x);
            imageView.setY(y);
            return imageView;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return imageView;
    }
}
