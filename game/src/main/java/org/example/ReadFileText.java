package org.example;
import java.io.*;
public class ReadFileText {
    private char [][] arr;
    public ReadFileText() {
        arr = new char[12][16];
        for(int i = 0; i < 12; i++) {
            arr[i] = new char[16];
        }
        FileReader Fr = null;
        try {
            String path = new File("Map/Map1.txt").getAbsolutePath();
            Fr = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(Fr);
            int word;
            int i = 0;
            int j = 0;
            RememberMap a = new RememberMap();
            while ((word = bufferedReader.read()) != -1) {
                if ((char) word == '\n') {
                    i++;
                    j = -1;
                }
                else{
                    arr[i][j] = (char)word;
                }
                j++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public char[][] getArray() {
        return arr;
    }
}
