package game.interfaces;

import game.exceptions.InvalidDirectionException;
import game.exceptions.InvalidIndexException;
import game.exceptions.InvalidInputException;
import game.exceptions.InvalidWordException;
import game.Player;
import game.protocol.server.clientHandler;

public interface Game {

    public void start() throws InvalidInputException, InvalidIndexException, InvalidWordException, InvalidDirectionException ;

    void play() throws InvalidInputException, InvalidIndexException, InvalidWordException, InvalidDirectionException;

    Board getBoard();

    Player[] getPlayers();

    clientHandler[] getClientPlayers();

    /**
     * indicate whatever game is still going
     * return true if game is active, false otherwise
     * @ensures game that is being played is acitve
     */
    boolean gameOver();

    boolean noTilesLeft();

    void printResult(Player p1, Player p2);

    public Player winner(Player p1, Player p2);

    int playerFinalScore(Player player);

    Player getCurrentPlayer();
}
