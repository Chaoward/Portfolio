package RSA;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class RSAKeyGen {
	private BigInteger[] publicKey = new BigInteger[2];
	private BigInteger[] privateKey = new BigInteger[2];
	
	private BigInteger p;
	private BigInteger q;
	private BigInteger e;
	
	
	//main
	 public static void main(String[] args) throws Exception {
		 if (args.length > 1 && args.length <= 3) {
			 new RSAKeyGen(args[0], args[1], args[2]);
		 }
		 else if (args.length == 1) {
			 new RSAKeyGen( Integer.parseInt(args[0]) );
		 }
		 else
			 throw new Exception("Invalid arguements");
	 }
	
	//===== random generated key =======================================================
	RSAKeyGen(int k) throws Exception {
		//generating primes
		this.p = new BigInteger(k, 100, new Random());
		this.q = new BigInteger(k, 100, new Random());
		
		//validate primes
		System.out.println("Validating Primes...");
		while (!isPrime(this.p)) {
			if (this.p.remainder(BigInteger.TWO).equals(BigInteger.ZERO))
				this.p.add(BigInteger.ONE);
			else
				this.p.add(BigInteger.TWO);
			
		}
	
		while (!isPrime(this.q)) {
			if (this.q.remainder(BigInteger.TWO).equals(BigInteger.ZERO))
				this.q.add(BigInteger.ONE);
			else
				this.q.add(BigInteger.TWO);
		}
		
		//generate e
		System.out.println("Finding e...");
		this.e = new BigInteger(k, new Random());
		while (!checkE()) {
			this.e = this.e.add(BigInteger.ONE);
			if (this.e.compareTo(phi_n()) != -1)
				this.e = new BigInteger("2");
		}
		
		generate();
	}
	//===== *END* random generated key *END* ==========================================
	
	
	//===== custom p and q ============================================================
	RSAKeyGen(String _p, String _q, String _e) throws Exception {
		constructor( new BigInteger(_p), new BigInteger(_q), new BigInteger(_e) );
	}


	RSAKeyGen(BigInteger _p, BigInteger _q, BigInteger _e) throws Exception {
		constructor(_p, _q, _e);
	}
	
	private void constructor(BigInteger _p, BigInteger _q, BigInteger _e) throws Exception {
		if (!isPrime(_p) || !isPrime(_q)) throw new Exception("Values entered are NOT Primes");
		
		this.p = _p;
		this.q = _q;
		this.e = _e;
		if (!checkE()) throw new Exception("value \'e\' is invalid");
		
		generate();
	}
	//===== *END* custom p and q *END* ======================================================

	
	private void generate() {
		System.out.println("Generating Keys...");
			
		//private key
		BigInteger d = inverseOf(phi_n(), this.e);	
		this.privateKey[0] = d;
		this.privateKey[1] = this.p.multiply(this.q);
		
		//public key
		this.publicKey[0] = this.e;
		this.publicKey[1] = this.p.multiply(this.q);
		
		System.out.println("Public : " + this.publicKey[0].toString());
		System.out.println("Private : " + this.privateKey[0].toString());
		System.out.println("N : " + this.publicKey[1].toString());
		
		//Save Keys to a file
		System.out.println("Saving to File...");
		FileWriter file;
		try {
			file = new FileWriter("public_key.txt");
			BufferedWriter writer = new BufferedWriter(file);
			writer.write(this.publicKey[0].toString() + "\n");
			writer.write(this.publicKey[1].toString());
			writer.close();
			
			file = new FileWriter("private_key.txt");
			writer = new BufferedWriter(file);
			writer.write(this.privateKey[0].toString() + "\n");
			writer.write(this.privateKey[1].toString());
			writer.close();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("Keys Generated.");
	}
	
	private BigInteger phi_n() {
		return this.p.subtract(BigInteger.ONE).multiply( this.q.subtract(BigInteger.ONE) );
	}
	
	
	private boolean checkE() {
		BigInteger phi = phi_n();
		
		if (this.e.compareTo(BigInteger.ONE) == -1 || this.e.compareTo(phi) != -1 )
			return false;
		
		if (!phi.gcd(this.e).equals(BigInteger.ONE))
			return false;
		
		return true;
	}
	
	
	private boolean isPrime(BigInteger v) {
		//check value is equal to BigInteger.ONE
		if (v.equals(BigInteger.TWO)) return true;
		if (
			v.equals(BigInteger.ONE) || 
			v.remainder(BigInteger.TWO).equals(BigInteger.ZERO)
		) return false;
		
		//if a number can divise the value, it is not a prime number
		for (BigInteger i = new BigInteger("3"); i.compareTo(v) == -1; i = i.add(BigInteger.ONE) ) {
			if ( v.remainder(i).equals(BigInteger.ZERO) )
				return false;
		}
		
		return true;
	}
	
	
	//Extended EU
	private BigInteger inverseOf(BigInteger mod, BigInteger val) {
		BigInteger r1 = mod;
		BigInteger r2 = val;
		BigInteger t1=BigInteger.ZERO, t2=BigInteger.ONE, r, q, t;
		
		while (r2.compareTo(BigInteger.ZERO) == 1) {
			BigInteger[] result = r1.divideAndRemainder(r2);
			r = result[1];
			q = result[0];
			t = t1.subtract( t2.multiply(q) );
			
			r1 = r2;
	        r2 = r;
	        t1 = t2;
	        t2 = t;
		}
		
		//add t1 and t2 if t1 is negative
		return t1.compareTo(BigInteger.ZERO) == -1 ? t1.add(t2) : t1;
	}
	
	public BigInteger[] getPublicKey() {
		return publicKey;
	}
	
	public BigInteger[] getPrivateKey() {
		return privateKey;
	}
}
