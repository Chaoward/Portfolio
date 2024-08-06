package CombatPackage;

import base.Main;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

//verison: 0.1.0
public class CombatEvent {

    static boolean captured;
    static Random rng = new Random();
    static Scanner input = new Scanner(System.in);

    Player player;
    Monster wildMob;

    public CombatEvent(Player player, Monster enemyMon) {
        captured = false;
        this.player = player;
        this.wildMob = enemyMon;
    }

    //===== combat() ==================================================================
    /* returns a win (true) or lose (false). Main turn-based game-loop is here, and ends
       when the enemy poke is captured. */
    //=================================================================================
    public Boolean combat() {
        Monster playerMob = choosePokemon(player.pokeDex);

        int dmg = 0;
        int accuracy = 0;
        boolean validChoice = true;
        boolean isSwitching = false;

        //combat loop
        while (wildMob.getHp() > 0 && !captured) {

            do { //input loop
                validChoice = true;
                isSwitching = true;

                System.out.println("//===== Battle Stats =====\\\\");
                System.out.println("Wild " + wildMob.getName()
                        + "\nHP: " + wildMob.getHp() + " / " + wildMob.getMaxHp()
                        + "\n\nYour " + playerMob.getName()
                        + "\nHP: " + playerMob.getHp() + " / " + playerMob.getMaxHp()
                        + "\nYour Life: " + player.health
                );
                System.out.println("\nWhat will you do? (enter the number of your choice!)");
                System.out.println("1 Attack \n2 Inventory \n3 RUN \n4 Switch Pokemon");

                switch (input.nextLine()) {
                    case "1": //fight
                        if (playerMob != null && playerMob.getHp() > 0) {
                            accuracy = rng.nextInt(100) + 1;

                            if (accuracy <= 90 && accuracy > 85) {
                                System.out.println("A Critical Hit!");
                                dmg = (rng.nextInt(15) + 11) * 2;
                                Main.delay(2000);
                                System.out.println("You did " + dmg + " damage!");
                                wildMob.Dmg(dmg);
                            } else if (accuracy <= 85) {
                                dmg = rng.nextInt(15) + 11;
                                System.out.println("You dealt " + dmg + " damage to the enemy!");
                                wildMob.Dmg(dmg);
                                Main.delay(2000);
                                if (wildMob.getHp() <= 0) {
                                    System.out.println("YOU WIN!!!!!!!");
                                    Main.delay(2000);
                                    return true;
                                }
                            } else {
                                System.out.println("The enemy dodged the attack!");
                            }
                        }
                        else { System.out.println("You shout at " + playerMob.getName() + " to attack, but it's too weak to get up"); }
                        break;

                    case "2": //Using an Item
                        switch (player.item()) {
                            case 1: //capture
                                System.out.println("You throw a Pokeball!");
                                captured = player.capture(wildMob);
                                break;

                            case 2: //healing item
                                playerMob.heal(30);
                                System.out.println("Your " + playerMob.getName() + " healed 30 hp!");
                                break;

                            case 0:
                            default:
                                validChoice = false;
                        }
                        break;

                    case "3": //RUN!!!
                        if (rng.nextInt(5) + 1 >= 4) {
                            System.out.println("Wow you're a coward, you got away...");
                            Main.delay(2000);
                            return true;
                        } else {
                            System.out.println("You failed to run away, brace for the enemy's attack!");
                        }
                        break;

                    case "4": //Switch Pokemon
                        if (playerMob.getHp() <= 0) { isSwitching = false; }
                        playerMob = choosePokemon(player.pokeDex);
                        System.out.println(playerMob.getName() + " is on the field!");
                        break;

                    default:
                        System.out.println("I'm sorry, I didn't quite catch that, try again.");
                        validChoice = false;
                }
            } while (!validChoice);

            Main.delay(2000);
            //Enemy Actions
            if ((!isSwitching && !captured) || (playerMob.getHp() <= 0 && !captured)) { //targeting the player
                player.health -= (dmg = (rng.nextInt(15) + 11) * ((accuracy = rng.nextInt(100) + 1) >= 75 ? 2 : 1));
                System.out.println(wildMob.getName() + " Attacks" + (accuracy >= 75 ? " with a Critical Hit!" : "!"));
                Main.delay(1800);
                System.out.println("Dealing " + dmg + " Damage to you!");
            }
            else if (!captured && wildMob.getHp() > 0) { //targeting the pokemon
                accuracy = rng.nextInt(100) + 1;

                if (accuracy <= 80 && accuracy > 75) {
                    System.out.println("A Critical Hit!");
                    dmg = (rng.nextInt(15) + 11) * 2;
                    Main.delay(2000);
                    System.out.println("The enemy did " + dmg + " damage!");
                    playerMob.Dmg(dmg);
                } else if (accuracy <= 75) {
                    dmg = rng.nextInt(15) + 11;
                    System.out.println("You were dealt " + dmg + " damage!");
                    playerMob.Dmg(dmg);
                } else {
                    System.out.println("Your pokemon has dodged the attack!");
                }
            }
            Main.delay(2000);
            
            if (player.health <= 0) {
                    System.out.println("\n\n  |=== YOU DIED! ===|\n\n");
                    Main.delay(2000);
                    return false;
            }
        }

        return true;
    }
    //==============================================================================

    
    
    //===== choosePokemon() ==========================================================
    /* returns Monsters that the user chooses. */
    //==============================================================================
    public static Monster choosePokemon(ArrayList<Monster> inven) {
        System.out.println("//===== Choose Your Pokemon =====\\\\");

        //display pokemon in inventory
        for (int i = 0; i < inven.size(); ++i) {
            System.out.println((i + 1) + ".) " + inven.get(i).getName() + " HP: " + inven.get(i).getHp() + " / " + inven.get(i).getMaxHp());
        }

        int choice = input.nextInt() - 1;
        while (choice < 0 || choice >= inven.size()) {
            System.out.println("INVALID CHOICE!!!\nTry again");
            choice = input.nextInt() - 1;
        } 
        
        input.nextLine();
        if (inven.get(choice).getHp() <= 0) {
            inven.get(choice).recoverHp(0.3f);
        }

        return inven.get(choice);
    }
    //==============================================================================
    
    
   
}
