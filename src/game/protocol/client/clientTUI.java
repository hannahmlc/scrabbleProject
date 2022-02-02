package game.protocol.client;

import static game.protocol.commands.*;

import game.exceptions.*;
import java.io.IOException;
import java.util.Scanner;


public class clientTUI {

    private final gameClient gameClient;

    public clientTUI(gameClient client) {
        gameClient = client;
    }

    public void start() throws ServerUnavailableException, IOException {
        String input = getString("Write: READY to start a game or QUIT to exit ");
        handleUserInput(input+END);
    }

    private void handleUserInput(String input) throws ServerUnavailableException, IOException {

        switch (input) {
            case QUIT+END:
                gameClient.quit();
                break;
            case READY+END:
                gameClient.ready();
               break;
            default:
                printMessage("Unknown command");
                start();
        }
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

    public void handleError(String error) {
        //TODO: handle ERRORS prompts
    }
}
