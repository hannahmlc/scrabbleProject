package game;

import java.util.ArrayList;
import java.util.List;
import utils.ANSI;

public class Scoring {

    /*
    If a word is formed that covers two premium word squares, the score is
    doubled and then re-doubled (4x), or tripled and then re-tripled (9x)
     */
    public static int calculateMoveScoreHorizontally(char[] Letters, List<Tile> playerRack, int x, int y, Board board){
        int points = 0;
        boolean doubleW = false;
        boolean tripleW = false;
        FieldType field;
        for (int i=0; i<Letters.length;i++){
            field = board.fieldType(x,y);
            String Letter = String.valueOf(Letters[i]);
            switch (field){
                case CENTER: // pale red  (same as double_w)
                case DOUBLE_W: // pale red
                    doubleW = true;
                case TRIPLE_W: // red
                    tripleW = true;
                case DOUBLE_L: //pale blue
                    points = points + (LetterScore(Letter, playerRack)*2);
                case TRIPLE_L: //dark blue
                    points = points + (LetterScore(Letter, playerRack)*3);
                    break;
                case NORMAL:// default
                    points = points + LetterScore(Letter, playerRack);
                    break;
            }
            y++; // move to next place on board where letter is placed
        }
        if (doubleW){
            points = points*2;
        }
        if (tripleW){
            points = points*3;
        }
        if(Letters.length==7){
            bingo(points);
        }
        return points;
    }

    public static int calculateMoveScoreVertically(char[] Letters, List<Tile> playerRack, int x, int y, Board board){
        int points = 0;
        boolean doubleW = false;
        boolean tripleW = false;
        FieldType field;
        for (int i=0; i<Letters.length;i++){
            field = board.fieldType(x,y);
            String Letter = String.valueOf(Letters[i]);
            switch (field){
                case CENTER: // pale red  (same as double_w)
                case DOUBLE_W: // pale red
                    doubleW = true;
                case TRIPLE_W: // red
                    tripleW = true;
                case DOUBLE_L: //pale blue
                    points = points + (LetterScore(Letter, playerRack)*2);
                case TRIPLE_L: //dark blue
                    points = points + (LetterScore(Letter, playerRack)*3);
                    break;
                case NORMAL:// default
                    points = points + LetterScore(Letter, playerRack);
                    break;
            }
            x++; // move to next place on board where letter is placed
        }
        if (doubleW){
            points = points*2;
        }
        if (tripleW){
            points = points*3;
        }
        if(Letters.length==7){
            bingo(points);
        }
        return points;
    }

    public static int LetterScore(String Letter, List<Tile> tiles){
        int score = 99; // none of the letter contains score of 99, so if invalid letter is given, 99 is returned
        for (int i=0; i<tiles.size();i++){
            if(Letter.equals(tiles.get(i).getLetter())){
                score = tiles.get(i).getPoints();
            }
        }
        return score;
    }

    public static void bingo(int score ){
        score +=50;
    }

    public static int unplacedLettersSum(Player player){
        int sum = 0;
        List<Tile> tiles = player.getRack();
        if (tiles.size()>0){
            for (Tile tile : tiles) {
                sum = tile.getPoints();
            }
        }
        return sum;
    }
}
/*

 */