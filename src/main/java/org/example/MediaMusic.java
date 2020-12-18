package org.example;

import javafx.scene.media.AudioClip;

import java.io.File;
import java.net.URI;

public class MediaMusic {
    AudioClip audioClip;
    public MediaMusic(String url, double volume) {
        String path = new File(url).getAbsolutePath();
        File newFile = new File(path);
        URI uri = newFile.toURI();
        audioClip = new AudioClip(uri.toString());
    }
}
