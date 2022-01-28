package game.exceptions;

public class InvalidInputException extends Exception{
    public InvalidInputException() {
        super("incorrect input, youve lost your turn");
    }
}
