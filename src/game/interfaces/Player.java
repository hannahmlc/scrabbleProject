package game.interfaces;

import game.Board;
import game.Tile;
import game.exceptions.InvalidDirectionException;
import game.exceptions.InvalidIndexException;
import game.exceptions.InvalidInputException;
import game.exceptions.InvalidWordException;
import java.util.List;
import zUnused_Scrappe.Letters;

public interface Player {

     void addLetters(List<Tile> bag);

     void removeLetters(List<Tile> rack, char[]Letters);

     List<Tile> getRack();

    void printRack(List<Tile> rack);

    boolean hasLetter(char Letter);

    List<String> TileLetters(List<Tile> tiles);

    int getScore();

    void addPoints(int points);

    String getName();

    public game.Board playerMove(Board board)  throws InvalidInputException, InvalidIndexException,
        InvalidWordException, InvalidDirectionException;

    public boolean playerInGame(); // chekcing whatever player is in game, also true if on of player decided they no longer want to continue the game

}
