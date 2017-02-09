package vmware.rksm;

public class RabinKarpStringMatching {
	private int base;
	private long[] basePowers;
	private int p;
	private long[] prefixPrecomputationArray;
	private long hash(long x){
		return x % this.p;
	}
	private long hashFromPosWithLength(int x, int l){
		long hashCode = hash(this.prefixPrecomputationArray[x + l - 1] - hash(this.prefixPrecomputationArray[x - 1]*basePowers[l]) + this.p);
		return hashCode;
	}
	
	public RabinKarpStringMatching(String word){
		String lowerCaseWord = word.toLowerCase();
		int len = lowerCaseWord.length();
		this.prefixPrecomputationArray = new long[len + 1];
		this.base = 13;
		this.p = 15485867;
		
		this.basePowers = new long[len  + 1];
		this.basePowers[0] = 1;
		for(int i = 1; i < len + 1; i++){
			this.basePowers[i]  = this.basePowers[i - 1]*this.base;
		}
		
		this.prefixPrecomputationArray[0] = 0;
		
		for(int i = 1; i <= len; i++){
			this.prefixPrecomputationArray[i] = this.hash(this.prefixPrecomputationArray[i-1]*base + (lowerCaseWord.charAt(i-1) - 'a'));
		}
	}
	
	public boolean checkIfCertainSubstringExists(String substring){
		boolean ans = false;
		if(substring.length() > this.prefixPrecomputationArray.length - 1){
			ans = false;
		}else{
			String substringToLowerCase = substring.toLowerCase();
			long substringHash = 0;
			int len = substring.length();
			
			for(int i = 0; i < len; i++){
				substringHash+=hash((substringToLowerCase.charAt(i) - 'a')*basePowers[len - 1 - i]);
			}
			
			int arrLen = this.prefixPrecomputationArray.length;
			long currentHash = 0;
				for(int i = 1; i <= arrLen - len; i++){
					currentHash = this.hashFromPosWithLength(i, len);
					if(substringHash == currentHash){
						ans = true;
						break;
					}
					
				}
			}
		return ans;		
	}
	
	public int getPositionOfCertainSubstringExists(String substring){
		int ans = -1;
		if(substring.length() > this.prefixPrecomputationArray.length - 1){
			ans = -1;
		}else{
			String substringToLowerCase = substring.toLowerCase();
			long substringHash = 0;
			int len = substring.length();
			
			for(int i = 0; i < len; i++){
				substringHash+=hash((substringToLowerCase.charAt(i) - 'a')*basePowers[len - 1 - i]);
			}
			
			int arrLen = this.prefixPrecomputationArray.length;
			for(int i = 1; i < this.prefixPrecomputationArray.length; i++){
				if(i + len > arrLen){
					break;
				}
				long currentHash = this.hashFromPosWithLength(i, len);
				if(substringHash == currentHash){
					ans = i - 1;
				}
			}
		}
		return ans;		
	}
}
