package game.interfaces;

import game.exceptions.InvalidDirectionException;
import game.exceptions.InvalidIndexException;
import game.exceptions.InvalidInputException;
import game.exceptions.InvalidWordException;
import java.util.List;
import game.Player;

public interface Game {

    public void play()
        throws InvalidInputException, InvalidIndexException, InvalidWordException, InvalidDirectionException;


    public Board getBoard();

    public String getWinner();

    public String getPlayers();


    /**
     * indicate whatever game is still going
     * return true if game is active, false otherwise
     * @ensures game that is being played is acitve
     */
    public boolean gameOver();

    public boolean noTilesLeft();

    public void printResult(Player p1, Player p2);


    /*
    public void move(String[] tiles, String playerName) throws Exception;



     */
}
