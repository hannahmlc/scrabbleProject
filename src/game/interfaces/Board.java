package game.interfaces;

import game.FieldType;
import game.Tile;
import game.exceptions.InvalidInputException;

public interface Board {

    /**
     * places the tile on board
     * @param x - horizontal index on board
     * @param y - diagonal index on board
     * @param tile - tile to place
     * @ensures no tile is put on occupied place during game
     */
    void placeTile(int x, int y, String tile);

    /**
     * @param x - horizontal index on board
     * @param y - diagonal index on board
     * @return field of given index
     */
    String getField(int x, int y);

    /**
     * @param x - horizontal index on board
     * @param y - diagonal index on board
     * @return field up form given index
     */
    String getFieldUp(int x, int y);

    /**
     * @param x - horizontal index on board
     * @param y - diagonal index on board
     * @return field below given index
     */
    String getFieldDown(int x, int y);

    /**
     * @param x - horizontal index on board
     * @param y - diagonal index on board
     * @return field on the left to given index
     */
    String getFieldLeft(int x, int y);

    /**
     * @param x - horizontal index on board
     * @param y - diagonal index on board
     * @return field on the right to given index
     */
    String getFieldRight(int x, int y);

    /**
     * Creates a deep copy of board
     * @ensures the result is a new object, so not this object
     * @ensures the values of all fields of the copy match the ones of this Board
     */
    Board deepCopy();

    /**
     * Empties all fields of this board
     * @ensures all fields are EMPTY
     */
    void reset();


    /**
     * Prints the board and current game situation
     * @return the game situation as String
     */
    String printBoard();

    /**
     * @return special places on board
     */
    FieldType specialFiled(int x, int z);

    /**
     * @return true if board is full (all places are taken)
     * @ensures game checks if the board is full
     */
    boolean isFull();

    /**
     * @param x - horizontal index on board
     * @param y - diagonal index on board
     * checks whatever index are part of board
     * @ensures chosen field isn't out of bounds
     */
    boolean isField(int x, int y);

    /**
     * checks whatever place on board is empty
     * @param x - horizontal index on board
     * @param y - diagonal index on board
     * @ensures chosen field is empty
     */
    boolean isEmptyField(int x, int y);

    /**
     * @param x - horizontal index on board
     * @param y - diagonal index on board
     * @param letters - letters to be placed on board horizontally
     * @requires place where the word will be put, is empty
     * @ensures word is placed horizontally, letters are placed in correct order
     */
    void placeHorizontally(int x, int y, char[] letters) throws InvalidInputException;

    /**
     * @param x - horizontal index on board
     * @param y - diagonal index on board
     * @param letters - letters to be placed on board vertically
     * @requires place where the word will be put, is empty
     * @ensures word is placed vertically, letters are placed in correct order
     */
    void placeVertically(int x, int y, char[] letters) throws InvalidInputException;

    /**
     * Check if created word exist in dictionary
     * @param word - word that needs to be checked
     * @return true if word exist
     * @ensures word exists
     * @requires word isn't null
     */
    boolean isValidWord(String word);

    /**
     * @param score - player's current score
     * @param tile - tile player used
     * @ensures added score to player's score is correct
     */
    void calculateAddScore(int score, Tile tile);
}
