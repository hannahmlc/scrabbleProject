package game.interfaces;

import game.FieldType;
import game.FieldType;

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
     * @ensures choosen field isnt out of bounds
     */
    boolean isField(int x, int y);

    /**
     * checks whatever place on board is empty
     * @param x, y - indexes\
     * @ensures choosend field is empty
     */
    boolean isEmptyField(int x, int y);

    /**
     * @param x - index
     * @param y - index
     * @param letters - letters to be placed
     * places word on board horizontally
     * @requires place wher the word will be put, is empty
     * @ensures
     */
    public void placeHorizontally(int x, int y, char[] letters);

    /**
     * @param x - index
     * @param y - index
     * @param letters - letters to be placed
     * places word on board horizontally
     * @requires place wher the word will be put, is empty
     */
    public void placeVertically(int x, int y, char[] letters);

    /**
     * Check if created word exist in dictionary
     * @return true if word exist
     * @ensures word exists
     */
    public boolean isValidWord(String word);
}
