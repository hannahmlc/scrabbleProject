package game;

import game.exceptions.InvalidDirectionException;
import game.exceptions.InvalidIndexException;
import game.exceptions.InvalidInputException;
import game.exceptions.InvalidWordException;
import java.util.ArrayList;
import java.util.List;

public class Game implements game.interfaces.Game {

    public static final int NUMBER_PLAYERS = 2;

    List<Tile> bag = new ArrayList<>(100);// = TileBag.generateTiles();
    private Board board;
    private final Player[] players;
    int currentPlayer;

    public Game(Player p1, Player p2, List<Tile> bag) {
        this.board = new Board();
        this.bag = bag;
       // bag = TileBag.generateTiles();
        this.players = new Player[NUMBER_PLAYERS];
        players[0] = p1;
        players[1] = p2;
        p1.addLetters(bag);
        p2.addLetters(bag);
        //currentPlayer = (int)(Math.random() * 1);
    }

    @Override
    public void start() throws InvalidInputException, InvalidIndexException, InvalidWordException, InvalidDirectionException {
        currentPlayer = 0;
        board.reset();
        play();
    }

    @Override
    public void play()
        throws InvalidInputException, InvalidIndexException, InvalidWordException, InvalidDirectionException {
        currentPlayer = (int)(Math.random() * 1);
        //board.printBoard();
        while (!gameOver()){
            currentPlayer = currentPlayer % 2; // player can only be 1(index 0) or 2 (index 1)
            board.printBoard();
            this.board = players[currentPlayer].playerMove(board);
            players[currentPlayer].addLetters(bag); // add letters after move
            currentPlayer++; // change player
        }
        board.printBoard(); // print updated board
        printResult(players[0],players[1]);
    }

    @Override
    public Board getBoard() {
        return this.board;
    }

    @Override
    public Player[] getPlayers() {
        return this.players;
    }

    /**
     * indicate whatever game is still going
     * return true if game is active, false otherwise
     * @ensures game that is being played is active
     */
    public boolean gameOver() {
        return (noTilesLeft() || board.isFull() || !players[0].continues || !players[1].continues);
    }

    @Override
    public boolean noTilesLeft() {
        return bag.size() == 0;
    }

    @Override
    public void printResult(Player p1, Player p2) {
        if (this.winner(p1,p2)==p1){
            System.out.println("Winner player: " +p1.getName());

        } else if (this.winner(p1,p2)==p2){
            System.out.println("Winner player: " +p2.getName());
        }else {
            System.out.println("Draw. There is no winner!");
        }
    }

    @Override
    public Player winner(Player p1, Player p2){
        int score1 = p1.getScore();
        int score2 = p2.getScore();

        int finalScore1 = playerFinalScore(p1);
        int finalScore2 = playerFinalScore(p2);

        if (p1.getRack().size()==0){
            score1 += playerFinalScore(p2);
        }
        if (p2.getRack().size()==0){
            score2 += playerFinalScore(p1);
        }

        if (finalScore1>finalScore2) { return p1;}
            else if (finalScore1<finalScore2){ return p2 ;}
                else {//In case of a tie, the player with the highest score before adding or deducting unplaced letters wins.
                    if (score1>score2){ return p1; }
                        else if (score1<score2){ return p2; }
                          else return null; //all scores are draw
                }

    }

    @Override
    public int playerFinalScore(Player player){
        return player.getScore() - Scoring.unplacedLettersSum(player);
    }



}
