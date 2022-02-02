package game.protocol;

import game.exceptions.ServerUnavailableException;
import java.io.IOException;

//TODO: add needed parameters to methods
public interface clientProtocol {

    //used for ip and port to crrate connection, start connection with server
    public void start();

     // Creates a connection to the server. Requests the IP and port to
     // connect to at the view (TUI).
    public void createConnection();

    //disconnects with server
    // Clear sockets and close connection with user.
    public void clearConnection();

    // Sends a message to the connected server, followed by a new line.
    // The stream is then flushed.
    public void sendMessage(String msg) throws ServerUnavailableException;

    // Read messages that came from server
    // parsed message from server to string
    public String readLineFromServer() throws ServerUnavailableException;

    //Handles server-client handshake
    // 1. Client sends command.HELLO to server
    // 2. Server returns one line containing commands.HELLO
    public void doHello() throws ServerUnavailableException;


  //client joins with username, join;name
    public void join() throws ServerUnavailableException;

    //client informs about being ready: ready
    public void ready() throws ServerUnavailableException;

    // Print board.
    // Checks who makes first move,waitForMoveResponse(), call sendMove().
    public void play() throws ServerUnavailableException, IOException;

    // Wait for MOVE;<positions>;<letters>;<directions>!
    //or SWAP;<letters/none>!
    public void waitForMoveResponse() throws ServerUnavailableException, IOException;

    //Send moves from user to server, waitForMoveResponse().
    //call clearConnection().
    public void sendMove(int[] indices) throws ServerUnavailableException, IOException;

    //send message to server client will quit the game
    public void quit() throws ServerUnavailableException;
}
