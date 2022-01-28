package game;

public class Tile {

    private String letter;
    private int Points;

    public Tile(String letter, int points){
        this.letter = letter;
        this.Points = points;
    }

    //get letter of tile
    public String getLetter(){
        return letter;
    }

    //get points of tile
    public int getPoints(){
        return Points;
    }


}
