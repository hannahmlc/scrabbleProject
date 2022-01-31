package game.interfaces;

import game.Tile;
import java.util.List;
import zUnused_Scrappe.Letters;

public interface Player {

     void addLetters(List<Tile> bag);

     void removeLetters(List<Tile> rack, char[]Letters);

     List<Tile> getRack();

    void printRack(List<Tile> rack);

    boolean hasLetter(char Letter);

    int sumRack (List<Tile> rack);

    List<String> TileLetters(List<Tile> tiles);

    int getScore();

    void addPoints(int points);

    String getName();



}
