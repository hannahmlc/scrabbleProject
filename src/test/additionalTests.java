package test;
//import WordChecker.java.*;


import game.Board;
import game.Player;
import game.Scoring;
import game.Tile;
import game.TileBag;
import game.exceptions.InvalidInputException;
import game.exceptions.InvalidWordException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utils. inputToPosition;

public class additionalTests {


    public static void main(String[] args) {

        //board view test
        Board board = new Board();
        board.placeTile(8,8,"A");
        board.placeTile(9,11,"D");
        System.out.println(board.printBoard());

        //test for  move positions input
        int i = inputToPosition.getPositionFromString("9");
        System.out.println(i); // expected - 9
        i = inputToPosition.getPositionFromLetter('A');
        System.out.println(i); // expected - 1


        //letter bag test

        List<Tile> bag = TileBag.generateTiles();
        Player p1 = new Player("name");
        System.out.println("original bag szie " + bag.size());// expected 100
        p1.addLetters(bag);
        System.out.println("bag after adding letters " + bag.size()); // expected 100-7

        System.out.println(p1.printRack(p1.getRack()));

    }}
