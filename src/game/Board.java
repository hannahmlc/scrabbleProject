package game;
import game.BoardView.boardPrint;
import game.exceptions.InvalidInputException;
import wordChecker.java.FileStreamScrabbleWordChecker;
import wordChecker.java.ScrabbleWordChecker;

public class Board implements game.interfaces.Board {

    public static final int DIM =15; // board dimension
    private String[][] tiles;

    private final boardPrint boardFormat = new boardPrint();

    /**
     * creates a new, empty board
     * @ensures all fields are EMPTY
     */
    public Board(){
        this.tiles = new String[DIM][DIM];
        reset();
    }


    /**
     * places the tile on board
     * @param x - horizontal index on board
     * @param y - diagonal index on board
     * @param tile - tile to place
     * @ensures no tile is put on occupied place during game
     */
    @Override
    public void placeTile(int x, int y, String tile) {
        if (tiles[x][y]== null){
            tiles[x][y] = tile;
        } else{
            System.out.println("Place taken");
        }
    }

    //TODO: make sure get field up/down etc arent out of bounds
    /**
     * @param x - horizontal index on board
     * @param y - diagonal index on board
     * @return field of given index
     */
    @Override
    public String getField(int x, int y) {
        return tiles[x][y];
    }

    /**
     * @param x - horizontal index on board
     * @param y - diagonal index on board
     * @return field up form given index
     */
    @Override
    public String getFieldUp(int x, int y) {
        return tiles[x+1][y];
    }

    /**
     * @param x - horizontal index on board
     * @param y - diagonal index on board
     * @return field below given index
     */
    @Override
    public String getFieldDown(int x, int y) {
        return tiles[x-1][y];
    }

    /**
     * @param x - horizontal index on board
     * @param y - diagonal index on board
     * @return field on the left to given index
     */
    @Override
    public String getFieldLeft(int x, int y) {
        return tiles[x][y+1];
    }

    /**
     * @param x - horizontal index on board
     * @param y - diagonal index on board
     * @return field on the right to given index
     */
    @Override
    public String getFieldRight(int x, int y) {
        return tiles[x][y-1];
    }


    /**
     * Creates a deep copy of board
     * @ensures the result is a new object, so not this object
     * @ensures the values of all fields of the copy match the ones of this Board
     */
    @Override
    public game.interfaces.Board deepCopy() {
        Board deepCopy = new Board();
        for(int i = 0; i<DIM;i++){
            for(int j=0; j<DIM;j++){
                deepCopy.tiles[i][j] = tiles[i][j];
            }
        }
        return deepCopy;
    }

    /**
     * Empties all fields of this board
     * @ensures all fields are EMPTY
     */
    @Override
    public void reset() {
        for (int i=0;i<DIM;i++){
            for (int j=0;j<DIM;j++){
                placeTile(i,j, null);
            }
        }
    }

    /**
     * Prints the board and current game situation
     * @return the game situation as String
     */
    @Override
    public String printBoard() {
        System.out.print(boardFormat.formatBoard(this));
        return boardFormat.formatBoard(this);
    }

