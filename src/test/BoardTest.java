package test;

import static org.junit.jupiter.api.Assertions.*;

import game.Board;
import game.FieldType;
import game.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {

    private Board board;
   // private Tile tile1;
   // private Tile tile2;

    @BeforeEach
    public void setUp() {
        board = new Board();
       // tile1 = new Tile("A",1);
    }

    @Test
    public void testPlaceTile(){
        board.placeTile(0,1,"A");
        assertEquals("A",board.getField(0,1));
    }

    @Test
    public void testGetField(){
        assertNull(board.getField(0, 1));
        board.placeTile(2,3,"B");
        String field = board.getField(2,3);
        assertEquals("B",field);
    }


    @Test
    public void testFieldType(){
        assertEquals(FieldType.CENTER,board.fieldType(7,7));
        assertEquals(FieldType.TRIPLE_L,board.fieldType(9,5));
        assertEquals(FieldType.NORMAL,board.fieldType(14,13));
    }

    @Test
    public void testIsValidWord(){
        assertTrue(board.isValidWord("dog"));
        assertFalse(board.isValidWord("asd"));
    }



}