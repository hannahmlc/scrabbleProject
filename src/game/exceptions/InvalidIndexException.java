package game.exceptions;

public class InvalidIndexException extends Exception{
    public InvalidIndexException() {
        super("incorrect index, youve lost your turn");
    }
}