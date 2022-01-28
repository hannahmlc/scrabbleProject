package utils;

public class LetterToPosition {
    public static int getYfromLetter(char letter) {
        int y = letter-64; // ASCII value -64 so that A will be 1
        return y;
    }
}
