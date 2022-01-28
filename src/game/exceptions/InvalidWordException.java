package game.exceptions;

public class InvalidWordException extends Exception {
    public InvalidWordException() {
        super("Word doesnt exist");
    }
}
