package game;

import game.Letters;
import game.Tile;
import java.util.ArrayList;
import java.util.List;

public class TileBag {
    List<Tile> bag = new ArrayList<>(100);


      public static List<Tile> generateTiles(){
          List<Tile> bag = new ArrayList<>(100);
          Tile A = new Tile("A",1 );
          Tile B = new Tile("B",3 );
          Tile C = new Tile("C",3 );
          Tile D = new Tile("D",2 );
          Tile E = new Tile("E",1 );
          Tile F = new Tile("F",4 );
          Tile G = new Tile("G",2 );
          Tile H = new Tile("H",4 );
          Tile I = new Tile("I",1 );
          Tile J = new Tile("J",8 );
          Tile K = new Tile("K",5 );
          Tile L = new Tile("L",1 );
          Tile M = new Tile("M",3 );
          Tile N = new Tile("N",1 );
          Tile O = new Tile("O",1 );
          Tile P = new Tile("P",3 );
          Tile Q = new Tile("Q",10 );
          Tile R = new Tile("R",1 );
          Tile S = new Tile("S",1 );
          Tile T = new Tile("T",1 );
          Tile U = new Tile("U",1 );
          Tile V = new Tile("V",4 );
          Tile W = new Tile("W",4 );
          Tile X = new Tile("X",8 );
          Tile Y = new Tile("Y",4 );
          Tile Z = new Tile("Z",10 );
          Tile BLANK = new Tile("BLANK",0 );

          bag.add(A);
          bag.add(B);
          bag.add(C);
          bag.add(D);
          bag.add(E);
          bag.add(F);
          bag.add(G);
          bag.add(H);
          bag.add(I);
          bag.add(J);
          bag.add(K);
          bag.add(L);
          bag.add(M);
          bag.add(N);
          bag.add(O);
          bag.add(P);
          bag.add(Q);
          bag.add(R);
          bag.add(S);
          bag.add(T);
          bag.add(U);
          bag.add(V);
          bag.add(W);
          bag.add(X);
          bag.add(Y);
          bag.add(Z);
          bag.add(BLANK);
          return bag;
      }




      private static class tileQuantity{
      }


}
//    The following tiles are in the game:
//9xA, 2xB, 2xC, 4xD, 12xE, 2xF, 2xG, 2xH, 8xI, 2xJ, 2xK, 4xL, 2xM, 6xN, 8xO, 2xP, 1xQ, 6xR,
//4xS, 6xT, 4xU, 2xV, 2xW, 1xX, 2xY, 1xZ and 2xBlank.

//Each tile can have a different value for the scoring:
//A=1, B=3, C=3, D=2, E-1, F=4, G=2, H=4, I=1, J=8, K=5, L=1, M=3, N=1, O=1, P=3, Q=10,
//R=1, S=1, T=1, U=1. V=4, W=4, X=8, Y=4, Z=10, Blank = 0
