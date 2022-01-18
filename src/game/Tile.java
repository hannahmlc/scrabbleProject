package game;

public class Tile {

    private Letters Letters;
    private int Points;

    public Tile(Letters letters, int points){
        this.Letters = letters;
        this.Points = points;
    }

    //get letter of tile
    public Letters getLetter(){
        return Letters;
    }

    //get points of tile
    public int getPoints(){
        return Points;
    }


}
