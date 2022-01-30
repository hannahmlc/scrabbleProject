package game.interfaces;

import game.Tile;
import game.exceptions.InvalidDirectionException;
import game.exceptions.InvalidIndexException;
import game.exceptions.InvalidInputException;
import game.exceptions.InvalidWordException;
import game.Player;

public interface Game {

    void play()
        throws InvalidInputException, InvalidIndexException, InvalidWordException, InvalidDirectionException;


    Board getBoard();

    String getWinner();

    String getPlayers();


    /**
     * indicate whatever game is still going
     * return true if game is active, false otherwise
     * @ensures game that is being played is acitve
     */
    boolean gameOver();

    boolean noTilesLeft();

    void printResult(Player p1, Player p2);


    /*
    public void move(String[] tiles, String playerName) throws Exception;



     */
}
