/*
 * Board class is the representation of the board of the game
 */
package forza4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Branc
 */
public class Board {
    
    //Board attributes
    private static final BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    private static final int width = 7;
    private static final int height = 6;
    private static int column = 0;
    private static Disc[][] board = new Disc[height][width];
    private static Disc huPlayer;
    private static Disc aiPlayer;
    
    //Board constructor
    public Board(){
        this.column = 0;
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                this.board[i][j] = Disc.NONE;
            }
        }
    }
    
    //Getter and Setter methods
    public int getColumn(){
        return this.column;
    }
    public Disc[][] getGrid(){
        return this.board;
    }
    public Disc getHuPlayer(){
        return this.huPlayer;
    }
    public void setHuPlayer(Disc huPlayer){
        this.huPlayer = huPlayer;
    }
    public Disc getAiPlayer(){
        return this.aiPlayer;
    }
    public void setAiPlayer(Disc aiPlayer){
        this.aiPlayer = aiPlayer;
    }
    
    //method that prints the board. 
    //Prints the cell of the matrix
    public static void printBoard(Board board){
        Disc[][] matrix = board.getGrid();
        Disc huPlayer = board.getHuPlayer();
        Disc aiPlayer = board.getAiPlayer();
        System.out.println("|  1  |  2  |  3  |  4  |  5  |  6  |  7  |");
        System.out.println("-------------------------------------------");
        for(int i = 0; i < matrix.length; i++) {
            System.out.print("| ");
            for(int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] == Disc.NONE ) {
                    System.out.print(Disc.NONE + " | ");
                }else if(matrix[i][j] == huPlayer) {
                    System.out.print(huPlayer +" | ");    
                }else if(matrix[i][j] == aiPlayer) {
                    System.out.print(aiPlayer + " | ");    
                }
            }
            System.out.println();
    }      
    }
}
