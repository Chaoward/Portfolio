package SDES;

import SDES.PermTable;
import SDES.KeyGeneration;

public class SDES {
	
	public static String encrypt(String plain, KeyGeneration keyGen) {
		String cipher = "";
		
		for (int i = 0; i < plain.length(); i++) {
			cipher += (char) SDES.encrypt((short) (plain.charAt(i)), keyGen);
		}
		
		return cipher;
	}
	
	public static String decrypt(String cipher, KeyGeneration keyGen) {
		String plain = "";
		
		for (int i = 0; i < cipher.length(); i++) {
			plain += (char) SDES.decrypt((short) (cipher.charAt(i)), keyGen);
		}
		
		return plain;
	}
	
	public static short encrypt(short plain, KeyGeneration keyGen) {
		
		short firstPerm = PermTable.initial_P(plain);
		
		short firstRound = swap( round(firstPerm, keyGen.getK1()) );
		
		short secondRound = round(firstRound, keyGen.getK2());
		
		return PermTable.final_P(secondRound);
	}
	
	
	public static short decrypt(short cipher, KeyGeneration keyGen) {
		
		short firstPerm = PermTable.initial_P(cipher);
		
		short firstRound = swap( round(firstPerm, keyGen.getK2()) );
		
		short secondRound = round(firstRound, keyGen.getK1());
		
		return PermTable.final_P(secondRound);
	}


	public static short round(short plain, short subkey) {
		short left = (short) (plain & 0b11110000);
		left = (short) (left >> 4);
		short right = (short) (plain & 0b00001111);
		
		//F-function and XOR
		left = (short) (left ^ f(right, subkey));
		
		return (short) ((short) (left << 4) | right);
	}
	
	
	public static short f(short r, short subkey){

		  // Expansion: Expand the input 'r' using the E/P permutation.
		  short expanded = PermTable.expand_EP(r);
		  
		  // XOR with subkey: Apply bitwise XOR between the expanded data and the subkey.
		  expanded ^= subkey;

		  // Use the S-box tables to perform substitution.
		  // Store the S-box outputs in an array.
		  short sOutput = PermTable.S_box(expanded);

		  // Final permutation: Rearrange the bits according to the P4 permutation. 
		  short fOutput = PermTable.P4(sOutput);

		  // Return the result of the function, which is the output of the F function.
		  return fOutput;

		}
	
	
	private static short swap(short plain) {
		//split and move
		short left = (short) (plain & 0b11110000);
		left = (short) (left >> 4);
		
		short right = (short) (plain & 0b00001111);
		right = (short) (right << 4);
		
		//combine
		return (short) (left | right);
	}
}
