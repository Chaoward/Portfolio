package SDES;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;

import SDES.*;

public class Part3 {

	public static void part3() {
		//PLEASE FILL CONST with file paths to msg.txt files and out.txt files
		String READ_MSG_1 = "msg1.txt";
		String READ_MSG_2 = "msg2.txt";
		String OUT_FILE_1 = "out.txt";
		String OUT_FILE_2 = "out2.txt";
		
		ArrayList<Short> data = new ArrayList<Short>();
		
		//read the file and convert to binary values
		try {
		      FileReader file = new FileReader(READ_MSG_1);
		      BufferedReader reader = new BufferedReader(file);
		      
		      try {
		    	int curPos = 0b10000;
		    	int curValue = 0;
		    	int bit;
		    	while ( (bit = reader.read()) > 0 ) {
		    		if ( (char)bit == '1')
		    			curValue = curValue | curPos;
		    		if (curPos == 1) {
		    			data.add( (short) curValue );
		    			curValue = 0;
		    			curPos = 0b10000;
		    		}
		    		else
		    			curPos = curPos >> 1;
		    	}
			} catch (IOException e) {
				e.printStackTrace();
			}
		      
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		
		//produce all possible decryptions with all possible keys
		short key = 0;
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(OUT_FILE_1));
			while (key <= 0b1111111111) {
				String result = key + " : ";
				KeyGeneration keyGen = new KeyGeneration(key);
				
				for (short in : data) {
					result += CASCII.Convert( toArray(SDES.decrypt(in, keyGen), 5) );
				}
				
				System.out.println(result);
				writer.write(result + "\n\n");
				key++;
			}
			writer.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//===== Triple SDES ==========
		data = new ArrayList<Short>();
		
		//read the file and convert to binary values
		try {
		      FileReader file = new FileReader(READ_MSG_2);
		      BufferedReader reader = new BufferedReader(file);
		      
		      try {
		    	int curPos = 0b10000;
		    	int curValue = 0;
		    	int bit;
		    	while ( (bit = reader.read()) > 0 ) {
		    		if ( (char)bit == '1')
		    			curValue = curValue | curPos;
		    		if (curPos == 1) {
		    			data.add( (short) curValue );
		    			curValue = 0;
		    			curPos = 0b10000;
		    		}
		    		else
		    			curPos = curPos >> 1;
		    	}
			} catch (IOException e) {
				e.printStackTrace();
			}
		      
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		
		//produce all possible decryptions with all possible keys
		key = 0;
		short key2 = 0;
		try {
			writer = new BufferedWriter(new FileWriter(OUT_FILE_2));
			while (key <= 0b1111111111) {
				KeyGeneration keyGen = new KeyGeneration(key);
				while (key2 <= 0b1111111111) {
					String result = key + "," + key2 + " : ";
					KeyGeneration keyGen2 = new KeyGeneration(key2);
					
					for (short in : data) {
						result += CASCII.Convert( toArray(TripleSDES.decrypt(in, keyGen, keyGen2), 5) );
					}
					
					System.out.println(result);
					writer.write(result + "\n\n");
					key2++;
				}
				key2 = 0;
				key++;
			}
			writer.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	//function to convert short type binary into an array representation
	//Used for CASCII conversion
	public static byte[] toArray(short v, int size) {
    	byte[] arr = new byte[size];
    	short curPos = 1;
    	
    	for (int i = 0; i < arr.length; i++) {
    		arr[i] = (byte) ((v & curPos) != 0 ? 1 : 0);
    		curPos = (short) (curPos << 1);
    	}
    	
    	return arr;
    }
}
