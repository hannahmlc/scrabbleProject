package game;

public class Tile {

    private Letters letter;
    private int Points;

    public Tile(Letters letters, int points){
        this.letter = letters;
        this.Points = points;
    }

    //get letter of tile
    public Letters getLetter(){
        return letter;
    }

    //get points of tile
    public int getPoints(){
        return Points;
    }


}
