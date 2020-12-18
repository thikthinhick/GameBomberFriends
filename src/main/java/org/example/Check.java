package org.example;

public class Check {
    public boolean getCheck(int x, int y, int direction) {
        if(direction == 1) {
           if( GamePlay.array[y/48][((x + 48)/48)] == '.' &&  GamePlay.array[(y + 47)/48][(x + 48)/48] == '.')
               return true;
           if((x + 48) % 48 == 0) {
               return true;
           }
        }
        else if (direction == 2) {
            if ( GamePlay.array[y / 48][x / 48] == '.' &&  GamePlay.array[(y + 46) / 48][x / 48] == '.')
                return true;
            if (x % 48 == 0) {
                return true;
            }
        } else if (direction == 3) {
            if ( GamePlay.array[y / 48][x / 48] == '.' &&  GamePlay.array[y / 48][(x + 46) / 48] == '.') {
                return true;
            }
            if (y % 48 == 0) {
                return true;
            }
        } else if (direction == 4) {
            if ( GamePlay.array[(y + 48) / 48][x / 48] == '.' &&  GamePlay.array[(y + 48) / 48][(x + 46) / 48] == '.') {
                return true;
            }
            if ((y + 48) % 48 == 0)
                return true;
        }
        return false;
    }
    public char[][] getArray() {
        return  GamePlay.array;
    }
}
