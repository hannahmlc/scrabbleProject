package game;

import game.exceptions.InvalidDirectionException;
import game.exceptions.InvalidIndexException;
import game.exceptions.InvalidInputException;
import game.exceptions.InvalidWordException;
import java.util.Scanner;
import utils.ANSI;
import utils.LetterToPosition;
import utils.TextIO;

public class Move {

    public static void makeMove(Board board, Player player)
        throws InvalidInputException, InvalidIndexException, InvalidWordException, InvalidDirectionException {
        String prompt = "\n"  + "> " + player.getName() + " make a move or swap letters     "
            + ANSI.WHITE_UNDERLINED +  "example move: H8;RICE;VER" + ANSI.RESET;

        System.out.println(prompt);

        String [] split = new Scanner(System.in).next().toUpperCase().split(";");
        if (split.length<3) throw new InvalidInputException();
        else {

            String positions = split[0];
            int x = positions.charAt(0);
            char charY = positions.charAt(1);
            int y = LetterToPosition.getYfromLetter(charY);
            x-=1;//array indexing
            y-=1;//array indexing

            String letters = split[1];
            char[] lettersArray = letters.toCharArray();
            if (board.isValidWord(letters)) throw new InvalidWordException();

            String direction = split[2];

            if (!board.isField(x,y) || !board.isEmptyField(x,y)) {
                throw new InvalidIndexException();
            }
            else{
                if(direction.equals("HOR")){
                    board.placeHorizontally(x,y,lettersArray);
                }else if(direction.equals("VER")){
                    board.placeVertically(x,y,lettersArray);
                }else throw new InvalidDirectionException();
            }

        }
    }

}
