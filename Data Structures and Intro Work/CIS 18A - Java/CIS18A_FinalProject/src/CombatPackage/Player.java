package CombatPackage;

//import com.company.Monster;
import base.Main;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

//version: 0.1.0
public class Player {
    public static ArrayList<Monster> pokeDex;

    public int pokeballs = 5;
    public int potions = 2;
    public int points = 0;
    public int health = 100;
    private String name;
    
    static Scanner input;

    //verison: 0.0.1
    public Player() {
        pokeDex = new ArrayList();
        input = new Scanner(System.in);

        do {
            System.out.print("Enter Player Name: ");
            name = input.nextLine();
        } while (name == null || name == "");

        pokeDex.add(0, new Monster("Pikachu", 35));
    }
    
  
    
    public String getName() { return this.name; }
    
    
    
    public void displayPokemons() {
        System.out.println("\n\n//==== Pokemon Storage =====\\\\");
        for (int i = 0; i < this.pokeDex.size(); ++i) {
            System.out.print((i + 1) + ".) " + this.pokeDex.get(i).getName());
            System.out.println("  " + this.pokeDex.get(i).getHp() + " / " + this.pokeDex.get(i).getMaxHp() + " HP");
        }
    }



    //===== capture() =====================================
    /* Returns bool depending if the pokemon is captured or
       defeated. Attempts to capture the passed in MonsterObj
       with a roll, results varying in MonsterObj's HP. */
    //=====================================================
    public boolean capture(Monster pokemon) {
        if (pokeballs > 0) {
            Random rng = new Random();
            int threshold = (pokemon.getHp() + (int)((float)pokemon.getHp() * 0.1f));
            //--this.pokeballs;

            Main.delay(1500);
            if ( rng.nextInt(pokemon.getMaxHp()) + 1 >= threshold) {
                pokemon.recoverHp(1.0f);
                pokeDex.add(pokemon);
                System.out.println("YOU CAUGHT " + pokemon.getName() + "!");
                this.points += 100;

                return true;
            }
            else {
                System.out.println(pokemon.getName() + " resist being captured!");
            }
        }
        else {
            System.out.println("You ran out of Pokeballs!");
        }

        return false;
    }
    //=====================================================
    
    
    
    //==== item() =================================================================
    /* Subtracts one from the selected item variable and returns an int choice for CombatEvent. */
    //=============================================================================
    public int item() {
        while(true) {
            System.out.println("What item do you wish to use? \n 1 Pokeball: " + pokeballs + "\n 2 Potion: " + potions + "\n 0 Back");
            switch(input.nextLine()) {
                case "1":
                    if (pokeballs > 0) {
                        pokeballs -= 1;
                        return 1;
                     } else { System.out.println("You don't have any to use!"); }
                    break;
                    
                case "2":
                    if (potions > 0) {
                        potions -= 1;
                        return 2;
                    }else {
                        System.out.println("You don't have any to use!");
                    }
                case "0":
                    return 0;
                default:
                    System.out.println("That was not a valid choice, try again!");
            }
        }

    }
    //=============================================================================
}

