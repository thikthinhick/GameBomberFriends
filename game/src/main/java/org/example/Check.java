package org.example;

import java.util.List;

public class Check {
    private List<Vector3> list;
    public boolean getCheck(int x, int y, int direction) {
        if(direction == 1) {
           if(App.array[y/48][((x + 48)/48)] == '.' && App.array[(y + 46)/48][(x + 48)/48] == '.')
               return true;
           if((x + 48) % 48 == 0) {
               return true;
           }
        }
        else if (direction == 2) {
            if (App.array[y / 48][x / 48] == '.' && App.array[(y + 46) / 48][x / 48] == '.')
                return true;
            if (x % 48 == 0) {
                return true;
            }
        } else if (direction == 3) {
            if (App.array[y / 48][x / 48] == '.' && App.array[y / 48][(x + 46) / 48] == '.') {
                return true;
            }
            if (y % 48 == 0) {
                return true;
            }
        } else if (direction == 4) {
            if (App.array[(y + 48) / 48][x / 48] == '.' && App.array[(y + 48) / 48][(x + 46) / 48] == '.') {
                return true;
            }
            if ((y + 48) % 48 == 0)
                return true;
        }
        return false;
    }
    public char[][] getArray() {
        return App.array;
    }
}
