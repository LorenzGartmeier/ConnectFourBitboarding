package Bitboards;

public class TestClass {


    public static int arrayToInt(int[][] array){
        int red = 0;
        int yellow = 0;

        for(int line = 0; line < 4; line++){
            for(int column = 0; column < 4; column++){
                if(array[line][column] == 1) red++;
                else if(array[line][column] == -1) yellow++;
                red <<= 1;
                yellow <<= 1;

            }
        }


        return (red << 16) | yellow;
    }

    // length of evalArray: 923521 at least


    public static void main(String[] args){
        System.out.println(Integer.MAX_VALUE);

    }


    public static int eval(int[][] array){
        for(int i = 0; i < 4; i++){
            switch(String.valueOf(array[i][0]) + String.valueOf(array[i][1]) + String.valueOf(array[i][2]) + String.valueOf(array[i][3])){
                case "0000": return 0;
            }
        }
        return 0;
    }




}
