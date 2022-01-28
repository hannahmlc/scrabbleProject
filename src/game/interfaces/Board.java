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
     * @param tile - tile to place
     */
    public void setTile(int x, int y, String tile);

    public String getField(int x, int y);
    public String getFieldUp(int x, int y);
    public String getFieldDown(int x, int y);
    public String getFieldLeft(int x, int y);
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

    public boolean isFull();

    /**
     * checks whatever index are part of board
     * @ensures chosen field isn't out of bounds
     */
    boolean isField(int x, int y);

    /**
     * checks whatever place on board is empty
     * @param x, y - indexes\
     * @ensures chosen field is empty
     */
    boolean isEmptyField(int x, int y);

    /**
     * @param x - index
     * @param y - index
     * @param letters - letters to be placed on board horizontally
     * @requires place where the word will be put, is empty
     * @ensures
     */
    public void placeHorizontally(int x, int y, char[] letters) throws InvalidInputException;

    /**
     * @param x - index
     * @param y - index
     * @param letters - letters to be placed on board vertically
     * @requires place where the word will be put, is empty
     */
    public void placeVertically(int x, int y, char[] letters) throws InvalidInputException;

    /**
     * Check if created word exist in dictionary
     * @return true if word exist
     * @ensures word exists
     */
    public boolean isValidWord(String word);
}
