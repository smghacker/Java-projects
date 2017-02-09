package vmware.deqArr;

public class DequeWithArrayMain {
	public static void main(String[] args){
		DequeWithArray deq = new DequeWithArray();
		int n = 60000;
		long begin = System.nanoTime();
		for(int i = 0; i < n; i++){
			deq.addFirst(i);
		}
		long end = System.nanoTime();
		
		System.out.println(end - begin);
		
		
	}
}
