package game;
//import WordChecker.java.*;


import utils.LetterToPosition;

public class testtes {

        //TODO: delete this:
        public static void main(String[] args) {
            Board board = new Board();
            //System.out.println(BaordPrint.addLine());
            board.placeTile(0,1,"A");
            System.out.println(board.printBoard());
            int b = LetterToPosition.getYfromLetter('B');
            System.out.println(b);
        }

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
