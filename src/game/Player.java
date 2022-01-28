package game;

import game.exceptions.InvalidDirectionException;
import game.exceptions.InvalidIndexException;
import game.exceptions.InvalidInputException;
import game.exceptions.InvalidWordException;
import java.util.List;
import java.util.Scanner;


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

    public Board playerMove(Board board)
        throws InvalidInputException, InvalidIndexException, InvalidWordException, InvalidDirectionException {
        Move.makeMove(board, this);
        return board;
    }
}
