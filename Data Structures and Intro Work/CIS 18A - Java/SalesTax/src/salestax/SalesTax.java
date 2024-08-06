package salestax;

import java.util.Scanner;

/**
 * This program is a store, will display the total prices plus sales tax.
 * The prompt you gave us lack in detail for this task, so this is what I made.
 * NOTE: yes there are stuff we didn't discuss in class
 */

public class SalesTax {
   
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cart[] = new int[]{0,0,0,0};
        int jin = 0;
        
        System.out.println("Enter the tax rate: ");
        float taxRate = input.nextFloat();
        
        //loop to select items for purchase
        do {
            cart[jin] += (jin >= 1 && jin <= 3 ? 1 : 0);
            
            System.out.println("//==== Welcome to the Store ====\\\\"
                    + "\n1.) Generic Common Sword : $100"
                    + "\n2.) Generic Food Item : $10.03"
                    + "\n3.) Generic Useless Item : $30.99"
                    + "\n0.) finish");
            
            System.out.println("\n\nIn Cart: " 
                    + (cart[1] != 0 ? "Sword " + cart[1] + "X " : " ") 
                    + (cart[2] != 0 ? "Food " + cart[2] + "X " : " ") 
                    + (cart[3] != 0 ? "Useless " + cart[3] + "X " : " "));
            
            jin = input.nextInt();
            
        } while(jin != 0);
        
        
        float total = 0;
        for (int i = 0; i < 3; ++i) {
            switch(i) { //switch cases with for loops to add the amount of the same items
                case 1:
                    for (int j = 0; j < cart[i]; ++j){
                        total += 100f;
                    }
                    break;
                case 2:
                    for (int j = 0; j < cart[i]; ++j){
                        total += 10.03f;
                    }
                    break;
                case 3:
                    for (int j = 0; j < cart[i]; ++j){
                        total += 30.99f;
                    }
                    break;
            }
        }
        
        
        System.out.print("\n\nPrice: ");
        System.out.printf("%.2f", total);
        System.out.print("\nSales Tax: " + taxRate + "\nTotal: ");
        System.out.printf("%.2f", total + (taxRate * total));
        
    }
    
}