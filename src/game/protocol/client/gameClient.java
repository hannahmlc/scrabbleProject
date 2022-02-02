package game.protocol.client;

import game.Board;
import game.Game;
import game.Player;
import game.Tile;
import game.TileBag;
import game.exceptions.ExitProgram;
import game.exceptions.ServerUnavailableException;
import game.protocol.*;
import static game.protocol.commands.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import javax.print.DocFlavor;

public class gameClient implements clientProtocol {

    private static Game currentGame;

    private Socket serverSock;
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
    public void start() throws ExitProgram, ServerUnavailableException {
        try {
            createConnection();
            doHello();
            join();
            clientTUI.start();
        } catch (ServerUnavailableException | ExitProgram | IOException e) {
            clientTUI.handleError(String.valueOf(e));
            //TODO: make so that it can start new connection insetad of closing
        }

    }

    @Override
    public void createConnection() throws ExitProgram {

        clearConnection();

        while (serverSock == null) {
            String host = clientTUI.getString("Provide ip of server: ");
            int port = clientTUI.getPort("Provide port of server: ");

            // try to open a Socket to the server
            try {
                InetAddress addr = InetAddress.getByName(host);
                clientTUI.printMessage("Attempting to connect to " + addr + ":"
                    + port + "...");
                serverSock = new Socket(addr, port);
                in = new BufferedReader(new InputStreamReader(
                    serverSock.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(
                    serverSock.getOutputStream()));
            } catch (IOException e) {
                throw new ExitProgram("ERROR: could not create a socket on "
                    + host + " and port " + port + ".");
            }
        }

    }

    @Override
    public void clearConnection() {
        serverSock = null;
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
    public String readLinesFromServer() throws ServerUnavailableException {
        if (in != null) {
            String oneLine ="";
            try {
                for (String serverLine = in.readLine(); serverLine != null && !serverLine.equals(END); serverLine = in.readLine()) {
                    oneLine = oneLine + serverLine + DELIMITER;
                }
                return oneLine;
            }catch (IOException e) {
                throw new ServerUnavailableException("Could not read from server (no response form server)");
            }
        }else {
            throw new ServerUnavailableException("Could not read from server (no response form server)");
        }
    }

    @Override
    public void doHello() throws ServerUnavailableException {
        sendMessage(HELLO + DELIMITER + "Client " + this.name + END); //WELCOME;<clientName>;<names>;!
        clientTUI.printMessage(readLinesFromServer());
    }

    @Override
    public void join() throws ServerUnavailableException {
        String name = clientTUI.getString("Provide username: ");
        sendMessage(JOIN + DELIMITER + name + END);
        //TODO: username taken(2 cant be the same) handle
        this.name = name;
        clientTUI.printMessage(readLinesFromServer());

    }

    @Override
    public void ready() throws ServerUnavailableException, IOException {
        sendMessage(READY+END);
        lookingForPlayers = !lookingForPlayers;
        if (lookingForPlayers) {
            clientTUI.printMessage("Waiting for another player to start a a game....");
            waitForPlayers();
        }
    }

    @Override
    public void play() throws ServerUnavailableException, IOException {
        //TODO: play the game
    }

    @Override
    public void waitMove() throws ServerUnavailableException, IOException {
        //TODO: wait for move input
    }

    @Override
    public void sendMove(int[] indices) throws ServerUnavailableException, IOException {
        //TODO: send move input to server
    }

    @Override
    public void quit() throws ServerUnavailableException {
        sendMessage(QUIT+END);
        clientTUI.printMessage(readLinesFromServer());
        closeConnection();
    }

    public void closeConnection() {
        clientTUI.printMessage("You've quited, closing connection...");
        try {
            in.close();
            out.close();
            serverSock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean lookingForPlayers = false;
    private static Game game;
    @Override
    public void waitForPlayers() throws IOException, ServerUnavailableException {
        while (lookingForPlayers) {
            if (in.ready()) {
                String severOutput = readLinesFromServer();
                if (severOutput.contains(GAMESTART)) {
                    System.out.println("Game have started");

                    String[] split = severOutput.split(DELIMITER);


                    Player player1 = new Player(split[1]);
                    Player player2 = new Player(split[2]);
                    List<Tile> bagOfTiles = TileBag.generateTiles();
//TODO: finsih game creation
                    this.game = new Game(player1, player2,bagOfTiles);

                    clientTUI.printMessage(game.getBoard().printBoard());
                    lookingForPlayers = false;
                    play();
                }
            }
        }
    }
}
