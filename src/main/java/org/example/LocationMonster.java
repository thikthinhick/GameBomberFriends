package org.example;


public class LocationMonster {
    int Location;
    public static void Floyd(int [][]arr, int [][]brr) {
        for (int k = 0; k < 165; k++) {
            for (int i = 0; i < 165; i++) {
                for (int j = 0; j < 165; j++) {
                    if (arr[i][j] > arr[i][k] + arr[k][j]) {
                        arr[i][j] = arr[i][k] + arr[k][j];
                        brr[i][j] = brr[i][k];
                    }
                }
            }
        }
    }
    public LocationMonster(int X, int Y, int Z) {
        int [][]brr = new int[165][];
        for(int i = 0; i < 165; i++) {
            brr[i] = new int[165];
        }
        int [][]crr = new int[165][];
        for(int i = 0; i < 165; i++) {
            crr[i] = new int[165];
        }
        for(int i = 0; i < 165; i++) {
            for(int j = 0;j < 165; j++) {
                brr[i][j] = 1000;
            }
        }
        for(int i = 1; i < 10; i++) {
            for(int j = 1; j < 14; j++) {
                if(GamePlay.array[i][j] == '.') {
                    int x = i * 15 + j;
                    if(GamePlay.array[i + 1][j] == '.'){
                        brr[x][x + 15] = 1;
                        brr[x + 15][x] = 1;
                    }
                    if(GamePlay.array[i - 1][j] == '.') {
                        brr[x][x - 15] = 1;
                        brr[x - 15][x] = 1;
                    }
                    if(GamePlay.array[i][j - 1] == '.') {
                        brr[x][x - 1] = 1;
                        brr[x - 1][x] = 1;
                    }
                    if(GamePlay.array[i][j + 1] == '.') {
                        brr[x + 1][x] = 1;
                        brr[x][x + 1] = 1;
                    }
                }
            }
        }
        for(int i = 0; i < 165; i++) {
            for(int j = 0; j < 165; j++) {
                if(brr[i][j] == 1000) crr[i][j] = 0;
                else crr[i][j] = j;
            }
        }
        Floyd(brr, crr);
        int u = Z;
        int v = X/48 + Y/48 * 15;
        if(brr[u][v] < 165){
            Location =  crr[u][v];
        }
    }
    public int getLocations() {
        return Location;
    }
}
