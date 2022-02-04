package game.protocol.server;

import game.Game;
import game.Tile;
import game.TileBag;
import game.exceptions.ExitProgram;
import game.exceptions.InvalidIndexException;
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
    private List<clientHandler> clients;
    private List<String> clientNames;
    List<Tile> bag = TileBag.generateTiles(); //bag for the game, this server is able to handle only game at time so tile bag is only one
    public List<clientHandler> clientsReady;//name of clients ready to play, it 2 - start a game


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
            clientsReady.remove(0);
            clientHandler p2 = clientsReady.get(0);
            clientsReady.remove(0);
            Game game = new Game(p1, p2,bag);
            p1.createGame(game);
            p2.createGame(game);
        }
    }

    public void addClientsReady(clientHandler client){
        this.clientsReady.add(client);
    }

    @Override
    public void sendTiles(clientHandler client) throws IOException, ServerUnavailableException {
        List<Tile> rack = client.getPlayer().getRack();
        String rackString= rack.get(0).getLetter();
        for(int i=1; i<rack.size();i++){
            rackString+= DELIMITER+rack.get(i).getLetter();
        }
        client.sendMessage(TILES+DELIMITER+rackString);
    }

    @Override
    public void doMove(int[] move,String word, String direction, String name) throws InvalidIndexException {

    }

    @Override
    public void endGame() throws IOException {
        clientHandler p1 = clients.get(0);
        clientHandler p2 = clients.get(1);
            //TODO: finish\
        // GAMEOVER;<endType(QWINNER, DRAW, STOP)>;<names>;<points>!
       p1.sendMessage(GAMEOVER);
       p2.sendMessage(GAMEOVER);
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
                // Sets up the hotel application
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
