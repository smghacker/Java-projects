package vmware.hashing;

public class HashTableMain {
	public static void main(String[] args){
		int n = 5;
		HashTable ht = new HashTable();
		for(int i = n; i < 3*n; i++){
			ht.insert(i);
		}
		
		for(int i = n; i < 4*n; i++){
			System.out.println(ht.find(i));
		}
		
		System.out.println(ht.getMaxCollisionDepth());
	}
}