    /**
     * @return special places on board
     */
    @Override
    public FieldType specialFiled(int x, int y) {
        if (x==7 && y==7) return FieldType.CENTER; //8x8 normally, -1 because array
        else if ((x==0 && y==0) || (x==0 && y==14) || (x==14 && y==0) ||(x==14 && y==14)) return FieldType.TRIPLE_W; // corners
        else if ((x==0 && y==7) || (x==7 && y==0) || (x==7 && y==14) ||(x==14 && y==7)) return FieldType.TRIPLE_W; // middle corners
        else if((x== 1 && y==1) || (x== 2 && y==2) || (x== 3 && y==3) || (x== 4 && y==4) ||
            (x== 1 && y==13) || (x== 2 && y==12) || (x== 3 && y==11) || (x== 4 && y==10) ||
            (x== 10 && y==4) || (x== 11 && y==3) || (x== 12 && y==2) || (x== 13 && y==1) ||
            (x== 10 && y==10) || (x== 11 && y==11) || (x== 12 && y==12) || (x== 13 && y==13)) return FieldType.DOUBLE_W; //diagonal from middle
         else if ((x== 5 && y==1) || (x== 9 && y==1) ||
        (x== 1 && y==5) || (x== 5 && y==5) || (x== 9 && y==5) || (x== 13 && y==5) ||
        (x== 1 && y==9) || (x== 5 && y==9) || (x== 9 && y==9) || (x== 13 && y==9) ||
        (x== 5 && y==13) || (x== 9 && y==13) ) return FieldType.TRIPLE_L;//triple letter

       else if ( (x== 0 && y==3) || (x== 0 && y==11) ||
        (x== 2 && y==6) || (x== 2 && y==8) ||
        (x== 3 && y==0) || (x== 3 && y==7) || (x== 3 && y==14) ||
        (x== 6 && y==2) || (x== 6 && y==6) || (x== 6 && y==8) || (x== 6 && y==12) ||
        (x== 7 && y==3) || (x== 7 && y==11) ||
        (x== 8 && y==2) || (x== 8 && y==6) || (x== 8 && y==8) || (x== 8 && y==12) ||
        (x== 11 && y==0) || (x== 11 && y==7) || (x== 11 && y==14) ||
        (x== 12 && y==6) || (x== 12 && y==8) ||
        (x== 14 && y==2) || (x== 14 && y==12) ) return FieldType.DOUBLE_L;//double letter
        else return FieldType.NORMAL;
    }

    /**
     * @return true if board is full (all places are taken)
     * @ensures game checks if the board is full
     */
    @Override
    public boolean isFull() {
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                if (getField(i,j) == null) {
                    return false;
                }
            }
        }return true;
    }

    /**
     * @param x - horizontal index on board
     * @param y - diagonal index on board
     * checks whatever index are part of board
     * @ensures chosen field isn't out of bounds
     */
    @Override
    public boolean isField(int x, int y) {
        return x < DIM && y < DIM;
    }

    /**
     * checks whatever place on board is empty
     * @param x - horizontal index on board
     * @param y - diagonal index on board
     * @ensures chosen field is empty
     */
    @Override
    public boolean isEmptyField(int x, int y) {
        return getField(x, y) == null;
    }

    /**
     * @param x - horizontal index on board
     * @param y - diagonal index on board
     * @param letters - letters to be placed on board horizontally
     * @requires place where the word will be put, is empty
     * @ensures word is placed horizontally, letters are placed in correct order
     */
    @Override
    public void placeHorizontally(int x, int y, char[] letters) throws InvalidInputException {
            for(int i=0;i<letters.length;i++){
                String letter = String.valueOf(letters[i]);
                if (getField(x,y)==null && isField(x,y)) {
                    placeTile(x,y,letter); //TODO: make sure if word doesnt fit letters doesnt stay on board / arent placed
                }else{
                        throw new InvalidInputException();//TODO: make sure word isny logner then place from given tile (example, h12 ver, word cant be 10 letters
                }
                    y++;
            }
    }

    /**
     * @param x - horizontal index on board
     * @param y - diagonal index on board
     * @param letters - letters to be placed on board vertically
     * @requires place where the word will be put, is empty
     * @ensures word is placed vertically, letters are placed in correct order
     */
    @Override
    public void placeVertically(int x, int y, char[] letters) throws InvalidInputException {
        for(int i=0;i<letters.length;i++){
            String letter = String.valueOf(letters[i]);
            if (getField(x,y)==null && isField(x,y)) placeTile(x,y,letter); //TODO: make sure if word doesnt fit letters doesnt stay on board / arent placed
            else throw new InvalidInputException(); //TODO: make sure word isny logner then place from given tile (example, h12 ver, word cant be 10 letters
            x++;
        }
    }

    ScrabbleWordChecker checker = new FileStreamScrabbleWordChecker();
    /**
     * Check if created word exist in dictionary
     * @param word - word that needs to be checked
     * @return true if word exist
     * @ensures word exists
     * @requires word isn't null
     */
    @Override
    public boolean isValidWord(String word) {
        ScrabbleWordChecker.WordResponse response = checker.isValidWord(word);
        if(response == null){
            return false;
        } else{
            return true;
        }
    }

    /**
     * @param score - player's current score
     * @param tile  - tile player used
     * @ensures added score to player's score is correct
     */
    @Override
    public void calculateAddScore(int score, Tile tile) {

    }

}
