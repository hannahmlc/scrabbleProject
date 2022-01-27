package game.interfaces;

import game.Letters;
import java.util.List;

public interface Game {

    /**
     * Check if created word exist in dictionary
     * @return true if word exist
     * @ensures word exists
     */
    public boolean isValidWord();

    /**
     * add letters to player rack
     */
    public List<Letters> generateRack();
}
