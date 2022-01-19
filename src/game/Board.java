package game;

public class Board implements game.interfaces.Board {

    public static final int DIM =15; // board dimension
    private Letters[][] tiles;

    /**
     * creates a new, empty baord
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
     */
    @Override
    public void placeTile(int x, int y, Letters tile) {
        if (tiles[x][y]== Letters.EMPTY){
            tiles[x][y] = tile;
        } else{
            System.out.println("Place taken");
        }
    }

    //TODO: make sure get fild up/down etc arent out of bounds
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
            for (int j=0;i<DIM;j++){
                placeTile(i,j, Letters.EMPTY);
            }
        }
    }

    /**
     * Prints the board and current game situation
     * @return the game situation as String
     */
    @Override
    public String printBoard() {
        return null;
    }


// --Board/Game related methods-----------------------------------------------



}
