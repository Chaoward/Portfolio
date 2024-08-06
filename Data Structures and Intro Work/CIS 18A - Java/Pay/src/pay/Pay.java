package pay;

import java.util.Scanner;
import java.util.Random; //imported for the function FriendlyError()

/**
 * program that ask the user for hours worked and rate of pay, then calculates and display total pay
 */
public class Pay {

    /**
     * NOTE: There are extra features programed in making look a little different.
     * feel free to look through   *fin was here*  |(• _•)| 
     * NOTE+: yes there are stuff we didn't discuss in class
     * DISCLAMER: program responses do not intend to target or attack anyone, it's there for flavor and insult to injury.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double hours;
        double rate;
        
        
        //Ask for hour
        do {
            System.out.println("Enter the amount of hours worked(zero or higher): ");
            hours = input.nextDouble();
            if (hours < 0) { FriendlyError(); }
        } while (hours < 0);
        
        //Ask for pay
        do {
            System.out.println("Enter your pay rate earned(zero or higher): ");
            rate = input.nextDouble();
            if (rate < 0) { FriendlyError(); }
        } while (rate < 0);
        
        //calculates total pay plus any overtime pay
        double pay = (rate * (hours > 40 ? 40 : hours)) + (hours > 40 ? rate * (hours - 40) * 1.5 : 0);
        System.out.println("Here's your pay fellow worker, $" + pay);
        
        //if-statements that determine an extra dialogue to output depending on the entered variables
        if (pay <= 12) { 
            System.out.println("\nAlso I would like a Big Mac, and 20 nuggets"); 
        }
        else if (pay > 2000) {
            System.out.println("\nCan, can I have some?");
        }
        else if (pay > 300) {
            if (hours >= 24) { System.out.println("\nHoly crap, do you ever rest!?"); }
            else if (rate >= 99) { System.out.println("\nWhere and how can I apply for your job position?"); }
            else { System.out.println("\nThat's pretty good!"); }
        }
        else {
            System.out.println("\nAverage");
        } 
    }

    
    
    
    //This function is called when the user makes an input error.
    //Will output a random response.
    private static void FriendlyError() {
        Random rand = new Random();

        switch (rand.nextInt(5) + 1) {
            case 1:
                System.out.println("There once was a s***head, just like you\nwho made an input error even though it told them what to f****ng input\nAnd censored me too.");
                break;
            case 2:
                System.out.println("Wha... wait, huh?\nError???");
                break;
            case 3:
                System.out.println("I- I don't what to say\nliterally told you what to input");
                break;
            case 4: {
                int num = rand.nextInt(1000);
                
                System.out.println("CONGRATULATIONS!!!\n");
                System.out.println("I'd give that a " + num + " / 100");
                if (num > 100) {
                    System.out.println("wait, what???");
                }
            }
                break;
            case 5:
                for (int i = 0; i < 3000; ++i){
                    System.out.println("ERROR!!!ERROR!!!ERROR!!!ERROR!!!ERROR!!!ERROR!!!ERROR!!!ERROR!!!ERROR!!!ERROR!!!ERROR!!!ERROR!!!ERROR!!!ERROR!!!ERROR!!!");
                }
                for (int i = 0; i < 100; ++i){
                    System.out.println("\n");
                }
                System.out.println("I'm Okay...");
                break;
            default:
                System.out.println("You made a mistake so bad that even this program broke\nHOW?!?!?");
        }

        System.out.println("\n\n");
    }

}
