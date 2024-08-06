package CombatPackage;
//package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.util.ArrayList;

//verison: 0.1.1
public class Monster {

    private String name;
    private int maxHp;
    private int curHp;
    
    private static boolean hasReadFromFile = false;
    public static ArrayList<String> nameList = new ArrayList();
    private static String fileLocation = "src\\pokemon.txt"; //edit file path here

    //===== constructors ==========================================================
    public Monster() {
        setRandomName();
        setRandomMaxHp();
    }
    
    public Monster(String _name) {
        this.name = _name;
        setRandomMaxHp();
    }
    
    public Monster(int hp) {
        this.maxHp = hp;
        this.curHp = this.maxHp;
        setRandomName();
    }

    public Monster(String _name, int hp) {
        this.name = _name;
        this.maxHp = hp;
        this.curHp = maxHp;
    }
    //=============================================================================

    
    //System.out.println(111);
    public void setName(String name) {
        this.name = name;
    }
    
    
    private void setRandomName() {
        //reads the pokemon.txt file for list of pokemon names storing in an ArrayList<String>
        if (!hasReadFromFile) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(new File(fileLocation)));
                String nameData;
                
                try {
                    while ((nameData = reader.readLine()) != null) {
                        nameList.add(nameData);
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
        
        this.name = nameList.get(new Random().nextInt(nameList.size()));
    }


    private void setRandomMaxHp() {
        Random power = new Random();
       
        this.maxHp = power.nextInt(100) + 20;
        this.curHp = this.maxHp;
    }

    public void setHp(int amount) { this.curHp = amount > this.maxHp ? this.maxHp : amount; }



    //Accessors
    public int getMaxHp() { return this.maxHp; }
    public int getHp() { return this.curHp; }
    public String getName() { return this.name; }



    //sets curHp to maxHp if healed over maxHp else add amount to heal
    public void heal(int amount) {
        this.curHp = this.curHp + amount > maxHp ? this.maxHp : this.curHp + amount;
    }


    //recover Hp by percentage of max Hp
    public void recoverHp(float percent) {
        if (this.curHp < 0) { this.curHp = 0; }
        this.curHp += (int)((float)this.maxHp * percent);
        if (this.curHp > this.maxHp) { this.curHp = this.maxHp; }
    }


    // HP is subtracted the number passed (Damage)
    public void Dmg(int hit) {
        this.curHp = this.curHp - hit < 0 ? 0 : this.curHp - hit ;
    }



}