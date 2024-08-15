package SDES;

public class PermTable {
	private static short[] IP = new short[] {
			0b01000000, 0b00000100, 0b00100000, 0b10000000,
			0b00010000, 0b00000001, 0b00001000, 0b00000010
			};
	private static short[] FP = new short[] {
			0b00010000, 0b10000000, 0b00100000, 0b00001000,
			0b00000010, 0b01000000, 0b00000001, 0b00000100
			};
	private static short[] EP = new short[] {
			0b1000, 0b0001, 0b0010, 0b0100,
			0b0010, 0b0100, 0b1000, 0b0001
			};
	private static short[] P4 = new short[] {0b0010, 0b1000, 0b0100, 0b0001};
	private static short[] P10 = new short[] {
			0b0010000000, 0b0000100000, 0b0100000000, 0b0000001000, 0b0001000000,
			0b0000000001, 0b1000000000, 0b0000000010, 0b0000000100, 0b0000010000
			};
	private static short[] P8_compress = new short[] {
			0b0000010000, 0b0010000000, 0b0000001000, 0b0001000000,
			0b0000000100, 0b0000100000, 0b0000000001, 0b0000000010
			};
	
	private static short[][] S0 = new short[][] {{1,3,0,3}, {0,2,2,1}, {3,1,1,3}, {2,0,3,2}};
	private static short[][] S1 = new short[][] {{0,2,3,2}, {1,0,0,1}, {2,1,1,0}, {3,3,0,3}};
	
	
	//===== initial and final =====================
	public static short initial_P(short plain) {
		return permutate(plain, PermTable.IP);
	}
	
	public static short final_P(short plain) {
		return permutate(plain, PermTable.FP);
	}
	
	//===== key permutations =======================
	public static short keyCompress(short key) {
		return permutate(key, P8_compress);
	}
	
	public static short P10(short key) {
		return permutate(key, P10);
	}
	
	//===== F function permutations ================
	public static short expand_EP(short halfPlain) {
		return permutate(halfPlain, EP);
	}
	
	public static short P4(short halfPlain) {
		return permutate(halfPlain, P4);
	}
	
	public static short S_box(short plain) {
		short l_half = (short) ((short) (plain & 0b11110000) >> 4);
		short r_half = (short) (plain & 0b1111);
		
		//first half
		short colKey = (short) ((short) (l_half & 0b0110) >> 1);										//middle bits
		short rowKey = (short) ((short) ((short) (l_half & 0b1000) >> 3) + (short) (l_half & 0b0001));	//end bits
		l_half = S0[colKey][rowKey];
		
		//second half
		colKey = (short) ((short) (r_half & 0b0110) >> 1);
		rowKey = (short) ((short) ((short) (r_half & 0b1000) >> 3) + (short) (r_half & 0b0001));
		r_half = S1[colKey][rowKey];
		
		//combine
		short cipher = (short) ((short) (l_half << 2) | r_half);
		
		return cipher;
	}
	
	
	
	public static short permutate(short plain, short[] permTable) {
		short curPos = 1;
		short cipher = 0;
		
		//permutation starts at the end of the table
		for (int i = permTable.length - 1; i >= 0; i-- ) {
			//bit OR cipher with permTable entry if the current plain bit is 1
			if ( (plain & permTable[i]) != 0) {		//checks if position i is a 1
				cipher = (short) (cipher | curPos);	//change the current position of cipher to 1
			}
				
			//shift the 1 bit to left
			curPos = (short) (curPos << 1);
		}
		
		//truncate by bit-AND binary of 1's to the length of permTable
		short temp = 0;
		for (int i = 0; i < permTable.length; i++)	//building value of valid bits
			temp = (short) ((temp << 1) + 1);
		cipher = (short) (cipher & temp);
		
		return cipher;
	}
}
