package game.protocol.client;

import static game.protocol.protocols.commands.*;

import game.exceptions.*;
import java.io.IOException;
import java.util.Scanner;


public class clientTUI {

    private final gameClient gameClient;

    public clientTUI(gameClient client) {
        gameClient = client;
    }

    public void start() throws ServerUnavailableException, IOException, InvalidIndexException, InvalidInputException,
        InvalidWordException, InvalidDirectionException {
        String input = getString("Write: READY to start a game or QUIT to exit ");
        handleUserInput(input);
    }

    private void handleUserInput(String input)
        throws ServerUnavailableException, IOException, InvalidIndexException, InvalidInputException,
        InvalidWordException, InvalidDirectionException {

        switch (input) {
            case QUIT:
                gameClient.quit();
                break;
            case READY:
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
        printMessage(prompt);
        int port = Integer.parseInt(new Scanner(System.in).nextLine());
        return port ;
    }
    public boolean getBoolean(String prompt) {
        printMessage(prompt);
        boolean answer = Boolean.parseBoolean(new Scanner(System.in).nextLine());
        return answer;
    }

    public void handleError(String error) {
        printMessage(error);
    }
}
