package game;
//import WordChecker.java.*;


import game.exceptions.InvalidInputException;
import game.exceptions.InvalidWordException;
import java.util.Scanner;
import utils. inputToPosition;

public class testtes {

    //TODO: score generaiton
    //TODO: server, clinet
    //TODO: unit testing

    //TODO: delete this:
    public static void main(String[] args) {
        Board board = new Board();
        //System.out.println(BaordPrint.addLine());
        board.placeTile(7, 7, "A");
            /*
            for (int i=0;i<14;i++)
                for (int j=0;j<14;j++)
                    board.placeTile(i,j,"A");*/
        // board.printBoard();
        int b = inputToPosition.getPositionFromLetter('B');
        //System.out.println(b);
        //System.out.println(board.isField(7, 7));

/*
        String[] split = new Scanner(System.in).next().toUpperCase().split(";");
        if (split.length < 3) System.out.println("too short");
        else {
            String positions = split[0];
            char charX = positions.charAt(1);
            int x =  inputToPosition.getPositionFromNumber(charX);
            char charY = positions.charAt(0);
            int y =  inputToPosition.getPositionFromLetter(charY);
            x = x- 1;//array indexing
            y = y - 1;//array indexing

            String letters = split[1];
            char[] lettersArray = letters.toCharArray();

            String direction = split[2];

            System.out.println(positions);
            System.out.println("charY: " + charY);
            System.out.println("y: " + y);
            System.out.println("x: " + x);
            System.out.println("charX: " + charX);


        }
    }
    */
/*
    public boolean isWord(String word){
        FileStreamScrabbleWordChecker checker = new FileStreamScrabbleWordChecker();
        ScrabbleWordChecker.WordResponse response = checker.isValidWord(word);
        if(response == null){
            return false;
        }
        else{
            return true;
        }
    }

    public void Print(String word){
        if(isWord(word)) System.out.println("yes");
        else System.out.println("no");
    }
}*/

    }
}
