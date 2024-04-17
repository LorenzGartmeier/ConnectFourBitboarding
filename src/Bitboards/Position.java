package Bitboards;

public class Position {

    long yellow;
    long red;
    boolean redToMove;
    int[] heightInColumns;


    public Position(){
        heightInColumns = new int[7];
        redToMove = true;
    }




    public void print(){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < 42; i++){
            if(i % 7 == 0) result.insert(0, "\n");
            if((red >> i) % 2 == 1) result.insert(0, "X ");
            else if ((yellow >> i) % 2 == 1) result.insert(0, "0 ");
            else result.insert(0, "- ");
        }
        System.out.println(result.toString());
    }

}
