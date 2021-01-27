/*
 * AI class that uses minimax algorithm and alpha beta pruning
 * Change depth var (line 93) to a higher value to
 * increase the difficulty of the game.
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
    
    //Method that allows the AI to find the column where it can make the best
    //move to minimize the score
    public static int findMove(Board board) {
        ArrayList<Integer> moves = new ArrayList<>();
        ArrayList<Integer> trackCol = new ArrayList<>();
        ArrayList<Integer> trackMin = new ArrayList<>();
        
        Disc[][] matrix = board.getGrid();
        Disc aiPlayer = board.getAiPlayer();
        Disc huPlayer = board.getHuPlayer();

        int bestMove = Integer.MAX_VALUE;
        int move = -1;
        int num = -1;
        System.out.print("Computer is thinking...");
        for(int j = 0; j < matrix[0].length; j++) {
            if(checkColumn(matrix, j)) {
                    matrix = dropCoin(matrix, j, aiPlayer);
                    move = minimax(matrix,0,huPlayer, Integer.MIN_VALUE, Integer.MAX_VALUE, huPlayer, aiPlayer, board);
                    //System.out.print(move + " - ");
                    moves.add(move);
                    trackCol.add(j);
                    System.out.print(".");
                    matrix = removeDisc(matrix, j);
                    if(move < bestMove) {
                            bestMove = move;
                            num = j;
                    }
            }
        }
        System.out.println();
        
        //Add to the trackMin ArrayList all the moves
        //with the min score.
        int min = getMin(moves);
        for(int i = 0; i < moves.size(); i++) {
                if(moves.get(i) == min) {
                        trackMin.add(trackCol.get(i));
                }
        }
        //If there are moves with the same min score
        //chooses one randomly
        if(trackMin.size() > 1) {
                Random rd = new Random();
                do {
                        num = trackMin.get(rd.nextInt(trackMin.size()));
                }while(!checkColumn(matrix, num));
        }
        return num;
    }   
    
    //Method that check if a column is free.
    //Return true if yes, no otherwise
    public static boolean checkColumn(Disc[][] matrix, int col) {
        for(int i = 0; i < matrix.length; i++) {
            if(matrix[i][col] == Disc.NONE) {
                return true;
            }
        }
        return false;
    }
    //Method that implements the minimax algorithm alpha-beta pruning
    //Call itself recursively up to a certain depth value.
    //Check the score before the recursive call.
    public static int minimax(Disc[][] matrix, int depth, Disc player, int alpha, int beta, Disc huPlayer, Disc aiPlayer, Board board) {
        int score = checkResult(board);
        if(score == 100) {
                return score - depth;
        }
        if(score == -100) {
                return score + depth;
        }
        if(!isBoardAvailable(matrix)) {
                return 0;
        }
        if(depth > 6) {
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
    
    //Method that removes a certain disc dropped in the table, which
    //was used to predict future moves.
    public static Disc[][] removeDisc(Disc[][] matrix, int col){
        for(int i = 0; i < matrix.length; i++) {
            if(matrix[i][col] != Disc.NONE) {
                matrix[i][col] = Disc.NONE;
                break;
            }
        }
        return matrix;
    }
    
    //Method that return the minimum value of the predicted moves. 
    //It checks the move with the lowest value inside the moves ArrayList and 
    //returns its value.
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
