package game;

import game.exceptions.InvalidDirectionException;
import game.exceptions.InvalidIndexException;
import game.exceptions.InvalidInputException;
import game.exceptions.InvalidWordException;
import java.util.List;
import utils.TextIO;

public class Scrabble {

    public static void main(String[] args)
        throws InvalidIndexException, InvalidInputException, InvalidWordException, InvalidDirectionException {
        //System.out.print("Please provide your names separated by space:\n");

        Player player1;
        Player player2;

        if (args.length!=0){
            player1 = new Player(args[0]);
            player2 = new Player(args[1]);
        } else{
            System.out.print("Please provide name of first player:\n");
            String input1 = TextIO.getln();
            player1 = new Player(input1);
            System.out.print("Please provide name of second player:\n");
            String input2 = TextIO.getln();
            player2 = new Player(input2);
        }

        List<Tile> bagOfTiles = TileBag.generateTiles();

        //System.out.println(bagOfTiles.size() + "    " + bagOfTiles.get(100).getLetter());

        Game game = new Game (player1, player2,bagOfTiles);
        game.start();
    }
}
