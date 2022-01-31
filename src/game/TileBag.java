package game;

import java.util.ArrayList;
import java.util.List;

public class TileBag {
    List<Tile> bag = new ArrayList<>(100);

    TileBag(){
        this.bag = generateTiles();
    }

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
          Tile BLANK = new Tile("-",0 ); //BLANK tiles

          bag.add(A);
          bag.add(A);
          bag.add(A);
          bag.add(A);
          bag.add(A);
          bag.add(A);
          bag.add(A);
          bag.add(A);
          bag.add(A); //9xA

          bag.add(B);
          bag.add(B); //2xB

          bag.add(C);
          bag.add(C);//2xC

          bag.add(D);
          bag.add(D);
          bag.add(D);
          bag.add(D); //4xD

          bag.add(E);
          bag.add(E);
          bag.add(E);
          bag.add(E);
          bag.add(E);
          bag.add(E);
          bag.add(E);
          bag.add(E);
          bag.add(E);
          bag.add(E);
          bag.add(E);
          bag.add(E); //12xE

          bag.add(F);
          bag.add(F); //2xF

          bag.add(G);
          bag.add(G); //2xG

          bag.add(H);
          bag.add(H); //2xH

          bag.add(I);
          bag.add(I);
          bag.add(I);
          bag.add(I);
          bag.add(I);
          bag.add(I);
          bag.add(I);
          bag.add(I); //8xI

          bag.add(J);
          bag.add(J); //2xJ

          bag.add(K);
          bag.add(K); //2xK

          bag.add(L);
          bag.add(L);
          bag.add(L);
          bag.add(L); //4xL

          bag.add(M);
          bag.add(M); //2xM

          bag.add(N);
          bag.add(N);
          bag.add(N);
          bag.add(N);
          bag.add(N);
          bag.add(N); //6xN

          bag.add(O);
          bag.add(O);
          bag.add(O);
          bag.add(O);
          bag.add(O);
          bag.add(O);
          bag.add(O);
          bag.add(O); //8xO

          bag.add(P);
          bag.add(P); //2xP

          bag.add(Q); //1xQ

          bag.add(R);
          bag.add(R);
          bag.add(R);
          bag.add(R);
          bag.add(R);
          bag.add(R); //6xR

          bag.add(S);
          bag.add(S);
          bag.add(S);
          bag.add(S); //4xS

          bag.add(T);
          bag.add(T);
          bag.add(T);
          bag.add(T);
          bag.add(T);
          bag.add(T); //6xT

          bag.add(U);
          bag.add(U);
          bag.add(U);
          bag.add(U);//4xU

          bag.add(V);
          bag.add(V);//2xV

          bag.add(W);
          bag.add(W);//2xW

          bag.add(X);//1xX

          bag.add(Y);
          bag.add(Y);//2xY

          bag.add(Z);//1xZ

          bag.add(BLANK);
          bag.add(BLANK); //2xBLANK
          return bag;
      }


      //private static class tileQuantity{}

}
//    The following tiles are in the game:
//9xA, 2xB, 2xC, 4xD, 12xE, 2xF, 2xG, 2xH, 8xI, 2xJ, 2xK, 4xL, 2xM, 6xN, 8xO, 2xP, 1xQ, 6xR,
//4xS, 6xT, 4xU, 2xV, 2xW, 1xX, 2xY, 1xZ and 2xBlank.

//Each tile can have a different value for the scoring:
//A=1, B=3, C=3, D=2, E-1, F=4, G=2, H=4, I=1, J=8, K=5, L=1, M=3, N=1, O=1, P=3, Q=10,
//R=1, S=1, T=1, U=1. V=4, W=4, X=8, Y=4, Z=10, Blank = 0
