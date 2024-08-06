package base;
//Main class

//Function: Contains the main game loop, calls other classes
import CombatPackage.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.net.URISyntaxException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import static java.awt.Desktop.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {

    static String fileLocation = "src\\scoreboard.txt";
    static String SPLIT_PREFIX = "~";
    static ArrayList<String[]> scoreHistory;
    static boolean hasReadFromFile = false;
    private static Player player = new Player();

    public static void displayScoreboard() {
        if (!hasReadFromFile) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(new File(fileLocation)));
                scoreHistory = new ArrayList();
                String playerName;

                try {
                    while ((playerName = reader.readLine()) != null) {
                        scoreHistory.add(playerName.split(SPLIT_PREFIX));
                    }
                    hasReadFromFile = true;
                    reader.close();
                } catch (IOException e) {
                    System.out.println("///// ERROR /////\n[IOException]: ...");
                }

            } catch (FileNotFoundException e) {
                System.out.println("///// ERROR /////\n[FileNotFoundException]: possible error in opening the file");
            }
        }

        System.out.println("//===== SCOREBOARD =====\\\\");
        for (int i = 0; i < scoreHistory.size(); ++i) {
            System.out.println(scoreHistory.get(i)[0] + ": " + scoreHistory.get(i)[1] + "pts");      
        }
    }
    
    

    static void EndGame() {
        for (int i = 0; i < player.pokeDex.size(); ++i) {
            player.points += 20 + (player.pokeDex.get(i).getMaxHp() * 10);
        }

        System.out.println("Game Over");
        System.out.println("//===== FINAL SCORE =====\\\\\n" + player.getName() + ": " + player.points);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileLocation, true))) {
            writer.write(player.getName() + SPLIT_PREFIX + player.points);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }

    //========== MAIN =============================================================
    public static void main(String[] args) throws IOException, URISyntaxException {

        boolean gameRunning = true;
        int userChoice = 0;
        Scanner in = new Scanner(System.in);
        String gameMenu = "\nHello " + player.getName() + ", \nWhat would you like to do?\n1. Wilderness\n2. Pokémon\n3. Bag\n4. Pokécenter \n5. Scoreboard \n6. Quit game"; // Main
        // menu

        while (gameRunning == true) {

            // Error handling (gives users a nice error message if they don't put in an
            // integer)
            try { // Error handling if user does not enter an int
                System.out.println(gameMenu);
                userChoice = in.nextInt();
            } catch (InputMismatchException e) {
                in.next(); // accepts error token to avoid endless loop

            }

            switch (userChoice) { // Decide the game logic here

                case 1: // Go into the wilderness
                    wilderness();

                    break;

                case 2: // View captured pokemon
                    player.displayPokemons();
                    break;

                case 3: // View inventory (pokeballs)
                    System.out.println("//===== Inventory =====\\\\");
                    System.out.println("Pokéballs: " + player.pokeballs);
                    System.out.println("Potions: " + player.potions);
                    break;

                case 4: // Pokecenter (Heals all pokemon the player has in their team)
                    for (int i = 0; i < player.pokeDex.size(); i++) {
                        player.pokeDex.get(i).recoverHp(100);
                    }
                    
                    System.out.println(
                            "Welcome to our Pokémon Center! "
                            + "\nWe heal your Pokémon back to perfect health! \nMay I see your Pokémon?");
                    System.out.println("...");
                    delay(3000);
                    AudioPlayer.playAudio("src\\center.wav");
                    System.out.println("Thank you for waiting. \nYour Pokémon are fully healed. \nWe hope to see you again.");
                    delay(1800);
                    break;

                case 5:
                    displayScoreboard();
                    break;

                case 6: // Quits the game
                    System.out.println("Exiting game...");
                    gameRunning = false;
                    break;

                default: // I'd like game to keep running at this point, just ask menu again
                    System.out.println("\nInvalid choice, please try again!");
            }

        }

        EndGame();

    }
    //=============================================================================

    // This is the wilderness method which will bring our player out to the
    // wilderness where they will find pokeballs and fight/ capture wild pokemon
    static void wilderness() throws URISyntaxException, IOException {
        // wilderness loop
        boolean wildernessLoop = true;
        Random rand = new Random();
        Scanner input = new Scanner(System.in);
        System.out.println("Its a nice day to battle, wouldn't you agree " + player.getName() + "? \n Lets see what you find... ");
        delay(2200);

        while (wildernessLoop) {
            // rng for encounter/find pokeballs
            int wildernessRng = rand.nextInt(12);
            int pokeballRng = rand.nextInt(3);
            pokeballRng = pokeballRng + 1;
            // navigation menu
            System.out.println("\nWhich direction to you want to go?\n "
                    + "//=====Select=====\\\\ \n W for North\n A for West\n S for South\n D for East\n B for Main Menu");
            String selection = input.nextLine().toLowerCase();
            // Easter eggs
            if (selection.startsWith("x")) {
                System.out.println("\nProf. Oak: Good to see you! How is your Pokédex coming? Here, let me take a look...");
                delay(2000);
                profOakAssessment();
                
            } else if (selection.startsWith("b")) {
                wildernessLoop = false;
            } else if (selection.startsWith("y")) {
                getDesktop().browse(new java.net.URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ%22"));
            } else if (selection.startsWith("z")) {
                System.out.println(("You have found the third dimension... "
                        + "\nMewtwo appears out of thin air!\n"
                        + "He uses Manipulate on Pikachu to make him turn on you.\nPikachu shocks you with a lightning bolt."
                        + "\nYou are knocked unconscious."));
                wildernessLoop = false;
                // pseudo navigation to give the feeling of moving around.
            } else if (selection.startsWith("w") || selection.startsWith("a") || selection.startsWith("s")
                    || selection.startsWith("d")) {

                if (wildernessRng <= 2) {
                    // if rng is <= 2 you battle 3 in 10 chance
                    // String Name = addName();
                    Monster monster = new Monster();

                    do {
                        System.out.println("A Wild " + monster.getName() + " appears!\n 1. Fight \n 2. Run ");
                        selection = input.nextLine();
                        if (selection.equals("1")) {
                            CombatEvent newCombat = new CombatEvent(player, monster);

                            if (newCombat.combat() == false && player.health <= 0) {
                                EndGame();
                            }
                            player.points += 100;

                        } else if (selection.equals("2")) {
                            int runRng = rand.nextInt(5);
                            // 2 out of 5 chance to successfully get away away
                            if (runRng > 1) {
                                System.out.println("Phew that was close, you got away...");
                                // back to wilderness loop or game loop?
                            } else {
                                System.out.println("You did not get away, now you must fight!");
                                CombatEvent newCombat = new CombatEvent(player, monster);

                                if (newCombat.combat() == false && player.health <= 0) {
                                    EndGame();
                                } else {
                                    player.points += 100;
                                }
                            }

                        } else {
                            System.out.println("Please select a valid option");
                        }

                    } while (!(selection.equals("1")) && !(selection.equals("2")));
                    
                } // if wildernessrng > 2 < 5 you will find pokeballs you will find 1 to 3
                else if (wildernessRng > 2 && wildernessRng <= 5) {
                    System.out.println("You Found " + pokeballRng + " Pokeball(s)!");
                    player.pokeballs += pokeballRng;
                    
                    // 3 of 12 chances to not find anything
                } else if (wildernessRng > 5 && wildernessRng <= 9) {
                    System.out.println("There isn't anything over here...");
                    
                } // 3 of 12 chances to find potion
                else if (wildernessRng > 9) {
                    System.out.println("You found a Potion");
                }

            } else {
                System.out.println("Please select a valid option");

            }
        }

    }

    static void profOakAssessment() {

        if (player.pokeDex.size() < 4) {
            System.out.println("You still have lots to do. Look for Pokémon in grassy areas!\n");
        } else if (player.pokeDex.size() >= 4 && player.pokeDex.size() < 8) {
            System.out.println("You still need more Pokémon! Try to catch other species!\n");
        } else if (player.pokeDex.size() >= 8 && player.pokeDex.size() < 14) {
            System.out.println("Good, you're trying hard!\n");
        } else if (player.pokeDex.size() >= 14 && player.pokeDex.size() < 25) {
            System.out.println("Ho! This is getting even better!\n");
        } else {
            System.out.println("I'm impressed! It must have been difficult to do!\n");
        }

    }

    public static void delay(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

}
