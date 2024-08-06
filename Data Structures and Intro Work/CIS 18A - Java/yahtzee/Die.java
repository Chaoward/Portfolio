/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yahtzee;
import java.util.Random;


public class Die {
    private long value;
    private long[][] diceSet;
    private int[] setSize;
    private static Random rand;
    
    Die() {
        value = 0;
        rand = new Random();
        setSize = new int[2];
        diceSet = new long[setSize[0]][setSize[1]];
    }
    
    
    
    public long roll() {
        value = Math.round(rand.nextInt(6) + 1);
        return value;
    }
    
    public void rollSet(int sets, int amountPerSet) {
        setSize[0] = sets;
        setSize[1] = amountPerSet;
        diceSet = new long[sets][amountPerSet];
        
        for (int i = 0; i < sets; ++i) {
            for (int j = 0; j < amountPerSet; ++j) {
                diceSet[i][j] = roll();
            }
        }
    }
    
    public void printCurrentSet() {
        for (int i = 0; i < setSize[0]; ++i) {
            System.out.println("===== Set " + (i + 1) + " =====");
            for (int j = 0; j < setSize[1]; ++j) {
                System.out.println("roll" + (j + 1) + ": " + diceSet[i][j]);
            }
        }
    }
    
    public long getCurrentValue() { return value; }
}
