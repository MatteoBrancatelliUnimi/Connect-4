/*
 *Main class
 */
package forza4;

import java.io.IOException;
import static forza4.Utility.*;
/**
 *
 * @author Branc
 */
public class Forza4 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException{
        boolean playAgain;
        
        printMenu();
        //till playAgain is true, start a game.
        do{
            Board board = new Board();
            Game.play(board);
            playAgain = askForRestartGame();  
        }while(playAgain);
    }
    
}
