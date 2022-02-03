package game.protocol.server;

import game.exceptions.ExitProgram;
import game.exceptions.ServerUnavailableException;
import game.protocol.serverProtocol;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import utils.ANSI;
import static game.protocol.commands.*;

public class gameServer implements serverProtocol {

    private ServerSocket sock;
    private int nextClientNumber; //number of  next client
    private List<clientHandler> clients;
    private List<String> clientNames;

    private final List<clientHandler> queue; // queue where player waits till other player connect to start game

    //tui
    private final serverTUI serverTUI;

    public gameServer() {
        serverTUI = new serverTUI(this);
        clients = new ArrayList<>();
        clientNames = new ArrayList<>();
        queue = new ArrayList<>();
        nextClientNumber = 1;
    }


    @Override
    public void start() {

    }

    @Override
    public void setup() throws ExitProgram {
        sock = null;
        while (sock == null) {
            int port = serverTUI.getPort("Provide " + ANSI.GREEN_BOLD + "port: " + ANSI.RESET);
            String host = serverTUI.getString("Provide " + ANSI.GREEN_BOLD + "IP: " + ANSI.RESET);
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
        serverTUI.printMessage(WELCOME + client.name+END);
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
        }
        return !clientNames.contains(name);
    }

    @Override
    public void createGame() throws IOException, ServerUnavailableException {

    }

    @Override
    public void sendTiles() throws IOException, ServerUnavailableException {

    }

    @Override
    public void doMove() {

    }

    @Override
    public void endGame() {

    }
}
