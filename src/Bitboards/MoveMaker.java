package Bitboards;

public class MoveMaker {

    static long[][] moveNumbers = new long[][]{
            {0b1L, 0b10000000L, 0b100000000000000L, 0b1000000000000000000000L, 0b10000000000000000000000000000L,0b100000000000000000000000000000000000L},
            {0b10L, 0b100000000L, 0b1000000000000000L, 0b10000000000000000000000L, 0b100000000000000000000000000000L, 0b1000000000000000000000000000000000000L},
            {0b100L, 0b1000000000L, 0b10000000000000000L, 0b100000000000000000000000L, 0b1000000000000000000000000000000L, 0b10000000000000000000000000000000000000L},
            {0b1000L, 0b10000000000L, 0b100000000000000000L, 0b1000000000000000000000000L, 0b10000000000000000000000000000000L, 0b100000000000000000000000000000000000000L},
            {0b10000L, 0b100000000000L, 0b1000000000000000000L, 0b10000000000000000000000000L, 0b100000000000000000000000000000000L, 0b1000000000000000000000000000000000000000L},
            {0b100000L, 0b1000000000000L, 0b10000000000000000000L, 0b100000000000000000000000000L, 0b1000000000000000000000000000000000L, 0b10000000000000000000000000000000000000000L},
            {0b1000000L, 0b10000000000000L, 0b100000000000000000000L, 0b1000000000000000000000000000L, 0b10000000000000000000000000000000000L, 0b100000000000000000000000000000000000000000L}
    };


    public static void makeMove(int move, Position position){
        if(position.redToMove){
            position.red += moveNumbers[move][position.heightInColumns[move]];
        } else {
            position.yellow += moveNumbers[move][position.heightInColumns[move]];
        }

        position.heightInColumns[move]++;
        //System.out.println("In Spalte " + move + " sind " + position.heightInColumns[move] + " Steine");
        //System.out.println(Long.toBinaryString(position.red | position.yellow));
        position.redToMove = !position.redToMove;
    }




    public static void undoMove(int move, Position position){
        position.heightInColumns[move]--;
        if(position.redToMove){
            position.yellow -= moveNumbers[move][position.heightInColumns[move]];
        } else {
            position.red -= moveNumbers[move][position.heightInColumns[move]];
        }
        //System.out.println("In Spalte " + move + " sind " + position.heightInColumns[move] + " Steine");
        //System.out.println(Long.toBinaryString(position.red | position.yellow));


        position.redToMove = !position.redToMove;
    }



    public static void main(String[] args){


        for(int move = 0; move < 7; move++){
            System.out.print("{");
            for(int height = 0; height < 6; height++){
                if(height == 0) System.out.print((long)Math.pow(2,height*7+move));
                else  System.out.print("," + (long)Math.pow(2,height*7+move));


            }
            System.out.println(8 >> 1);
        }
    }

}
