package game.protocol.protocols;

import game.exceptions.ExitProgram;
import game.exceptions.InvalidDirectionException;
import game.exceptions.InvalidIndexException;
import game.exceptions.InvalidInputException;
import game.exceptions.InvalidWordException;
import game.exceptions.ServerUnavailableException;
import java.io.IOException;

//TODO: add needed parameters to methods
public interface clientProtocol {

    // starts connection with server
    void start() throws ExitProgram, ServerUnavailableException;

     // Creates a connection to the server. Requests the IP and port to
     // connect to at the view (TUI).
     void createConnection() throws ExitProgram;

    //disconnects with server
    // Clear sockets and close connection with user.
    void clearConnection();

    // Sends a message to the connected server, followed by a new line.
    // The stream is then flushed.
    void sendMessage(String msg) throws ServerUnavailableException;

    // Read messages that came from server
    // parsed message from server to string
    public String readLinesFromServer() throws ServerUnavailableException, IOException;

    //Handles server-client handshake
    // 1. Client sends command.HELLO to server
    // 2. Server returns one line containing commands.HELLO
    public void doHello() throws ServerUnavailableException, IOException;


  //client joins with username, join;name
    void join() throws ServerUnavailableException, IOException;

    //client informs about being ready: ready
    void ready() throws ServerUnavailableException, IOException, InvalidIndexException, InvalidInputException,
        InvalidWordException, InvalidDirectionException;

    // Print board.
    // Checks who make first move,waitMove(), call sendMove().
    void play() throws ServerUnavailableException, IOException, InvalidIndexException, InvalidInputException,
        InvalidWordException, InvalidDirectionException;

    void sendSwap(String line)
        throws ServerUnavailableException, IOException, InvalidIndexException, InvalidInputException,
        InvalidWordException, InvalidDirectionException;

    // Wait for MOVE;<positions>;<letters>;<directions>!
    //or SWAP;<letters/none>!
    void waitMove() throws ServerUnavailableException, IOException, InvalidIndexException, InvalidInputException,
        InvalidWordException, InvalidDirectionException;

    //Send moves from user to server, waitForMove().
    //call clearConnection().
    void doMove(String move)
        throws ServerUnavailableException, IOException, InvalidIndexException, InvalidInputException,
        InvalidWordException, InvalidDirectionException;

    //send message to server client will quit the game
    void quit() throws ServerUnavailableException, IOException;

    //close connection after client quits/exit
    void closeConnection();

    //wait for both player to type ready top start game
    void waitForPlayers() throws IOException, ServerUnavailableException, InvalidIndexException, InvalidInputException,
        InvalidWordException, InvalidDirectionException;
}
