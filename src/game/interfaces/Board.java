package game.interfaces;

import game.FieldType;
import game.exceptions.InvalidInputException;

public interface Board {

    /**
     * places the tile on board
     * @param x - horizontal index on board
     * @param y - diagonal index on board
     * @param tile - tile to place
     * @ensures no tile is put on occupied place during game
     */
    public void placeTile(int x, int y, String tile);

    /**
     * set the tile on board
     * @param x - horizontal index on board
     * @param y - diagonal index on board
     * @param letter - tile to place
     */
    public void setTile(int x, int y, String letter);

    /**
     * @param x - horizontal index on board
     * @param y - diagonal index on board
     * @return field of given index
     */
    public String getField(int x, int y);

    /**
     * @param x - horizontal index on board
     * @param y - diagonal index on board
     * @return field up form given index
     */
    public String getFieldUp(int x, int y);

    /**
     * @param x - horizontal index on board
     * @param y - diagonal index on board
     * @return field below given index
     */
    public String getFieldDown(int x, int y);

    /**
     * @param x - horizontal index on board
     * @param y - diagonal index on board
     * @return field on the left to given index
     */
    public String getFieldLeft(int x, int y);

    /**
     * @param x - horizontal index on board
     * @param y - diagonal index on board
     * @return field on the right to given index
     */
    public String getFieldRight(int x, int y);

    /**
     * Creates a deep copy of board
     * @ensures the result is a new object, so not this object
     * @ensures the values of all fields of the copy match the ones of this Board
     */
    public Board deepCopy();

    /**
     * Empties all fields of this board
     * @ensures all fields are EMPTY
     */
    public void reset();


    /**
     * Prints the board and current game situation
     * @return the game situation as String
     */
    public String printBoard();

    /**
     * @return special places on board
     */
    public FieldType specialFiled(int x, int z);

    /**
     * @return true if board is full (all places are taken)
     * @ensures game checks if the board is full
     */
    public boolean isFull();

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
    public void placeHorizontally(int x, int y, char[] letters) throws InvalidInputException;

    /**
     * @param x - horizontal index on board
     * @param y - diagonal index on board
     * @param letters - letters to be placed on board vertically
     * @requires place where the word will be put, is empty
     * @ensures word is placed vertically, letters are placed in correct order
     */
    public void placeVertically(int x, int y, char[] letters) throws InvalidInputException;

    /**
     * Check if created word exist in dictionary
     * @param word - word that needs to be checked
     * @return true if word exist
     * @ensures word exists
     * @requires word isn't null
     */
    public boolean isValidWord(String word);
}
