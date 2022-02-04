package game;

import game.exceptions.InvalidDirectionException;
import game.exceptions.InvalidIndexException;
import game.exceptions.InvalidInputException;
import game.exceptions.InvalidWordException;
import java.util.Scanner;
import utils.ANSI;
import utils. inputToPosition;

public class Move {

    public static void makeMove(Board board, Player player)
        throws InvalidInputException, InvalidIndexException, InvalidWordException, InvalidDirectionException {

//TODO: handle blank tiles

        String prompt = "\n"  + "> " + player.getName() + " make a move or swap letters     "
            + ANSI.WHITE_UNDERLINED +  "example move: MOVE;H8;RICE;VER || SWAP;ABCD ||SWAP; (swap without letter is considered skipping a move)" + ANSI.RESET;
        System.out.println(prompt);

        String [] split = new Scanner(System.in).next().toUpperCase().split(";");

        if (split.length<2) {
            if (split.length==1 && split[0].equals("STOP")) player.continues=false;
                else{
                System.out.println(new InvalidInputException().getMessage());
            }
        } else if(split[0].equals("SWAP")){
            swapLetters(player,split);
        } else {
            normalMove(board,player,split);
        }
    }

    public static void makeMove(Board board, Player player, int x, int y, char[] letters, String direction)
        throws InvalidInputException, InvalidIndexException, InvalidWordException, InvalidDirectionException {
        String word = letters.toString();

        //TODO: fix so that it counts letters already placed before as word
        if (!board.isValidWord(word)) {
            System.out.println(new InvalidWordException().getMessage());
        }else{
            //placing letters on board
            if (!board.isField(x,y) || !board.isEmptyField(x,y)) {
                System.out.println(new InvalidIndexException().getMessage());
            }
            else{
                if(direction.equals("HOR")){
                    board.placeHorizontally(x,y,letters);
                    addScoreHOR(player,letters,x,y,board);
                } else if(direction.equals("VER")){
                    board.placeVertically(x,y,letters);
                    addScoreVER(player,letters,x,y,board);
                } else{
                    System.out.println(new InvalidDirectionException().getMessage());
                }
            }
            player.removeLetters(player.getRack(),letters);
        }
    }

    public static void addScoreHOR(Player player, char[] lettersArray, int x, int y,Board board){
        int points = Scoring.calculateMoveScoreHorizontally(lettersArray,player.getRack(),x,y,board);
        player.addPoints(points);
    }

    public static void addScoreVER(Player player, char[] lettersArray, int x, int y,Board board){
        int points = Scoring.calculateMoveScoreVertically(lettersArray,player.getRack(),x,y,board);
        player.addPoints(points);
    }

    public static void swapLetters(Player player,String[] input){
        String letters = input[1];
        char[] lettersArray = letters.toCharArray();
        player.removeLetters(player.getRack(),lettersArray);
    }

    public static void normalMove(Board board, Player player, String[]input)
        throws InvalidDirectionException, InvalidInputException, InvalidIndexException, InvalidWordException {

        String positions = input[1];

        int x;
        char charY = positions.charAt(0);
        int y =  inputToPosition.getPositionFromLetter(charY);

        if (positions.length()>2){

            String StringX = positions.substring(1);
            x = inputToPosition.getPositionFromString(StringX);
        }else{
            char charX = positions.charAt(1);
            x=  inputToPosition.getPositionFromNumber(charX);
        }

        x = x- 1;//array indexing
        y = y - 1;//array indexing

        //checking if word exist
        //adding score to players score
        //removing letters from players rack and adding new ones
        String letters = input[2];
        char[] lettersArray = letters.toCharArray();
        if (!board.isValidWord(letters)) {
            System.out.println(new InvalidWordException().getMessage());
        }else{
            //placing letters on board
            String direction = input[3];
            if (!board.isField(x,y) || !board.isEmptyField(x,y)) {
                System.out.println(new InvalidIndexException().getMessage());
            }
            else{
                if(direction.equals("HOR")){
                    board.placeHorizontally(x,y,lettersArray);
                    addScoreHOR(player,lettersArray,x,y,board);
                } else if(direction.equals("VER")){
                    board.placeVertically(x,y,lettersArray);
                    addScoreVER(player,lettersArray,x,y,board);
                } else{
                    System.out.println(new InvalidDirectionException().getMessage());
                }
            }
            player.removeLetters(player.getRack(),lettersArray);
        }
    }

}
