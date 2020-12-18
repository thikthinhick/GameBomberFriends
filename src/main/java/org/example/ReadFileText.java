package org.example;
import java.io.*;
public class ReadFileText {
    private char [][] arr;
    private char [][] brr;
    public ReadFileText(int level) {
        arr = new char[12][16];
        for(int i = 0; i < 12; i++) {
            arr[i] = new char[16];
        }
        brr = new char[12][16];
        for(int i = 0; i < 12; i++) {
            brr[i] = new char[16];
        }
        FileReader Fr = null;
        try {
            System.out.println(GamePlay.numberMap);
            String path = new File("Map/Map" + level + ".txt").getAbsolutePath();
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
                    if((char)word == 'h' || (char)word == 'b' || (char)word == 's' || (char)word == 'i' || (char)word == 'k'||(char)word == 'd' || (char)word == 'p'|| (char)word == '1'|| (char)word == '3' || (char)word == '2'){
                        j--;
                        brr[i][j] = (char)word;
                    }
                    else
                    arr[i][j] = (char)word;
                }
                j++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public char[][] getArray() {
        return arr;
    }
    public char[][] getBrray() {
        return brr;
    }
}
