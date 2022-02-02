package game.protocol;

public class commands {

     // List of messages used in client-server communication.

    public static final String HELLO = "HELLO";// for server-client handshake

    public static final String JOIN = "JOIN";
    public static final String READY = "READY";
    public static final String MOVE = "MOVE";
    public static final String SWAP = "SWAP";
    public static final String QUIT = "QUIT"; //client ends game

    public static final String WELCOME = "WELCOME";
    public static final String ERROR = "ERROR";
    public static final String START = "START"; //GAMESTART
    public static final String TILES = "TILES"; //Sends client which tiles they have.
    public static final String CURRENT = "CURRENT"; //Informs all clients after a turn whose turn it is.
    public static final String UPDATE = "UPDATE"; //Send the board to all players and update all points.
    public static final String GAMEOVER = "GAMEOVER";
    public static final String GAMESTART = "GAMESTART";

    public static final String DELIMITER = ";";
    public static final String END = "!";

}
