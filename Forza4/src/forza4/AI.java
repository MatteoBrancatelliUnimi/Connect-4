/*
 * AI class that uses minimax algorithm w/ alpha beta pruning
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forza4;

import java.util.ArrayList;

import static forza4.Game.*;
import java.util.Random;
/**
 *
 * @author Branc
 */
public class AI {
    
    public static int findMove(Board board) {
        ArrayList<Integer> moves = new ArrayList<Integer>();
        ArrayList<Integer> trackMax = new ArrayList<Integer>();
        ArrayList<Integer> trackMin = new ArrayList<Integer>();
        
        Disc[][] matrix = board.getGrid();
        Disc aiPlayer = board.getAiPlayer();
        Disc huPlayer = board.getHuPlayer();
        //System.out.println(huPlayer + " " + aiPlayer);
        //int column = board.getColumn();

        int bestMove = Integer.MAX_VALUE;
        int move = -1;
        int num = -1;
        System.out.print("Computer is thinking...");
        for(int j = 0; j < matrix[0].length; j++) {
            if(checkColumn(matrix, j)) {
                    matrix = dropCoin(matrix, j, aiPlayer);
                    move = minimax(matrix,0,huPlayer, Integer.MIN_VALUE, Integer.MAX_VALUE, huPlayer, aiPlayer, board);
                    moves.add(move);
                    trackMax.add(j);
                    System.out.print(".");
                    matrix = removeDisc(matrix, j);
                    if(move < bestMove) {
                            bestMove = move;
                            num = j;
                    }
            }
        }
        System.out.println();

        int min = getMin(moves);
        for(int i = 0; i < moves.size(); i++) {
                if(moves.get(i) == min) {
                        trackMin.add(trackMax.get(i));
                }
        }
        if(trackMin.size() > 1) {
                Random rd = new Random();
                do {
                        num = trackMin.get(rd.nextInt(trackMin.size()));
                }while(!checkColumn(matrix, num));
        }
        return num;
    }   
    public static boolean checkColumn(Disc[][] matrix, int col) {
        for(int i = 0; i < matrix.length; i++) {
            if(matrix[i][col] == Disc.NONE) {
                return true;
            }
        }
        return false;
    }
    public static int minimax(Disc[][] matrix, int depth, Disc player, int alpha, int beta, Disc huPlayer, Disc aiPlayer, Board board) {
        int score = checkResult(board);
        if(score == 10000) {
                return score - depth;
        }
        if(score == -10000) {
                return score + depth;
        }
        if(!isBoardAvailable(matrix)) {
                return 0;
        }
        if(depth > 5) {
                return score;
        }

        if(player == huPlayer) {
            int bestMax = Integer.MIN_VALUE;
            for(int i = 0; i < matrix[0].length; i++) {
                if(checkColumn(matrix, i)) {
                    matrix = dropCoin(matrix, i, huPlayer);
                    bestMax = Math.max(minimax(matrix,depth+1,aiPlayer,alpha, beta, huPlayer, aiPlayer, board), bestMax);
                    matrix = removeDisc(matrix, i);
                    alpha = Math.max(alpha, bestMax);
                    if(alpha >= beta) {
                            return bestMax;
                    }
                }
            }
            return bestMax;
        }else {
            int bestMin = Integer.MAX_VALUE;
            for(int i = 0; i < matrix[0].length; i++) {
                if(checkColumn(matrix, i)) {
                    matrix = dropCoin(matrix, i, aiPlayer);
                    bestMin = Math.min(minimax(matrix, depth+1,huPlayer,alpha, beta, huPlayer, aiPlayer, board), bestMin);
                    matrix = removeDisc(matrix, i);
                    alpha = Math.min(alpha, bestMin);
                    if(alpha >= beta) {
                            return bestMin;
                    }
                }
            }
            return bestMin;			
        }

    }
    public static Disc[][] removeDisc(Disc[][] matrix, int col){
        for(int i = 0; i < matrix.length; i++) {
            if(matrix[i][col] != Disc.NONE) {
                matrix[i][col] = Disc.NONE;
                break;
            }
        }
        return matrix;
    }
    public static int getMin(ArrayList<Integer> moves) {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < moves.size(); i++) {
                if(moves.get(i) < min) {
                        min = moves.get(i);
                }
        }
        return min;
    }
}
