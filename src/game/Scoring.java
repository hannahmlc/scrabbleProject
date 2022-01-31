package game;

import java.util.ArrayList;
import java.util.List;

public class Scoring {

    public static int calculateMoveScore(char[] Letters, List<Tile> playerRack){
        int points = 0;
        for (int i=0; i<Letters.length;i++){
            String Letter = String.valueOf(Letters[i]);
            points = points + LetterScore(Letter, playerRack);
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

}
