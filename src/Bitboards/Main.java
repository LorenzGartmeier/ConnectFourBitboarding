package Bitboards;

import java.util.Scanner;

public class Main {






    public static void main(String[] args){
        Position position = new Position();
        position.print();
        boolean firstPlayer = false;
        Scanner scanner = new Scanner(System.in);
        while (!WinChecker.IsWon(position)){
            if(!firstPlayer){
                MoveMaker.makeMove(scanner.nextInt(), position);
            } else {
                double time = System.nanoTime();
                MoveMaker.makeMove(Engine.calculate(position, 13),position);
                System.out.println((System.nanoTime()-time)/1000000);
            }
            firstPlayer = !firstPlayer;
            position.print();
        }


    }

    public static void main2(String[] args) {
        for(int i = 0; i < 15; i++){
            Position position = new Position();
            MoveMaker.makeMove(3,position);
            double time = System.nanoTime();

            int column = Engine.calculate(position, i);
            MoveMaker.makeMove(column,position);

            System.out.println((System.nanoTime()-time)/1000000);
        }
    }



}
