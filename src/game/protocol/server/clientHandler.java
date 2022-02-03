package game.protocol.server;

import game.exceptions.ServerUnavailableException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import static game.protocol.commands.*;

public class clientHandler implements Runnable {

    private gameServer gameServer; // server
    public String name; //name of client

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

    private void shutdown() {
        System.out.println("Shut down"+END);
        try {
            in.close();
            out.close();
            sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gameServer.removeClient(this);
    }


    public void handleCommands(String message) throws IOException{
        String[] split = message.split(DELIMITER, 4);
        String command = split[0];
        String parameter1 = null;
        String parameter2 = null;
        String parameter3 = null;

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
                out.write("Shutdown");
                break;
            case WELCOME:
                out.write(WELCOME + DELIMITER+name);
                break;
            case HELLO:
                out.write(WELCOME + DELIMITER+name);
                break;
            case JOIN:
                if (parameter1 == null) {
                    out.write(ERROR+DELIMITER+"provide username"+END);
                    out.newLine();
                    out.flush();
                }else if (gameServer.join(parameter1)) {
                        name = parameter1;
                        gameServer.welcome(this);
                        out.write(WELCOME+DELIMITER+parameter1+END);
                        out.newLine();
                        out.flush();
                    } else {
                        out.write(ERROR+DELIMITER+"username taken"+END);
                        out.newLine();
                        out.flush();
                    }
                break;
            default:
                out.write(ERROR+DELIMITER+"incorrect command");
                out.newLine();
                out.flush();
        }
    }



    @Override
    public void run() {
        String line;
        try{
           line = in.readLine();
            while (line != null) {
                System.out.println("[" + name + "] Incoming: " + line);
                handleCommands(line);
                //new game
                //TODO: rechek if it shouldn't be in handle commands
                gameServer.createGame();
                line = in.readLine();
            }
            shutdown();
        } catch (IOException e) {
            e.printStackTrace();
            shutdown();
        }

    }
}
