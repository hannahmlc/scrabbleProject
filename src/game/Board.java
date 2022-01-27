package game;
import game.BoardView.boardPrint;

public class Board implements game.interfaces.Board {

    public static final int DIM =15; // board dimension
    private Letters[][] tiles;

    private final boardPrint boardFormat = new boardPrint();

    /**
     * creates a new, empty board
     * @ensures all fields are EMPTY
     */
    public Board(){
        this.tiles = new Letters[DIM][DIM];
        reset();
    }

// --Board/Tiles related methods-----------------------------------------------
    /**
     * places the tile on board
     * @param x - horizontal index on board
     * @param y - diagonal index on board
     * @param tile - tile to place
     * @ensures no tile is put on occupied place during game
     */
    @Override
    public void placeTile(int x, int y, Letters tile) {
        if (tiles[x][y]== Letters.EMPTY){
            tiles[x][y] = tile;
        } else{
            System.out.println("Place taken");
        }
    }

    /**
     * set the tile on board
     * @param x    - horizontal index on board
     * @param y    - diagonal index on board
     * @param tile - tile to place
     */
    @Override
    public void setTile(int x, int y, Letters tile) {
        tiles[x][y] = tile;
    }

    //TODO: make sure get field up/down etc arent out of bounds
    @Override
    public Letters getField(int x, int y) {
        return tiles[x][y];
    }

    @Override
    public Letters getFieldUp(int x, int y) {
        return tiles[x+1][y];
    }

    @Override
    public Letters getFieldDown(int x, int y) {
        return tiles[x-1][y];
    }

    @Override
    public Letters getFieldLeft(int x, int y) {
        return tiles[x][y+1];
    }

    @Override
    public Letters getFieldRight(int x, int y) {
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
                setTile(i,j, Letters.EMPTY);
            }
        }
    }

    /**
     * Prints the board and current game situation
     * @return the game situation as String
     */
    @Override
    public String printBoard() {
        return boardPrint.formatBoard(this);
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
        (x== 5 && y==13) || (x== 9 && y==13) ) return FieldType.TRIPLE_L;//tripple letter

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


// --Board/Game related methods-----------------------------------------------



}
