package game;
//import WordChecker.java.*;


import utils.LetterToPosition;

public class testtes {

    //TODO: score generaiton
    //TODO: server, clinet
    //TODO: unit testing

        //TODO: delete this:
        public static void main(String[] args) {
            Board board = new Board();
            //System.out.println(BaordPrint.addLine());
            board.placeTile(7,7,"A");
            /*
            for (int i=0;i<14;i++)
                for (int j=0;j<14;j++)
                    board.placeTile(i,j,"A");*/
            board.printBoard();
            int b = LetterToPosition.getYfromLetter('B');
            //System.out.println(b);
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
