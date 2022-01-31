package game;

import game.exceptions.InvalidDirectionException;
import game.exceptions.InvalidIndexException;
import game.exceptions.InvalidInputException;
import game.exceptions.InvalidWordException;
import java.util.ArrayList;
import java.util.List;


public class Player implements game.interfaces.Player {

   private final String username;
   public List<Tile> rack  = new ArrayList<>(7);
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
    public void removeLetters(List<Tile> rack, char[] Letters) {
        List<String> tileLetters = TileLetters(rack);
        for (int i=0;i< Letters.length;i++){
            String letter = String.valueOf(Letters[i]);
            for (int j=0;j<rack.size();j++){
                Tile tile = rack.get(j);
                String rackLetter = tile.getLetter();
                if (rackLetter.equals(letter)) {
                    this.rack.remove(j);
                }
            }
        }
    }

    @Override
    public List<Tile> getRack() {
        return this.rack;
    }

    @Override
    public void printRack(List<Tile> rack) {
        String rackString = "";
        for (Tile tile : rack) {
            rackString += "[" + tile.getLetter() + "] ";
        }
        System.out.println(rackString);
    }

    @Override
    public boolean hasLetter(char Letter) {
        List<String> tileLetters = TileLetters(this.getRack());
       return tileLetters.contains(Character.toString(Letter));
    }

    @Override
    public List<String> TileLetters(List<Tile> tiles) {
        List<String> tileLetters = new ArrayList<>(7);
        for(int i=0;i<7;i++){
            tileLetters.add(tiles.get(0).getLetter());
        }
        return tileLetters;
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

    public Board playerMove(Board board)
        throws InvalidInputException, InvalidIndexException, InvalidWordException, InvalidDirectionException {
        System.out.println("Your Letters:");
        printRack(this.getRack());
        Move.makeMove(board, this);
        return board;
    }


}
