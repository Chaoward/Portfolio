package SDES;

import java.util.*;

/* This class generates two 8-bit subkeys from a 10-bit key */
class KeyGeneration
{
    /* Raw 10-bit key used by sender & reciever*/
	private int[] key = new int[10];

    /* two 8-bit sub-keys for use in particular stages */
    /* of the encryption and decryption algorithm      */
    private int[] K1 = new int[8];
    private int[] K2 = new int[8];

    private boolean flag = false;

    KeyGeneration(short k)
    {
    	this.key = toArray(k);
    	GenerateSubKeys();
    }

    void GenerateSubKeys()
    {

        //permutates the 10-bit key
        permutationP10();

        leftshiftLS();

        //Key 1 is being Generated
        this.K1 = permutationP8();

        leftshiftLS2();

        //Key 2 is being Generated
        this.K2 = permutationP8();

        flag = true;
    }

    /* 
        Permutation of P10 of 10-bit key
        P10(k1, k2, k3, k4, k5, k6, k7, k8, k9, k10) = (k3, k5, k2, k7, k4, k10, k1, k9, k8, k6)
    */

    private void permutationP10()
    {
        int[] temp = new int[10];

        temp[0] = key[2];
        temp[1] = key[4];
        temp[2] = key[1];
        temp[3] = key[6];
        temp[4] = key[3];
        temp[5] = key[9];
        temp[6] = key[0];
        temp[7] = key[8];
        temp[8] = key[7];
        temp[9] = key[5];

        key = temp;
    }

    /*
        Performs a left shift (LS-1) rotation, separately on the first five bits and the second five bits.
    */

    private void leftshiftLS()
    {
        int[] temp = new int[10];
    
        temp[0] = key[1];
        temp[1] = key[2];
        temp[2] = key[3];
        temp[3] = key[4];
        temp[4] = key[0];
    
        temp[5] = key[6];
        temp[6] = key[7];
        temp[7] = key[8];
        temp[8] = key[9];
        temp[9] = key[5];
    
        key = temp;
    }

    /*
        Permutaion P8, which picks out and permutes 8 of the 10 bits following 
        the rule: P8[ 6 3 7 4 8 5 10 9 ], subkey is returned (8bit)
    */

    private int[] permutationP8()
    {
        int[] temp = new int[8];
    
        temp[0] = key[5];
        temp[1] = key[2];
        temp[2] = key[6];
        temp[3] = key[3];
        temp[4] = key[7];
        temp[5] = key[4];
        temp[6] = key[9];
        temp[7] = key[8];
    
        return temp;
    }

    private void leftshiftLS2()
    {
        int[] temp = new int[10];
    
        temp[0] = key[2];
        temp[1] = key[3];
        temp[2] = key[4];
        temp[3] = key[0];
        temp[4] = key[1];
    
        temp[5] = key[7];
        temp[6] = key[8];
        temp[7] = key[9];
        temp[8] = key[5];
        temp[9] = key[6];
    
        key = temp; 
    }

    public short getK1()
    {
        if(!flag)
        {
            System.out.println("\nKeys were not generated. ");
            return -1;
        }
        return toShort(this.K1);
    }

    public short getK2()
    {
        if(!flag)
        {
            System.out.println("\nKeys were not generated. ");
            return -1;
        }
        return toShort(this.K2);
    }
    
    
    public short toShort(int[] arr) {
    	short value = 0;
    	short curPos = 1;
    	
    	for (int i = arr.length-1; i >= 0; i--) {
    		if (arr[i] == 1) {
    			value = (short) (value | curPos);
    			value = (short) (value << 1);
    			curPos = (short) (curPos << 1);
    		}
    	}
    	
    	return value;
    }
    
    public int[] toArray(short v, int size) {
    	int[] arr = new int[size];
    	short curPos = 1;
    	
    	for (int i = 0; i < arr.length; i++) {
    		arr[i] = (v & curPos) != 0 ? 1 : 0;
    		curPos = (short) (curPos << 1);
    	}
    	
    	return arr;
    }
    
    public int[] toArray(short v) {
    	return toArray(v, 10);
    }
}
