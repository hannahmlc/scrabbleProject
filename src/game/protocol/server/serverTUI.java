package game.protocol.server;

import game.protocol.client.gameClient;
import java.util.Scanner;

public class serverTUI {

   gameServer gameServer;

    public serverTUI(gameServer server) {
        gameServer = server;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public String getString(String prompt) {
        printMessage(prompt);
        String string = new Scanner(System.in).nextLine();
        return string;
    }


    public int getPort(String prompt) {
        System.out.println(prompt);
        int port = Integer.parseInt(new Scanner(System.in).nextLine());
        return port ;
    }

}
