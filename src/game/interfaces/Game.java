package game.interfaces;

public interface Game {

    /**
     * Check if created word exist in dictionary
     * @return true if word exist
     * @ensures word exists
     */
    public boolean isValidWord();
}
