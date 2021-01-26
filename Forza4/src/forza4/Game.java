/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forza4;

import java.io.IOException;

/**
 *
 * @author Branc
 */
public class Game {

        public static void play(Board board) throws IOException{
        Disc[][] matrix = board.getGrid();
        int column = board.getColumn();
        Utility.askForPlayer(board);
        Disc huPlayer = board.getHuPlayer();
        Disc aiPlayer = board.getAiPlayer();
        Board.printBoard(board);
        System.out.println();
        while(checkResult(board) != 10000 && checkResult(board) != -10000 && isBoardAvailable(matrix)){
            column = playerMove(matrix);
            matrix = dropCoin(matrix, column, huPlayer);
            Board.printBoard(board);
            System.out.println(); 
            if(checkResult(board) == 10000 || checkResult(board) == -10000 && isBoardAvailable(matrix)){
                break;
            }
            column = AI.findMove(board);
            matrix = dropCoin(matrix, column, aiPlayer);
            Board.printBoard(board);
            System.out.println();
        }
        
        if(checkResult(board) == 10000){
            Utility.displayEndGameMessage(huPlayer,10000);
        }else if(checkResult(board) == -10000){
            Utility.displayEndGameMessage(aiPlayer, -10000);
        }else{
            Utility.displayEndGameMessage(Disc.NONE, 0);
        }
    }
    public static int checkResult(Board board) {
        Disc[][] matrix = board.getGrid();
        Disc huPlayer = board.getHuPlayer();
        Disc aiPlayer = board.getAiPlayer();
        int score = 0;
        //vertical check
        for(int i = 0; i < matrix.length - 3; i++) {
                for(int j = 0; j < matrix[i].length; j++) {
                        //check score for human player
                        if(matrix[i][j] == matrix[i+1][j] && matrix[i][j] == matrix[i+2][j] && matrix[i][j] == matrix[i+3][j] &&
                           matrix[i][j] == huPlayer){
                                return 10000;

                        }else if(matrix[i][j] == matrix[i+1][j] && matrix[i][j] == matrix[i+2][j] && matrix[i][j] == huPlayer){
                                score += 100;

                        }else if(matrix[i][j] == matrix[i+1][j] && matrix[i][j] == huPlayer){
                                score += 50;
                        }
                        //check score for AI player
                        if(matrix[i][j] == matrix[i+1][j] && matrix[i][j] == matrix[i+2][j] && matrix[i][j] == matrix[i+3][j] &&
                           matrix[i][j] == aiPlayer){
                                return -10000;

                    }else if(matrix[i][j] == matrix[i+1][j] && matrix[i][j] == matrix[i+2][j] && matrix[i][j] == aiPlayer){
                                score -= 100;

                        }else if(matrix[i][j] == matrix[i+1][j] && matrix[i][j] == aiPlayer){
                                score -= 50;

                        }
                }
        }
        //horizontal check
        for(int i = 0; i < matrix.length; i++) {
                for(int j = 0; j < matrix[i].length-3; j++) {
                        //check score for human player
                        if(matrix[i][j] == matrix[i][j+1] && matrix[i][j] == matrix[i][j+2] && matrix[i][j] == matrix[i][j+3] &&
                           matrix[i][j] == huPlayer){
                                return 10000;

                        }else if(matrix[i][j] == matrix[i][j+1] && matrix[i][j] == matrix[i][j+2] && matrix[i][j] == huPlayer){
                                score += 100;

                        }else if(matrix[i][j] == matrix[i][j+2] && matrix[i][j] == huPlayer){
                                score += 50;
                        }
                        //check score for AI player
                        if(matrix[i][j] == matrix[i][j+1] && matrix[i][j] == matrix[i][j+2] && matrix[i][j] == matrix[i][j+3] &&
                           matrix[i][j] == aiPlayer){
                                return -10000;

                    }else if(matrix[i][j] == matrix[i][j+1] && matrix[i][j] == matrix[i][j+2] && matrix[i][j] == aiPlayer){
                                score -= 100;

                        }else if(matrix[i][j] == matrix[i][j+1] && matrix[i][j] == aiPlayer){
                                score -= 50;

                        }
                }
        }
        //diagonal check
        for(int i = 0; i < matrix.length-3; i++) {
                for(int j = 0; j < matrix[i].length-3; j++) {
                        //check score for human player
                        if(matrix[i][j] == matrix[i+1][j+1] && matrix[i][j] == matrix[i+2][j+2] && matrix[i][j] == matrix[i+3][j+3] &&
                           matrix[i][j] == huPlayer){
                                return 10000;

                        }else if(matrix[i][j] == matrix[i+1][j+1] && matrix[i][j] == matrix[i+2][j+2] && matrix[i][j] == huPlayer){
                                score += 100;

                        }else if(matrix[i][j] == matrix[i+1][j+1] && matrix[i][j] == huPlayer){
                                score += 50;
                        }
                        //check score for AI player
                        if(matrix[i][j] == matrix[i+1][j+1] && matrix[i][j] == matrix[i+2][j+2] && matrix[i][j] == matrix[i+3][j+3] &&
                           matrix[i][j] == aiPlayer){
                                return -10000;

                    }else if(matrix[i][j] == matrix[i+1][j+1] && matrix[i][j] == matrix[i+2][j+2] && matrix[i][j] == aiPlayer){
                                score -= 100;

                        }else if(matrix[i][j] == matrix[i+1][j+1] && matrix[i][j] == aiPlayer){
                                score -= 50;

                        }
                }
        }
        //antidiagonal check
        for(int i = 0; i < matrix.length-3; i++) {
                for(int j = 3; j < matrix[i].length; j++) {
                        //check score for human player
                        if(matrix[i][j] == matrix[i+1][j-1] && matrix[i][j] == matrix[i+2][j-2] && matrix[i][j] == matrix[i+3][j-3] &&
                           matrix[i][j] == huPlayer){
                                return 10000;

                        }else if(matrix[i][j] == matrix[i+1][j-1] && matrix[i][j] == matrix[i+2][j-2] && matrix[i][j] == huPlayer){
                                score += 100;

                        }else if(matrix[i][j] == matrix[i+1][j-1] && matrix[i][j] == huPlayer){
                                score += 50;
                        }
                        //check score for AI player
                        if(matrix[i][j] == matrix[i+1][j-1] && matrix[i][j] == matrix[i+2][j-2] && matrix[i][j] == matrix[i+3][j-3] &&
                           matrix[i][j] == aiPlayer){
                                return -10000;

                    }else if(matrix[i][j] == matrix[i+1][j-1] && matrix[i][j] == matrix[i+2][j-2] && matrix[i][j] == aiPlayer){
                                score -= 100;

                        }else if(matrix[i][j] == matrix[i+1][j-1] && matrix[i][j] == aiPlayer){
                                score -= 50;

                        }
                }
        }

        return score;
    }
    public static boolean isBoardAvailable(Disc[][] matrix) {
        for(int i = 0; i<matrix.length; i++) {
                for(int j=0; j<matrix[i].length; j++) {
                        if(matrix[i][j] == Disc.NONE) {
                                return true;
                        }
                }
        }
        return false;
    }  
    public static int playerMove(Disc[][] matrix) throws IOException {
        int value = 0;
        boolean check = false;
        do{
            value = Utility.askForInteger("Enter a column number[1-7]:",1,7);
            check = isColumnFree(matrix, value-1);
        }while(!check);
        
        return value-1;
    }
    public static boolean isColumnFree(Disc[][] matrix, int col) {
        for(int i = 0; i < matrix.length; i++) {
            if(matrix[i][col] == Disc.NONE) {
                return true;
            }else{
                System.err.println("*** Column "+(col+1)+" is full! Try another column. ***");
                break;
            }
        }
        return false;
    }
    public static Disc[][] dropCoin(Disc[][] matrix, int col, Disc disc){
        for(int i = matrix.length-1; i >= 0; i--) {
            if(matrix[i][col] == Disc.NONE) {
                    matrix[i][col] = disc;
                    break;
            }else{
                //System.out.println("error");
            }
        }
        return matrix;
    }
}
