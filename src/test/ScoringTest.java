package test;

import static org.junit.jupiter.api.Assertions.*;

import game.Scoring;
import game.Tile;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class ScoringTest {

    @Test
    public void calculateMoveScoreTest() {
        Tile A = new Tile("A",1 );
        Tile B = new Tile("B",3 );
        Tile C = new Tile("C",5 );
        List<Tile> tiles = new ArrayList<>();
        tiles.add(A);
        tiles.add(B);
        tiles.add(C);
        char[] Letters = {'A','B','C'};
        int score = Scoring.calculateMoveScore(Letters, tiles);
        assertEquals(score,9);
    }
    
    @Test
    public void testLetterScore() {
        Tile A = new Tile("A",1 );
        Tile B = new Tile("B",3 );
        Tile C = new Tile("C",5 );
        List<Tile> tile = new ArrayList<>();
        tile.add(A);
        tile.add(B);
        tile.add(C);
        int scoreA = Scoring.LetterScore("A",tile);
        int scoreB = Scoring.LetterScore("B",tile);
        int scoreC = Scoring.LetterScore("C",tile);
        int invalidLetter = Scoring.LetterScore("E",tile);
        assertEquals(scoreA,1);
        assertEquals(scoreB,3);
        assertEquals(scoreC,5);
        assertEquals(invalidLetter,99);
    }

}