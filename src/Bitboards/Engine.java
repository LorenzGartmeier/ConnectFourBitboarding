package Bitboards;

public class Engine {

    public static final int ones = 1;
    public static final int twos = 3;

    public static final int threes = 8;

    public static int minimax(Position position, int alpha, int beta, int depth) {
        if(depth == 0) return eval(position);

        if(WinChecker.IsWon(position)){
            if(position.redToMove) return Integer.MIN_VALUE;
            else return Integer.MAX_VALUE;
        }
        if(MoveGenerator.isFull(position)) return 0;
        if (position.redToMove) {
            int maxEval = Integer.MIN_VALUE;
            for (int i : MoveGenerator.generateMoves(position)) {
                MoveMaker.makeMove(i, position);
                int eval = minimax(position, alpha, beta, depth - 1);
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(maxEval, alpha);
                MoveMaker.undoMove(i, position);
                if (beta <= alpha) break;
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int i : MoveGenerator.generateMoves(position)) {
                MoveMaker.makeMove(i, position);
                int eval = minimax(position, alpha, beta, depth - 1);
                minEval = Math.min(minEval, eval);
                beta = Math.min(minEval, beta);
                MoveMaker.undoMove(i, position);
                if (beta <= alpha) break;

            }
            return minEval;
        }
    }

