
public class StackUsingLinkedList {
	public Element top;
	public int size;
	public StackUsingLinkedList(){
		this.size = 0;
		this.top = null;
	}
	public void push(int newData){
		Element newElem;
		newElem = new Element();
		newElem.data = newData;
		newElem.nextElement = this.top;
		this.top = newElem;
		size++;
	}
	
	public int pop(){
		if(this.isEmpty()){
			return 0xBABACECA;
		}
		else{
			int data = this.top.data;
			this.top = this.top.nextElement;
			size--;
			return data;
		}
	}
	
	public int peek(){
		if(this.isEmpty()){
			return 0xBABACECA;
		}
		else{
			return this.top.data;
		}
	}
	
	public boolean isEmpty(){
		return this.size == 0;
	}
	
	public int getSize(){
		return this.size;
	}
}
