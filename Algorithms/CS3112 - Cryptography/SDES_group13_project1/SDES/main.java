package SDES;
import SDES.*;

public class main {
	
	public static void main(String[] s) {
		//Sample code
		/*
		short input = 0b11101100;
		short cipher;
		KeyGeneration sample_key = new KeyGeneration( (short) 0b1101010011 );
		
		System.out.println("===== Smaple =====");
		System.out.println("input: " + input);
		System.out.println("cipher: " + (cipher = SDES.encrypt(input, sample_key) ) );
		System.out.println("decipher: " + SDES.decrypt(cipher, sample_key) + "\n");
		*/
		
		
		short[][] cipherEntries = new short[][] {
			{0b0000000000, 0b00000000},
			{0b1111111111, 0b11111111},
			{0b0000011111, 0b00000000},
			{0b0000011111, 0b11111111},
		};
		short[][] decipherEntries = new short[][] {
			{0b1000101110, 0b00011100},
			{0b1000101110, 0b11000010},
			{0b0010011111, 0b10011101},
			{0b0010011111, 0b10010000},
		};
		
		//===== Part 1 ===============
		System.out.println("PART 1:");
		System.out.println("Raw Key | plain | cipher");
		for (short[] pair : cipherEntries) {
			short in = pair[1];
			KeyGeneration key = new KeyGeneration(pair[0]);
			System.out.println(Integer.toBinaryString(pair[0]) + " "
					+ Integer.toBinaryString(pair[1]) + " "
					+ Integer.toBinaryString(SDES.encrypt(in, key))
			);
		}
		
		for (short[] pair : cipherEntries) {
			short in = pair[1];
			KeyGeneration key = new KeyGeneration(pair[0]);
			System.out.println(Integer.toBinaryString(pair[0]) + " "
					+ Integer.toBinaryString(SDES.decrypt(in, key)) + " "
					+ Integer.toBinaryString(pair[1])
			);
		}
		
		//===== Part 2 ===========
		cipherEntries = new short[][] {
			{0b0000000000, 0b0000000000, 0b00000000},
			{0b1000101110, 0b0110101110, 0b11010111},
			{0b1000101110, 0b0110101110, 0b10101010},
			{0b1111111111, 0b1111111111, 0b10101010}
		};
		
		decipherEntries = new short[][] {
			{0b1000101110, 0b0110101110, 0b11100110},
			{0b1011101111, 0b0110101110, 0b01010000},
			{0b0000000000, 0b0000000000, 0b10000000},
			{0b1111111111, 0b1111111111, 0b10010010}
		};
		
		
		System.out.println("\nPART 2:");
		System.out.println("Raw Key 1 | Raw Key 2 | plain | cipher");
		for (short[] pair : cipherEntries) {
			short in = pair[2];
			KeyGeneration k1 = new KeyGeneration(pair[0]);
			KeyGeneration k2 = new KeyGeneration(pair[1]);
			
			System.out.println(Integer.toBinaryString(pair[0]) + " "
					+ Integer.toBinaryString(pair[1]) + " "
					+ Integer.toBinaryString(in) + " "
					+ Integer.toBinaryString(TripleSDES.encrypt(in, k1, k2))
			);
		}
		
		for (short[] pair : decipherEntries) {
			short in = pair[2];
			KeyGeneration k1 = new KeyGeneration(pair[0]);
			KeyGeneration k2 = new KeyGeneration(pair[1]);
			
			System.out.println(Integer.toBinaryString(pair[0]) + " "
					+ Integer.toBinaryString(pair[1]) + " "
					+ Integer.toBinaryString(TripleSDES.decrypt(in, k1, k2)) + " "
					+ Integer.toBinaryString(in)
			);
		}
		
		
		//===== Part 3 ===========
		KeyGeneration key = new KeyGeneration((short) 0b0111001101);
		String c;
		
		System.out.println("\nPART 3:");
		System.out.println("Cipher: " + (c = SDES.encrypt("CRYPTOGRAPHY", key)) );
		System.out.println("Plain: " + SDES.decrypt(c, key) );
		
		//uncoment to do part 3
		//Part3.part3();
	}
}
