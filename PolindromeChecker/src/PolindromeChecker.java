import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
public class PolindromeChecker {
	private Stack<Character> reversed;
	private Queue<Character> straight;
	
	public PolindromeChecker(){
		this.reversed = new Stack<Character>();
		this.straight = new LinkedList<Character>();
	}
	
	
	public boolean checkWord(String initWord){
		String word = initWord.toLowerCase();
		int size = word.length();
		boolean ans = true;
		for(int i = 0; i < size; i++){
			this.reversed.push(word.charAt(i));
			this.straight.add(word.charAt(i));
		}
		
		for(int i = 0; i < size; i++){
			char fromReversed = this.reversed.pop();
			char fromStraight = this.straight.poll();
			if(fromReversed != fromStraight){
				ans = false;
				break;
			}
		}
		
		while(!this.reversed.isEmpty()){
			reversed.pop();
		}
		
		while(!this.straight.isEmpty()){
			straight.poll();
		}
		
		return ans;
	}
}
