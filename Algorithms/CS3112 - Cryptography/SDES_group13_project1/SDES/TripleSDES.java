package SDES;

import SDES.SDES;
import SDES.KeyGeneration;

public class TripleSDES {
	public static short encrypt(short plain, KeyGeneration keyGen1, KeyGeneration keyGen2) {
		short cipher;
		
		//first cipher
		cipher = SDES.encrypt(plain, keyGen1);
		
		//reverse cipher
		cipher = SDES.decrypt(cipher, keyGen2);
		
		//second cipher
		cipher = SDES.encrypt(cipher, keyGen1);
		
		return cipher;
	}
	
	
	public static short decrypt(short cipher, KeyGeneration keyGen1, KeyGeneration keyGen2) {
		short plain;
		
		//first reverse cipher
		plain = SDES.decrypt(cipher, keyGen1);
		
		//cipher
		plain = SDES.encrypt(plain, keyGen2);
		
		//second reverse cipher
		plain = SDES.decrypt(plain, keyGen1);
		
		return plain;
	}
}
