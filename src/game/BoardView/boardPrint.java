package game.BoardView;

import game.Board;
import game.FieldType;
import utils.ANSI;

public class boardPrint {

    public static final int DIM =15; // board dimension

    public static String formatBoard(Board board){
        String boardString = topFrame() + addLine();

        for (int x = 0; x < DIM; x++) {

            int z = x+1; // outer frame numbering
            if (x>DIM+1){
                z = x-15;
            }
            if (z<10)boardString = boardString + "   " + z +  "   |  ";
                else boardString = boardString + "   " + z +  "  |  ";
            //boardString = boardString + "   " + z +  "  |  ";

            //square tiles generation
            for (int y = 0; y < DIM; y++)
            {
                FieldType field = board.specialFiled(x,y);
                switch (field){
                    case CENTER: // pale red  (same as double_w)
                    case DOUBLE_W: // pale red
                        if(board.getField(x,y)==null){
                            boardString = boardString + (ANSI.BLACK_BOLD + ANSI.PURPLE_BACKGROUND + board.getField(x, y) + ANSI.RESET ) + "  | ";
                        }else {
                            boardString = boardString  +( ANSI.WHITE_BACKGROUND_BRIGHT +ANSI.BLACK_BOLD + "  "+ board.getField(x, y))  + "  "+ANSI.RESET+" | ";
                        }
                        break;
                    case TRIPLE_W: // red
                        if (board.getField(x,y)==null){
                            boardString = boardString + (ANSI.BLACK_BOLD + ANSI.RED_BACKGROUND + board.getField(x, y) + ANSI.RESET ) + "  | ";
                        }else{
                            boardString = boardString  +( ANSI.WHITE_BACKGROUND_BRIGHT +ANSI.BLACK_BOLD + "  "+ board.getField(x, y))  + "  "+ANSI.RESET+" | ";
                        }
                        break;
                    case DOUBLE_L: //pale blue
                        if(board.getField(x,y)==null){
                            boardString = boardString + (ANSI.BLACK_BOLD + ANSI.CYAN_BACKGROUND + board.getField(x, y) + ANSI.RESET ) + "  | ";
                        }else{
                            boardString = boardString  +( ANSI.WHITE_BACKGROUND_BRIGHT +ANSI.BLACK_BOLD + "  "+ board.getField(x, y))  + "  "+ANSI.RESET+" | ";
                        }
                        break;
                    case TRIPLE_L: //dark blue
                        if(board.getField(x,y)==null){
                            boardString = boardString + (ANSI.BLACK_BOLD + ANSI.BLUE_BACKGROUND + board.getField(x, y) + ANSI.RESET ) + "  | ";
                        }else{
                            boardString = boardString  +( ANSI.WHITE_BACKGROUND_BRIGHT +ANSI.BLACK_BOLD + "  "+ board.getField(x, y))  + "  "+ANSI.RESET+" | ";
                        }

                        break;
                    case NORMAL:// default
                        if(board.getField(x,y)==null){
                            boardString = boardString + board.getField(x, y) + "  | ";
                        }else{
                            boardString = boardString  +( ANSI.WHITE_BACKGROUND_BRIGHT +ANSI.BLACK_BOLD + "  "+ board.getField(x, y))  + "  "+ANSI.RESET+" | ";
                        }
                        break;
                }


                //boardString = boardString + board.getField(x, y) + " | "; // default



            }
            boardString = boardString + (z) + "\n"  + addLine();
        }
        boardString = boardString + bottomFrame();
        return boardString;
    }

    //board frame lines
    public static String addLine(){
        String line = "--";
        for(int i = 0; i < (DIM+1);i++) {
            line = line + "--------";
        }
        line = line + " \n";
        return line;
    }

    //top frame
    public static String topFrame() {
        String line = "        |";
        for(int i = 0; i < DIM;i++) {
            line = line + "   "+ (letterArray(i)) + "   |";
        }
        line = line + " \n";
        return line;
    }

    //bottom frame (letters)
    public static String bottomFrame() {
        String line = "       |";
        for(int i = 0; i < DIM;i++) {
            line = line + "   "+ (letterArray(i)) + "   |";
        }
        line = line + " \n";
        return line;
    }


    //outer frame letters
    public static String letterArray(int index) {
        String[] array = {"A","B","C","D","E","F","G","H","I", "J","K","L","M","N","O"};
        return array[index];
    }
}
