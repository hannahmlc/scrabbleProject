package game.protocol.client;

import game.Board;
import game.Game;
import game.Player;
import game.Tile;
import game.TileBag;
import game.exceptions.ExitProgram;
import game.exceptions.InvalidDirectionException;
import game.exceptions.InvalidIndexException;
import game.exceptions.InvalidInputException;
import game.exceptions.InvalidWordException;
import game.exceptions.ServerUnavailableException;

import static game.protocol.protocols.commands.*;

import game.protocol.protocols.clientProtocol;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import utils.ANSI;
import utils.inputToPosition;


public class gameClient implements clientProtocol {

    private static Game game;
    private boolean lookingForPlayers = false;//waiting for ready from both players to start game

    private Socket sock;
    private BufferedReader in;
    private BufferedWriter out;

    // tui
    private final clientTUI clientTUI;

    //client name
    private String name;

    public gameClient() {
        clientTUI = new clientTUI(this);
    }

    @Override
    public void start() {
        try {
            createConnection();
            doHello();
            join();
            clientTUI.start();
        } catch (ServerUnavailableException | ExitProgram | IOException | InvalidIndexException | InvalidInputException | InvalidWordException | InvalidDirectionException e) {
            clientTUI.handleError(e.getMessage());
            if (clientTUI.getBoolean("Do you want to start a new connection ?")) {
                start();
            } else {
                clientTUI.printMessage("See you later!");
            }
        }
    }

