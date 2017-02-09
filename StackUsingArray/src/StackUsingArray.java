

public class StackUsingArray {
	
	public static void main(String[] args){
		int size = 4;
		
		Stacky s = new Stacky(size);
		for(int  i = 0; i < 2*size; i++){
			s.push(i);
		}
		
		for(int  i = 0; i < 2*size; i++){
			System.out.println(s.pop());
		}
	}
}
