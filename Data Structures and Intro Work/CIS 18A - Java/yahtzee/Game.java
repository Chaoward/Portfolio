/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yahtzee;
import java.util.Scanner;

public class Game {
    private boolean done;
    private Die die;
    
    Game() { die = new Die(); }
    
    
    
    public void start() {
        done = false;
        gameLoop();
    }
    
    public void stop() {
        done = true;
        System.out.println("\n\n\nGoodbye...");
    }
    
    
    
    private void gameLoop() {
        Scanner keyboard = new Scanner(System.in);
        
        while (!done) {
            System.out.print("\n===== Your Move! =====\n" +
                    "1.) Roll 1 die\n" +
                    "2.) Roll 5 dice\n" +
                    "3.) Roll 5 dice 3 times\n" +
                    "4.) Exit\n");
            
            switch (keyboard.nextInt()) {
                case 1: //roll 1 die
                    System.out.println("roll: " + die.roll());
                    break;
                case 2: //roll 5 dice
                    die.rollSet(1, 5);
                    die.printCurrentSet();
                    break;
                case 3: //roll 5 dice 3 times
                    die.rollSet(3, 5);
                    die.printCurrentSet();
                    break;
                case 4: //exit
                    stop();
                    break;
                default:
                    System.out.println("Error, wrong input");
            }
        }
    }
}