    @Override
    public void createConnection() throws ExitProgram {
        clearConnection();

        while (sock == null) {
            String host = clientTUI.getString("Provide " + ANSI.GREEN_BOLD + "IP: " + ANSI.RESET);
            int port = clientTUI.getPort("Provide " + ANSI.GREEN_BOLD + "port: " + ANSI.RESET);

            // try to open a Socket to the server
            try {
                InetAddress addr = InetAddress.getByName(host);
                clientTUI.printMessage("Attempting to connect to " + addr + " : " + port + "...");

                sock = new Socket(addr, port);
                in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));

            } catch (IOException e) {
                throw new ExitProgram("ERROR: could not create a socket on "
                    + host + " and port " + port + ".");

            }
        }

    }

    @Override
    public void clearConnection() {
        sock = null;
        in = null;
        out = null;
    }

    @Override
    public synchronized void sendMessage(String msg) throws ServerUnavailableException {
        try {
            out.write(msg);
            out.newLine();
            out.flush();

        } catch (IOException e) {
            throw new ServerUnavailableException("Server unavailable, couldn't write to server ");
        }
    }

    @Override
    public String readLinesFromServer() throws ServerUnavailableException, IOException {
        if (in != null) {
            try {
                String line = in.readLine();
                return line;
            } catch (IOException e) {
                throw new ServerUnavailableException("Could not read from server (no response form server)");
            }
        } else {
            throw new ServerUnavailableException("Could not read from server (no response form server)");
        }
    }

    @Override
    public void doHello() throws ServerUnavailableException, IOException {
        sendMessage(HELLO + DELIMITER + "Client " + this.name + END); //WELCOME;<clientName>;<names>;!
        clientTUI.printMessage(readLinesFromServer());
    }

    @Override
    public void join() throws ServerUnavailableException, IOException {
        String name = clientTUI.getString("Provide username: ");
        sendMessage(JOIN + DELIMITER + name + END);
        String response = readLinesFromServer();
        String join[] =response.split(DELIMITER);
      if(join[0].equals(ERROR)){
          clientTUI.printMessage(join[1]);
          join();
       } else{
           clientTUI.printMessage(response);
        }
        this.name = name;
    }

    @Override
    public void ready() throws ServerUnavailableException, IOException, InvalidIndexException, InvalidInputException,
        InvalidWordException, InvalidDirectionException {
        sendMessage(READY+DELIMITER+END);
        lookingForPlayers = !lookingForPlayers;
        if (lookingForPlayers) {
            clientTUI.printMessage("Waiting for another player to start a a game....");
            waitForPlayers();
        }
    }

    @Override
    public void waitForPlayers()
        throws IOException, ServerUnavailableException, InvalidIndexException, InvalidInputException,
        InvalidWordException, InvalidDirectionException {
        while (lookingForPlayers) {

            String serverResponse = readLinesFromServer();
            String[] serverResponsesSplit = serverResponse.split(DELIMITER);
            if (serverResponse.contains(GAMESTART)) {
                clientTUI.printMessage(START+END);

                String[] split = serverResponse.split(DELIMITER);


                Player p1 = new Player(split[1]);
                Player p2 = new Player(split[2]);
                List<Tile> bagOfTiles = TileBag.generateTiles();
                game = new Game(p1, p2,bagOfTiles);
                clientTUI.printMessage(game.getBoard().printBoard());

                lookingForPlayers = false;
                play();
            }

        }
    }

    @Override
    public void play() throws ServerUnavailableException, IOException, InvalidIndexException, InvalidInputException,
        InvalidWordException, InvalidDirectionException {
        String currentPlayer = game.getCurrentPlayer().getName();
        if(currentPlayer.equals(name)){ //check if player making move is this client
            String line = clientTUI.getString("example move: MOVE;H8;RICE;VER || SWAP;ABCD ||SWAP; (swap without letter is considered skipping a move)");
            String[] split = line.split(DELIMITER,4);
            if (split[0].equals(MOVE) && split.length >= 3) {
                doMove(line);
            } else if (split[0].equals(SWAP) && split.length >= 3){
                sendSwap(line);
            } else {
                clientTUI.printMessage("ERROR, wrong command");
                play();
            }
        }else{
            clientTUI.printMessage("other player is making a move");;
            waitMove();
        }
    }

    @Override
    public void sendSwap(String line)
        throws ServerUnavailableException, IOException, InvalidIndexException, InvalidInputException,
        InvalidWordException, InvalidDirectionException {
        sendMessage(SWAP+DELIMITER+line);
        waitMove();
    }

    @Override
    public void doMove(String line)
        throws ServerUnavailableException, IOException, InvalidIndexException, InvalidInputException,
        InvalidWordException, InvalidDirectionException {
        sendMessage(MOVE + DELIMITER +line);
        waitMove();
    }

    @Override
    public void waitMove() throws ServerUnavailableException, IOException, InvalidIndexException, InvalidInputException,
        InvalidWordException, InvalidDirectionException {

        String serverResponse = readLinesFromServer();
        System.out.println(serverResponse);
        if (serverResponse.contains(ERROR)) {
            clientTUI.printMessage(serverResponse.replace(ERROR, ""));
            play();
        } else if (serverResponse.contains(MOVE)) {
            String[] split = serverResponse.split(DELIMITER, 4);
            String parameter1 = split[1];
            int x;
            char charY = parameter1.charAt(0);
            int y =  inputToPosition.getPositionFromLetter(charY);

            if (parameter1.length()>2){
                String StringX = parameter1.substring(1);
                x = inputToPosition.getPositionFromString(StringX);
            }else{
                char charX = parameter1.charAt(1);
                x = inputToPosition.getPositionFromNumber(charX);
            }
            x = x- 1;//array indexing
            y = y - 1;//array indexing

            char[] letters = split[2].toCharArray();
            String direction =split[3];
            try {
                Board board = game.getCurrentPlayer().playerMove(game.getBoard(),x,y,letters,direction);
                game.setBoard(board);
                if (!in.ready()) {
                    clientTUI.printMessage(game.getBoard().printBoard());
                    play();
                } else {
                    waitMove();
                }
            } catch (IOException | ServerUnavailableException e) {
                e.printStackTrace();
            }
        } else if (serverResponse.contains(GAMEOVER)) {
            clientTUI.printMessage(serverResponse);
            game = null;
        } else {
            System.out.println(serverResponse);
        }
    }


    @Override
    public void quit() throws ServerUnavailableException, IOException {
        sendMessage(QUIT+END);
        //clientTUI.printMessage(readLinesFromServer());
        closeConnection();
    }

    public void closeConnection() {
        clientTUI.printMessage("You've quited, closing connection...");
        try {
            in.close();
            out.close();
            sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        (new gameClient()).start();
    }
}
