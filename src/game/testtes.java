package game;
//import WordChecker.java.*;



public class testtes {

        //TODO: delete this:
        public static void main(String[] args) {
            Board board = new Board();
            //System.out.println(BaordPrint.addLine());
            board.placeTile(0,1,Letters.A);
            System.out.println(board.printBoard());
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
