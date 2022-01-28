package game.interfaces;

import game.Tile;
import java.util.List;

public interface Player {

    public void addLetters(List<Tile> bag);

    public List<Tile> getRack();

    public int getScore();

    public void addPoints(int points);

    public String getName();
}
