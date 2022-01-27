package game;

import java.util.ArrayList;
import java.util.List;
public class Game {

    public static final int NUMBER_PLAYERS = 2;

    List<Tile> bag = TileBag.generateTiles();
    private Board board;
    private Player[] players;
    int current;

    public Game(Player p1, Player p2, List<Tile> bag) {
        this.board = new Board();
        this.bag = bag;
        this.players = new Player[NUMBER_PLAYERS];
        players[0] = p1;
        players[1] = p2;
        p1.addLetters(bag);
        p2.addLetters(bag);

        current = (int)(Math.random() * 1);
    }



}
