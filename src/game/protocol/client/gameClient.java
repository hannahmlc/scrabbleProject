package game.protocol.client;

import game.Game;
import game.exceptions.ExitProgram;
import game.exceptions.ServerUnavailableException;
import game.protocol.clientProtocol;
import game.protocol.commands;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

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
    public void start() {

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
                for (String serverLine = in.readLine(); serverLine != null && !serverLine.equals(commands.END); serverLine = in.readLine()) {
                    oneLine = oneLine + serverLine + commands.DELIMITER;
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
        sendMessage(commands.Hello + commands.DELIMITER + "Client " + this.name + commands.END); //WELCOME;<clientName>;<names>;!
        clientTUI.printMessage(readLinesFromServer());
    }

    @Override
    public void join() throws ServerUnavailableException {
        String name = clientTUI.getString("Provide username: ");
        sendMessage(commands.JOIN + commands.DELIMITER + name + commands.END);
        //TODO: username taken handle
        this.name = name;
        clientTUI.printMessage(readLinesFromServer());

    }

    @Override
    public void ready() throws ServerUnavailableException {

    }

    @Override
    public void play() throws ServerUnavailableException, IOException {

    }

    @Override
    public void waitForMoveResponse() throws ServerUnavailableException, IOException {

    }

    @Override
    public void sendMove(int[] indices) throws ServerUnavailableException, IOException {

    }

    @Override
    public void quit() throws ServerUnavailableException {
        sendMessage(commands.QUIT+commands.END);
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
}
