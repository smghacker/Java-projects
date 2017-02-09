
public class DequeWithIteratorTest {
	public static void main(String args[]){
		DequeWithIterator deq = new DequeWithIterator();
		int n = 10;
		for(int i = 0; i < n; ++i){
			if(i%2 == 0){
				deq.offerFirst(i);
			}else{
				deq.offerLast(i);
			}
		}
		
		DequeWithIterator.Iterator it = deq.begin();
		for(it = deq.begin(); !it.equals(deq.end()); it.next()){
			System.out.println(deq.pollFirst());
		}
	}
}
