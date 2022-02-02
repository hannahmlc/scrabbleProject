package game.protocol.client;

import game.exceptions.ExitProgram;
import game.exceptions.ServerUnavailableException;
import java.io.IOException;

public class clientTUI {

    private final gameClient gameClient;

    public clientTUI(gameClient client) {
        gameClient = client;
    }

    public void start() throws ServerUnavailableException {
        String input = getString("Provide input: ");
        handleUserInput(input);
    }

    private void handleUserInput(String input) {
    }

    public void printMessage(String message) {

    }

    public String getString(String prompt) {
        String string = "";
        return string;
    }


    public int getPort(String prompt) {
        return 0;
    }
}