    public static int calculate(Position position, int depth){
        int result = -1;
        int[] legalMoves = MoveGenerator.generateMoves(position);
        if (position.redToMove) {
            int maxEval = Integer.MIN_VALUE;
            for (int i : legalMoves) {
                MoveMaker.makeMove(i, position);
                int eval = minimax(position, Integer.MIN_VALUE, Integer.MAX_VALUE, depth);
                if(eval > maxEval){
                    maxEval = eval;
                    result = i;
                }
                MoveMaker.undoMove(i, position);
            }
            if(result == -1) return legalMoves[0];
            else return result;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int i : legalMoves) {
                MoveMaker.makeMove(i, position);
                int eval = minimax(position, Integer.MIN_VALUE, Integer.MAX_VALUE, depth);
                if(eval < minEval){
                    minEval = eval;
                    result = i;
                }
                MoveMaker.undoMove(i, position);
            }
            if(result == -1) return legalMoves[0];
            else return result;
        }
    }

        /*
    public static void main(String[] args) {
        for (int i = 0; i < 241; i++) {
            int reds = (i >> 4) % 2 + (i >> 5) % 2 + (i >> 6) % 2 + (i >> 7);
            int yellows = (i >> 3) % 2 + (i >> 2) % 2 + (i >> 1) % 2 + i % 2;
            if (yellows == 0) {
                System.out.println(reds * reds + ",");
            } else if (i >> 4 == 0) {
                System.out.println(-(yellows * yellows) + ",");
            } else {
                System.out.println("0,");
            }
        }
    }
    */
         

    public static void main(String[] args) {
        for (int i = 0; i < 241; i++) {
            int reds = (i >> 4) % 2 + (i >> 5) % 2 + (i >> 6) % 2 + (i >> 7);
            int yellows = (i >> 3) % 2 + (i >> 2) % 2 + (i >> 1) % 2 + i % 2;
            if (yellows == 0) {
                System.out.println(reds * reds + ",");
            } else if (i >> 4 == 0) {
                System.out.println(-(yellows * yellows) + ",");
            } else {
                System.out.println("0,");
            }
        }
    }

    public static int horizontals(Position position){
        int value = 0;
        for(int line = 0; line < 6; line ++){
            for(int column = 0; column < 4; column++){
                value += values[(int) ((((position.red >> (column*7 + line)) & 15) << 4) | (((position.yellow >> (column*7 + line)) & 15)))];
            }
        }
        return value;
    }


    static int eval (Position position){
        int eval = 0;
        for(long each: winPositions){
            switch ((Long.bitCount(position.red & each) << 3) | Long.bitCount(position.yellow & each)) {
                case 8 -> eval += ones;
                case 16 -> eval += twos;
                case 24 -> eval += threes;
                case 32 -> {
                    return Integer.MAX_VALUE;
                }
                case 1 -> eval -= ones;
                case 2 -> eval -= twos;
                case 3 -> eval -= threes;
                case 4 -> {
                    return Integer.MIN_VALUE;
                }
            }
        }
        return eval;
    }

    public static int verticals(Position position){
        int value = 0;
        for(int line = 0; line < 3; line ++){
            for(int column = 0; column < 7; column++){
                value += values[(int) ((((position.red >> (column*7 + line)) & 15) << 4) | (((position.yellow >> (column*7 + line)) & 15)))];
            }
        }
        return value;
    }


    static int[] values = new int[]{
            0,
            -1,
            -1,
            -4,
            -1,
            -4,
            -4,
            -9,
            -1,
            -4,
            -4,
            -9,
            -4,
            -9,
            -9,
            -16,
            1,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            1,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            4,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            1,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            4,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            4,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            9,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            1,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            4,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            4,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            9,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            4,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            9,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            9,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            16
    };

    public static long[] winPositions = new long[]{
            0b1111L,
            0b11110L,
            0b111100L,
            0b1111000L,
            0b11110000000L,
            0b111100000000L,
            0b1111000000000L,
            0b11110000000000L,
            0b111100000000000000L,
            0b1111000000000000000L,
            0b11110000000000000000L,
            0b111100000000000000000L,
            0b1111000000000000000000000L,
            0b11110000000000000000000000L,
            0b111100000000000000000000000L,
            0b1111000000000000000000000000L,
            0b11110000000000000000000000000000L,
            0b111100000000000000000000000000000L,
            0b1111000000000000000000000000000000L,
            0b11110000000000000000000000000000000L,
            0b111100000000000000000000000000000000000L,
            0b1111000000000000000000000000000000000000L,
            0b11110000000000000000000000000000000000000L,
            0b111100000000000000000000000000000000000000L,
            0b1000000100000010000001L,
            0b10000001000000100000010L,
            0b100000010000001000000100L,
            0b1000000100000010000001000L,
            0b10000001000000100000010000L,
            0b100000010000001000000100000L,
            0b1000000100000010000001000000L,
            0b10000001000000100000010000000L,
            0b100000010000001000000100000000L,
            0b1000000100000010000001000000000L,
            0b10000001000000100000010000000000L,
            0b100000010000001000000100000000000L,
            0b1000000100000010000001000000000000L,
            0b10000001000000100000010000000000000L,
            0b100000010000001000000100000000000000L,
            0b1000000100000010000001000000000000000L,
            0b10000001000000100000010000000000000000L,
            0b100000010000001000000100000000000000000L,
            0b1000000100000010000001000000000000000000L,
            0b10000001000000100000010000000000000000000L,
            0b100000010000001000000100000000000000000000L,
            0b1000000010000000100000001L,
            0b10000000100000001000000010L,
            0b100000001000000010000000100L,
            0b1000000010000000100000001000L,
            0b10000000100000001000000010000000L,
            0b100000001000000010000000100000000L,
            0b1000000010000000100000001000000000L,
            0b10000000100000001000000010000000000L,
            0b100000001000000010000000100000000000000L,
            0b1000000010000000100000001000000000000000L,
            0b10000000100000001000000010000000000000000L,
            0b100000001000000010000000100000000000000000L,
            0b1000001000001000001000L,
            0b10000010000010000010000L,
            0b100000100000100000100000L,
            0b1000001000001000001000000L,
            0b10000010000010000010000000000L,
            0b100000100000100000100000000000L,
            0b1000001000001000001000000000000L,
            0b10000010000010000010000000000000L,
            0b100000100000100000100000000000000000L,
            0b1000001000001000001000000000000000000L,
            0b10000010000010000010000000000000000000L,
            0b100000100000100000100000000000000000000L
    };
}









