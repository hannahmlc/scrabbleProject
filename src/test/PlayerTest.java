package test;

import static org.junit.jupiter.api.Assertions.*;

import game.Player;

import game.Tile;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private Player player;
    private List<Tile> rack  = new ArrayList<>(7);
    private int score;
    private boolean continues = true;

    private List<Tile> bag = new ArrayList<>(7);
    private Tile A = new Tile("A",1 );
    private Tile B = new Tile("B",3 );
    private Tile C = new Tile("C",3 );
    private Tile D = new Tile("D",2 );
    private Tile E = new Tile("E",1 );
    private Tile F = new Tile("F",4 );
    private Tile G = new Tile("G",2 );

    @BeforeEach
    public void setUp() {
        player = new Player("name");
        bag.add(A);
        bag.add(B);
        bag.add(C);
        bag.add(D);
        bag.add(E);
        bag.add(F);
        bag.add(G);
    }

    @Test
    public void testAddLetters(){
        player.addLetters(bag);
        assertEquals(7,player.getRack().size());
        assertTrue(player.getRack().contains(A));
        assertFalse(player.getRack().contains(null));
    }

    @Test
    public void testRemoveLetters(){
        player.getRack().add(A);
        player.getRack().add(C);
        char[] letters = {'A'};
        player.removeLetters(player.getRack(),letters);
        assertFalse(player.getRack().contains(A));
    }

    @Test
    public void testPrintRack(){
        player.getRack().add(A);
        player.getRack().add(A);
        player.getRack().add(C);
        player.getRack().add(D);
        player.getRack().add(E);
        player.getRack().add(A);
        player.getRack().add(G);
        String rackString = "[A] [A] [C] [D] [E] [A] [G] ";
        assertEquals(rackString,player.printRack(player.getRack()));

    }

    @Test
    public void testhHasLetters(){
        player.getRack().add(A);
        assertTrue(player.hasLetter('A'));
        assertFalse(player.hasLetter('D'));

    }
    @Test
    public void testTileLetters(){
        List<String> tileLetters =new ArrayList<>(2);
        tileLetters.add("A");
        tileLetters.add("G");
        List<Tile> tiles = new ArrayList<>(2);
        tiles.add(A);
        tiles.add(G);
        assertEquals(tileLetters,player.TileLetters(tiles));

    }

    @Test
    public void testAddPoints(){
        assertEquals(0,player.getScore());
        player.addPoints(3);
        assertEquals(3,player.getScore());
    }


    @Test
    public void testPlayerMove(){

    }



}