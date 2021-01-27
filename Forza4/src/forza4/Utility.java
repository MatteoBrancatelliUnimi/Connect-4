/*
 * Class used to print menus and request user input
 * Performs checks on user input and catches exceptions if necessary.
 */
package forza4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Branc
 */
public class Utility {
    private static final BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
    
    //Method that asks the user whether he wants to play with yellow or red.
    //Sets the value of the huPlayer and aiPlayer properties of the board object.
    public static void askForPlayer(Board board) throws IOException{
        StringBuilder menu = new StringBuilder();
        menu.append(" Choose your player color: \n");
        menu.append(" [0] Red    "+"\33[31mO\33[0m\n");
        menu.append(" [1] Yellow "+"\33[33mO\33[0m\n");
        System.out.println(menu.toString());
        int value = askForInteger("",0,1);
        if(value == 0){
            //Red disc is choosen
            board.setHuPlayer(Disc.RED);
            board.setAiPlayer(Disc.YELLOW);
        }else{
            //Yellow disc is choosen
            board.setAiPlayer(Disc.RED);
            board.setHuPlayer(Disc.YELLOW);
        }
    }
    
    //Method that asks the user for an integer value.
    //Try-catch block for error handling.
    public static int askForInteger(String msg, int min, int max) throws IOException {
        int value = 0;
        boolean checkValid = false;
        System.out.println(msg);
        do {
            try {
                value = Integer.parseInt(b.readLine());
                if(value < min || value > max) {
                    System.err.println("*** ERROR: Your input must be between "+min+" and "+max+"! ***");
                }else {
                    checkValid = true;
                }
            }catch(NumberFormatException e) {
                System.err.println("*** ERROR: Your input must be a number! ***");
            }
        }while(!checkValid);

        return value;
    }   
    
    //method that, based on the parameters passed, prints a message with the final status of the game.
    public static void displayEndGameMessage(Disc player, int score){
        switch(score){
            case 100:
                System.out.println("Player "+player+" won the game! Congratulations.");
                break;
            case -100:
                System.out.println("AI ["+player+"] won the game!");
                break;
            case 0:
                System.out.println("It's a draw!");
        }
    }
    
    //Method that asks the user for a char value.
    //Try-catch block for error handling.
    public static char askForChar(char values[]) throws IOException{
        char answer = 0;
        boolean checkValid = false;
        do{
            try{
                answer = b.readLine().toUpperCase().charAt(0);
                for(char i : values){
                    if(answer == i){
                        checkValid = true;
                    }
                }
                if(!checkValid){
                    System.err.println("*** ERROR: Your answer is not valid! ***");
                }
            }catch(IOException e){
                System.err.println("*** ERROR: Your answer is not a char! ***");
            }
        }while(!checkValid);
        return answer;
    }
    
    //Method that asks the user if they want to play another game.
    //Returns true if yes, no otherwise.
    public static boolean askForRestartGame() throws IOException{
        System.out.println(" Do you want to play again?[Y/N] ");
        char answer = askForChar(new char[]{'Y','N'});
        if(answer == 'Y'){
            return true;
        }else{
            return false;
        }
    }
    
    //Metodo that prints a banner and a short description of the game
    public static void printMenu(){
        StringBuilder menu = new StringBuilder();
        menu.append(" _______   ______   .______       ________       ___       _  _    \n" +
                    "|   ____| /  __  \\  |   _  \\     |       /      /   \\     | || |   \n" +
                    "|  |__   |  |  |  | |  |_)  |    `---/  /      /  ^  \\    | || |_  \n" +
                    "|   __|  |  |  |  | |      /        /  /      /  /_\\  \\   |__   _| \n" +
                    "|  |     |  `--'  | |  |\\  \\----.  /  /----. /  _____  \\     | |   \n" +
                    "|__|      \\______/  | _| `._____| /________|/__/     \\__\\    |_| ");
        menu.append("\n");
        menu.append("Place 4 discs in a row in any direction to win the game.\n");
        menu.append("You will play against a computer.\n");
        menu.append("GOOD LUCK!");
       System.out.println(menu.toString());
    }
}
