package game.interfaces;

import game.Tile;
import java.util.List;

public interface Player {

     void addLetters(List<Tile> bag);

     List<Tile> getRack();

    void printRack(List<Tile> rack);

    int getScore();

    void addPoints(int points);

    String getName();



}
