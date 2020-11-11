package org.example;

import java.util.List;

public class Check {
    private List<Vector3> list;
    private char[][] arr;

    public Check() {
        arr = new char[11][];
        for (int i = 0; i < 11; i++) {
            arr[i] = new char[15];
        }
        arr = new ReadFileText().getArray();
    }

    public boolean getCheck(int x, int y, int direction) {
        if(direction == 1) {
            if(arr[y/48][((x + 48)/48)] == '.' && arr[(y + 46)/48][(x + 48)/48] == '.')
                return true;
            if((x + 48) % 48 == 0) {
                return true;
            }
        }
        else if (direction == 2) {
            if (arr[y / 48][x / 48] == '.' && arr[(y + 46) / 48][x / 48] == '.')
                return true;
            if (x % 48 == 0) {
                return true;
            }
        } else if (direction == 3) {
            if (arr[y / 48][x / 48] == '.' && arr[y / 48][(x + 46) / 48] == '.') {
                return true;
            }
            if (y % 48 == 0) {
                return true;
            }
        } else if (direction == 4) {
            if (arr[(y + 48) / 48][x / 48] == '.' && arr[(y + 48) / 48][(x + 46) / 48] == '.') {
                return true;
            }
            if ((y + 48) % 48 == 0)
                return true;
        }
        return false;
    }
}
