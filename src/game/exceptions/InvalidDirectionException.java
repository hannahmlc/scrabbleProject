package game.exceptions;

public class InvalidDirectionException extends Exception {
    public InvalidDirectionException() {
        super("incorrect direction, you've lost your turn");
    }
}
