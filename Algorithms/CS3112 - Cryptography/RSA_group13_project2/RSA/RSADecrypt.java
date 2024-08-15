package RSA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class RSADecrypt {
	
	 public static void main(String[] args) throws IOException {
	        if (args.length != 2) {
	            System.out.println("Usage: java RSADecrypt <ciphertext_file> <pri_key_file>");
	            return;
	        }

	        String ciphertextFileName = args[0];
	        String keyFileName = args[1];

	        // Read the key from a file
	        BufferedReader reader = new BufferedReader(new FileReader(keyFileName));
	        BigInteger[] privateKey = new BigInteger[] {
	        		new BigInteger(reader.readLine()),
	        		new BigInteger(reader.readLine())
	        };
	        reader.close();

	        decryptFile(ciphertextFileName, privateKey);
	 }
	 
	 public static void decryptFile(String msgFilePath, String keyFilePath) throws IOException {
		 main( new String[] {msgFilePath, keyFilePath} );
	 }
	 
	 public static void decryptFile(String fileName, BigInteger[] key) throws IOException {
		 BufferedReader reader = new BufferedReader(new FileReader(fileName));
		 BufferedWriter writer = new BufferedWriter(new FileWriter("test.dec"));
		 
		 for (String blockStr : reader.readLine().split(" ")) {
			//parse block to BigInt, then decrypt
			 BigInteger block = new BigInteger(blockStr);
			 String plainBlock = block.modPow(key[0], key[1]).toString();
			 if (plainBlock.length() % 2 != 0)	//add zero in front if uneven length
				 plainBlock = '0' + plainBlock;
			 
			 //parse a char, then write to file
			 String chr = "";
			 for (char c : plainBlock.toCharArray()) {
				 chr += c;
				 if (chr.length() < 2)
					 continue;
				 
				 int num = Integer.parseInt(chr);
				 
				 if (num > 26)
					 writer.write( (char)(num + (int)'A' - 27 ) );
				 else if (num == 26)
					 writer.write(" ");
				 else
					 writer.write( (char)(num + (int)'a') );
				 
				 chr = "";
			 }
		 }

		 writer.close();
		 reader.close();
		 System.out.println("Successfully decrypted message to file: test.dec");
	 } 
	 
}
