package game;

import game.exceptions.InvalidDirectionException;
import game.exceptions.InvalidIndexException;
import game.exceptions.InvalidInputException;
import game.exceptions.InvalidWordException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game implements game.interfaces.Game {

    public static final int NUMBER_PLAYERS = 2;

    List<Tile> bag;// = TileBag.generateTiles();
    private Board board;
    private final Player[] players;
    int currentPlayer;

    public Game(Player p1, Player p2, List<Tile> bag) {
        this.board = new Board();
        this.bag = bag;
        this.players = new Player[NUMBER_PLAYERS];
        players[0] = p1;
        players[1] = p2;
        p1.addLetters(bag);
        p2.addLetters(bag);
        //currentPlayer = (int)(Math.random() * 1);
    }

    public void start()
        throws InvalidInputException, InvalidIndexException, InvalidWordException, InvalidDirectionException {
        currentPlayer = 0;
        board.reset();
        play();
    }

    public void play()
        throws InvalidInputException, InvalidIndexException, InvalidWordException, InvalidDirectionException {
        currentPlayer = (int)(Math.random() * 1);
        while (!gameOver()){
            currentPlayer = currentPlayer % 2; // player can only be 1(index 0) or 2 (index 1)
            board.printBoard();
            this.board = players[currentPlayer].playerMove(board);
            currentPlayer++; // change player
        }
        board.printBoard(); // print updated board
        //this.printResult(p1, p2);
    }

    @Override
    public game.interfaces.Board getBoard() {
        return null;
    }

    @Override
    public String getWinner() {
        return null;
    }

    @Override
    public String getPlayers() {
        return null;
    }

    /**
     * indicate whatever game is still going
     * return true if game is active, false otherwise
     * @ensures game that is being played is active
     */
    public boolean gameOver() {
        return (noTilesLeft() || board.isFull());
    }

    public boolean noTilesLeft() {
        return bag.size() == 0;
    }

    @Override
    public void printResult(Player p1, Player p2) {
        if (this.winner(p1,p2)==p1){
            System.out.println();

        } else if (this.winner(p1,p2)==p2){
            System.out.println();
        }else {
            System.out.println("Draw. There is no winner!");
        }
    }


    public Player winner(Player p1, Player p2){
        int score1 = p1.getScore();
        int score2 = p2.getScore();
        if (score1>score2) return p1;
            else if (score1<score2) return p2;
                else return null;
    }


}
