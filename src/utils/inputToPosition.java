package utils;

public class inputToPosition {
    public static int getPositionFromLetter(char letter) {
        return letter-64; // ASCII value -64 so that A will be 1
    }
    public static int getPositionFromNumber(char letter) {
        return letter-48; // ASCII value -48 so that char '1' will be 1
    }
    public static int getPositionFromString(String letter) {
        return Integer.parseInt(letter); // ASCII value -48 so that char '1' will be 1
    }

}
