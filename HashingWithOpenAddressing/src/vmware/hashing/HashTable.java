package vmware.hashing;

public class HashTable {
	private final int EMPTY_CELL = -2;
	private final double MAX_LOAD_FACTOR = 0.66;
	private final double INITIAL_LOAD_FACTOR = 0.0;
	private final int INITIAL_SIZE = 5;
	private final int INITIAL_NUMBER_OF_USED = 0;
	private final int INITIAL_NUMBER_OF_COLLISIONS = 0;
	
	
	private int primeForFirstHash;
	private int primeForGeneratingStep;
	private int size;
	private int[] table;
	private int numberOfUsed;
	private double loadFactor;
	private int maxCollisionDepth;
	
	private int firstHash(int x){
		return x % this.primeForFirstHash;
	}
	private int stepHash(int x){
		int result = x % this.primeForGeneratingStep;
		if(result == 0){
			result = 1;
		}
		return result;
	}
	
	/*
	 * Returns the previous prime number if the parameter x is equal to 2 it returns 2
	 */
	private int previousPrime(int x){
		int prime = x;
		for(int i = x - 1; i > 1; i--){
			boolean found = true;
			for(int j = 2; j <= Math.sqrt(i); j++){
				if(i%j == 0){
					found = false;
					break;
				}
			}
			
			if(found){
				prime = i;
				break;
			}
		}

		return prime;
	}
	
	/*
	 * Iterate to 2*x because of the Bertrand-Chebyshev's Theorem
	 * http://en.wikipedia.org/wiki/Bertrand%27s_postulate
	 */
	private int nextPrime(int x){		
		int prime = 0;
		for(int i = x + 1; i < 2*x; i++){
			boolean found = true;
			for(int j = 2; j <= Math.sqrt(i); j++){
				if(i%j == 0){
					found = false;
					break;
				}
			}
			
			if(found){
				prime = i;
				break;
			}
		}
		return prime;
	}
	private void resize(){		
		int[] old = new int[this.size];
		for(int i = 0; i < this.size; i++){
			old[i] = this.table[i];
		}		
		
		this.primeForGeneratingStep = this.nextPrime(2*this.size);
		this.primeForFirstHash = this.nextPrime(this.primeForGeneratingStep);
		this.size = this.primeForFirstHash;
		
		this.table = new int[this.size];
		for(int i = 0; i < this.size; i++){
			this.table[i] = EMPTY_CELL;
		}
		
		this.loadFactor = INITIAL_LOAD_FACTOR;
		this.numberOfUsed = INITIAL_NUMBER_OF_USED;
		this.maxCollisionDepth = INITIAL_NUMBER_OF_COLLISIONS;
		
		for(int i = 0; i < old.length; i++){
			if(old[i] != EMPTY_CELL){
				this.insert(old[i]);				
			}
		}
	}
	public HashTable(){
		
		this.primeForFirstHash = INITIAL_SIZE;
		this.primeForGeneratingStep = this.previousPrime(this.primeForFirstHash);
		
		this.size = INITIAL_SIZE;	
		
		this.table = new int[this.size];		
		for(int i = 0; i < this.size; i++){
			this.table[i] = EMPTY_CELL;
		}
		
		this.numberOfUsed = INITIAL_NUMBER_OF_USED;
		this.loadFactor = INITIAL_LOAD_FACTOR;
		this.maxCollisionDepth = INITIAL_NUMBER_OF_COLLISIONS;
	}
	public int capacity(){
		return this.size;
	}
	public boolean isEmpty(){
		return this.size == 0;
	}
	public int getMaxCollisionDepth(){
		return this.maxCollisionDepth;
	}
	public boolean insert(int data){
		int notBigloadFactor = Double.compare(this.loadFactor, MAX_LOAD_FACTOR);
		if(notBigloadFactor >= 0){
			this.resize();
		}
		
		int currentPosition = this.firstHash(data);
		int step = this.stepHash(data);
		boolean notFound = true;
		int currentMaxCollisionDepth = 0;
		while(notFound){
			if(this.table[currentPosition] == EMPTY_CELL){
				this.table[currentPosition] = data;
				this.numberOfUsed++;
				notFound = false;
			}else{
				if(this.table[currentPosition] == data){
					return false;
				}
			}
			currentMaxCollisionDepth++;
			currentPosition = (currentPosition + step) % size;
		}
		
		if(this.maxCollisionDepth < currentMaxCollisionDepth){
			this.maxCollisionDepth = currentMaxCollisionDepth;
		}
		this.loadFactor = (double)this.numberOfUsed/this.size;
		return true;
	}
	public boolean find(int data){
		int currentPosition = this.firstHash(data);
		int step = this.stepHash(data);
		boolean ans = false;
		int numUsed = this.numberOfUsed;
		while(numUsed != 0){
			if(this.table[currentPosition] == EMPTY_CELL){
				ans = false;
				break;
			}else{
				if(this.table[currentPosition] == data){
					ans = true;
					break;
				}
			}
			currentPosition = (currentPosition + step) % size;
			numUsed--;
		}
		return ans;
	}	
}
