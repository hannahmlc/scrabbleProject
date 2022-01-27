package game;

import java.util.List;

public class Player implements game.interfaces.Player {

   private final String username;
   public List<Tile> rack;
   public int score;

    public Player(String username) {
        this.username = username;
    }

    @Override
    public void addLetters(List<Tile> bag) {
        while (rack.size()<7 && bag.size()!=0){
            int index = (int)(Math.random() * bag.size());
            Tile letter =  bag.get(index);
            rack.add(letter);
            bag.remove(index);
        }
    }

    @Override
    public List<Tile> getRack() {
        return this.rack;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public void addPoints(int points) {
        this.score += points;
    }

    @Override
    public String getName() {
        return this.username;
    }
}