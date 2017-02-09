
public class StackUsingLinkedListTest {
	public static void main(String[] args){
		StackUsingLinkedList stack = new StackUsingLinkedList();
		int n = 10;
		for(int i = 0; i < n; i++){
			stack.push(i);
			System.out.println(stack.getSize());
		}
		System.out.println(stack.isEmpty());
		
		int top = stack.peek();
		System.out.println("On top " + top);
		for(int i = 0; i < n; i++){
			stack.pop();
			System.out.println(stack.getSize());
		}
		
		System.out.println(stack.isEmpty());
	}
}
