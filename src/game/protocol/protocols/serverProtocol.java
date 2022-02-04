package game.protocol.protocols;

import game.exceptions.ExitProgram;
import game.exceptions.InvalidDirectionException;
import game.exceptions.InvalidIndexException;
import game.exceptions.InvalidInputException;
import game.exceptions.InvalidWordException;
import game.exceptions.ServerUnavailableException;
import game.protocol.server.clientHandler;
import java.io.IOException;

public interface serverProtocol {


    //Opens a new ServerSocket at localhost on a user-defined port.
    void setup() throws ExitProgram;

    void welcome(clientHandler client);

    //Removes a client from the client list after they quit
    void removeClient(clientHandler client);

    //check is client name is already in use or not
    boolean join(String username);

    //create new game and start it
     void createGame() throws IOException, ServerUnavailableException;

    //send tiles to client
    void sendTiles(clientHandler client) throws IOException, ServerUnavailableException;

    // receive move , looks for game, ddo the move
    // okay: save changes, check if gameOver
    // check if game over:  yes- close game / no - update board
    // not ok : sends error message to client

    void doMove(int x, int y, char[] letters, String direction, String name)
        throws InvalidIndexException, ServerUnavailableException, IOException, InvalidInputException,
        InvalidWordException, InvalidDirectionException;

    //ends game,send to still connected players who won
    void endGame() throws IOException;
}
