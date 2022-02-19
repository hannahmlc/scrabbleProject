package game.protocol.server;

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
import game.protocol.protocols.serverProtocol;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import utils.ANSI;
import static game.protocol.protocols.commands.*;

public class gameServer implements serverProtocol, Runnable{

    private ServerSocket sock;
    private int nextClientNumber; //number of  next client
    private final List<clientHandler> clients;
    private final List<String> clientNames;
    List<Tile> bag = TileBag.generateTiles(); //bag for the game, this server is able to handle only game at time so tile bag is only one
    public List<clientHandler> clientsReady;//name of clients ready to play, it 2 - start a game
    public Game game;

    //tui
    private final serverTUI serverTUI;

    public gameServer() {
        serverTUI = new serverTUI(this);
        clients = new ArrayList<>();
        clientNames = new ArrayList<>();
        clientsReady = new ArrayList<>();
        nextClientNumber = 1;
    }


    @Override
    public void setup() throws ExitProgram {
        sock = null;
        while (sock == null) {
            String host = serverTUI.getString("Provide " + ANSI.GREEN_BOLD + "IP: " + ANSI.RESET);
            int port = serverTUI.getPort("Provide " + ANSI.GREEN_BOLD + "port: " + ANSI.RESET);
            try {
                InetAddress addr = InetAddress.getByName(host);
                serverTUI.printMessage("Attempting to connect to " + addr + " : " + port + "...");

                sock = new ServerSocket(port, 0, addr);

                serverTUI.printMessage("Server started at port " + port);

            } catch (IOException e) {
                throw new ExitProgram("ERROR: could not create a socket on "
                    + host + " and port " + port + ".");
                }
        }
    }


    @Override
    public void welcome(clientHandler client) {
        serverTUI.printMessage(WELCOME +DELIMITER + client.name+END);
    }

    @Override
    public void removeClient(clientHandler client) {
        clients.remove(client);
        clientNames.remove(client.name);
    }

    @Override
    public boolean join(String name) {
        if (!clientNames.contains(name)) {
            clientNames.add(name);
            return true;
        }
        else return false;
    }

    @Override
    public void createGame() throws IOException{
        if(clientsReady.size()>=2){

            clientHandler p1 = clientsReady.get(0);
            clients.add(clientsReady.get(0));
            clientsReady.remove(0);

            clientHandler p2 = clientsReady.get(0);
            clients.add(clientsReady.get(0));
            clientsReady.remove(0);
            this.game = new Game(p1, p2,bag);
            p1.createGame(game);
            p2.createGame(game);
        }
    }

    public void addClientsReady(clientHandler client){
        this.clientsReady.add(client);
    }

    @Override
    public void sendTiles(clientHandler client) throws IOException {
        List<Tile> rack = client.getPlayer().getRack();
        String rackString= rack.get(0).getLetter();
        for(int i=1; i<rack.size();i++){
            rackString+= DELIMITER+rack.get(i).getLetter();
        }
        client.sendMessage(TILES+DELIMITER+rackString);
    }

    @Override
    public void doMove(int x, int y, String letters, String direction, String name)
        throws InvalidIndexException, ServerUnavailableException, IOException, InvalidInputException,
        InvalidWordException, InvalidDirectionException {

        clientHandler[] clients = game.getClientPlayers();
        clientHandler p1 = clients[0];
        clientHandler p2 = clients[1];
        Player currentPlayer;
        clientHandler currentClient;

        if (p1.name.equals(name)){
             currentPlayer = p1.getPlayer();
            currentClient = p1;
        } else {
            currentPlayer = p2.getPlayer();
            currentClient = p1;
        }
        Board board = this.game.getBoard();
       //sendTiles(currentClient);
        board = currentPlayer.playerMove(board,x, y, letters, direction);
        System.out.println(board.printBoard()); //CHECK IF BOARD IS ACTUALLY changed //todo: GO BACK, AFTER CHECK SERVER RECIFES FORM PLAYER MOVE INGO CORRECTLY

        //todo: GAMEOVER IS BEING IGNORED GAME LAST FOREVER
        p1.sendMove(x, y,  letters, direction);
        p2.sendMove(x, y,  letters, direction);

        /*
        if (game.gameOver()) {
            Player winner = game.winner(p1.getPlayer(),p2.getPlayer());
            String reason = "reason "; // TODO: GAME OVER REASON
            //TODO: PLAYERS CLIENTS SEND GAME OVER
        } else {
            p1.sendMove(x, y,  letters, direction);
            p2.sendMove(x, y,  letters, direction);
        }*/

    }

    @Override
    public void endGame() throws IOException {
        if(clientsReady.size()>=2){
            clientHandler p1 = clients.get(0);
            clientHandler p2 = clients.get(1);
            //TODO: finish\
            // GAMEOVER;<endType(WINNER, DRAW, STOP)>;<names>;<points>!
            p1.sendMessage(GAMEOVER);
            p2.sendMessage(GAMEOVER);
        }
        else {
            serverTUI.printMessage(ERROR);
        }

    }

    public static void main(String[] args) {
        gameServer server = new gameServer();
        new Thread(server).start();
    }


    @Override
    public void run() {
        boolean openNewSocket = true;
        while (openNewSocket) {
            try {
                setup();

                while (true) {
                    Socket sock2 = sock.accept();
                    String name = "Client " + String.format("%02d", nextClientNumber++);
                    serverTUI.printMessage("New client [" + name + "] connected!");
                    clientHandler handler = new clientHandler(sock2, this, name);
                    new Thread(handler).start();
                    clients.add(handler);
                }
            } catch (ExitProgram e1) {
                // If setup() throws an ExitProgram exception,
                // stop the program.
                openNewSocket = false;
            } catch (IOException e) {
                System.out.println("A server IO error occurred: "
                    + e.getMessage());
                if (!serverTUI.getBoolean("Do you want to open a new socket?")) { //yes / no
                    openNewSocket = false;
                }
            }
        }
        serverTUI.printMessage("See you later!");
    }

}
