package game.protocol;

import game.exceptions.ExitProgram;
import game.exceptions.ServerUnavailableException;
import game.protocol.server.clientHandler;
import java.io.IOException;

//TODO: add needed parameters to methods
public interface serverProtocol {

    //starts / runs the connection
    public void start();

    //Opens a new ServerSocket at localhost on a user-defined port.
    public  void setup() throws ExitProgram;

    void welcome(clientHandler client);

    //Removes a client from the client list after they quit
    void removeClient(clientHandler client);

    //check is client name is already in use or not
    public boolean join(String username);

    //create new game and start it
    public void createGame() throws IOException, ServerUnavailableException;

    //send tiles to client
    public void sendTiles() throws IOException, ServerUnavailableException;

    // receive move , looks for game, ddo the move
    // okay: save changes, check if gameOver
    // check if game over:  yes- close game / no - update board
    // not ok : sends error message to client
    public void doMove();

    //ends game,send to still connected players who won
    public void endGame();
}
