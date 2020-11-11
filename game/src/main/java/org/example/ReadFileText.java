package org.example;
import java.io.*;
public class ReadFileText {
    private char [][] arr;
    public ReadFileText() {
        arr = new char[100][100];
        for(int i = 0; i < 100; i++) {
            arr[i] = new char[100];
        }
        FileReader Fr = null;
        try {
            Fr = new FileReader("C:\\Users\\nhoctrum\\IdeaProjects\\Project\\Map\\Map1.txt");
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 15; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }
    public char[][] getArray() {
        return arr;
    }
}
