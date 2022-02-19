package game.protocol.server;

import game.*;
import game.BoardView.boardPrint;
import game.exceptions.InvalidDirectionException;
import game.exceptions.InvalidIndexException;
import game.exceptions.InvalidInputException;
import game.exceptions.InvalidWordException;
import game.exceptions.ServerUnavailableException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import utils.inputToPosition;

import static game.protocol.protocols.commands.*;

public class clientHandler implements Runnable {

    private gameServer gameServer; // server
    public String name; //name of client
    public Player player;

    private BufferedReader in;
    private BufferedWriter out;
    private Socket sock;


    public clientHandler(Socket sock, gameServer server, String name) {
        try {
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
            this.sock = sock;
            this.gameServer = server;
            this.name = name;
        } catch (IOException e) {
            shutdown();
        }
    }

    @Override
    public void run() {
        String line;
        try{
            line = in.readLine();
            while (line != null) {
                System.out.println( name + " : " + line);
                out.flush();
                handleCommands(line);
                gameServer.createGame();
                line = in.readLine();
            }
            shutdown();
        } catch (IOException e) {
            e.printStackTrace();
            shutdown();
        }

    }

    private void shutdown() {
        System.out.println("> [" + name + "] Shutting down.");
        try {
            in.close();
            out.close();
            sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gameServer.removeClient(this);
        try {
            gameServer.endGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void handleCommands(String message) throws IOException{
        String[] split = message.split(DELIMITER, 4);
        String command = split[0];
        String parameter1 = null;
        String parameter2 = null;
        String parameter3 = null;
        System.out.println("lineee " +message);
        out.flush();
        if (split.length >= 2) {
            parameter1 = split[1];
            if (split.length >= 3) {
                parameter2 = split[2];
            } if (split.length == 4) {
                parameter3 = split[3];
            }
        }
        switch (command) {
            case QUIT:
                sendMessage("Shutdown");
                shutdown();
                break;
            case HELLO:
                sendMessage(WELCOME + DELIMITER+name+END);
                break;
            case JOIN:
                if (parameter1 == null) {
                    sendMessage(ERROR+DELIMITER+"provide username"+END);
                }else if (gameServer.join(parameter1)) {
                        name = parameter1;
                        this.player = new Player(name);
                        gameServer.welcome(this);
                        sendMessage(WELCOME+DELIMITER+parameter1+END);
                    } else {
                        sendMessage(ERROR+DELIMITER+"username taken"+END);

                    }
                break;
            case READY:
                if (!gameServer.clientsReady.contains(this)){
                    gameServer.addClientsReady(this);
                }
                break;
            case SWAP:
                //TODO: finish swap
                sendMessage(SWAP);
                break;
            case MOVE:
                if (parameter1 != null && parameter2!=null && parameter3!=null) {
                    //p1 - position (example: H8)
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

                    //p2 - word
                    String letters = parameter2;
                    //p3 -direction ( ver/hor )
                    String direction=parameter3;

                    try {
                        gameServer.doMove(x,y,letters,direction, name);
                    } catch (InvalidIndexException | ServerUnavailableException | InvalidInputException | InvalidWordException | InvalidDirectionException error) {
                        sendMessage(ERROR + DELIMITER + error);
                    }
                } else {
                    sendMessage(ERROR + DELIMITER + "incorrect index, you've lost your turn");
                }
                break;
            default:
                sendMessage(ERROR+DELIMITER+"Incorrect command, you've lost your turn");
        }
    }

    public void createGame(Game game) throws IOException{
            Board boardCopy = game.getBoard().deepCopy();
            String boardFormat = game.getBoard().printBoard(); //todo: CANT SEND FULL BOARD AS LINE
            Player[] player = game.getPlayers();
            String players = player[0].getName()+DELIMITER+player[1].getName();
            sendMessage(GAMESTART+DELIMITER+players);
    }




    public void sendMessage(String msg) throws IOException {
        out.write(msg);
        out.newLine();
        out.flush();
    }

    public Player getPlayer(){
        return this.player;
    }

    public  void  sendMove(int x, int y, String letters, String direction) throws IOException {
        String line = MOVE + DELIMITER + x + DELIMITER + y + DELIMITER + letters + DELIMITER + direction;
        sendMessage(line);
    }


}
