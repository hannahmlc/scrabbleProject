package game.interfaces;

import game.Letters;

public interface Board {

    /**
     * places the tile on board
     * @param x - horizontal index on board
     * @param y - diagonal index on board
     * @param tile - tile to place
     */
    public void placeTile(int x, int y, Letters tile);


    public Letters getField(int x, int y);
    public Letters getFieldUp(int x, int y);
    public Letters getFieldDown(int x, int y);
    public Letters getFieldLeft(int x, int y);
    public Letters getFieldRight(int x, int y);

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


}
