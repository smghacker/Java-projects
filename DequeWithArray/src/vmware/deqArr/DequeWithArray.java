package vmware.deqArr;

public class DequeWithArray {
	private int[] arr;
	private int numberOfElements;
	private int sizeOfDeque;
	private int front;
	private int back;
	private boolean full;
	private boolean empty;
	private static int INITIAL_SIZE = 16;
	
	private boolean isFrontEqualToBack(){
		return this.front == this.back;
	}
	
	private void resize(){
		int[] old = new int[this.sizeOfDeque];
		for(int i = 0; i < this.sizeOfDeque; i++){
			old[i] = this.arr[i];
		}
		
		this.sizeOfDeque = 2*this.sizeOfDeque;
		this.arr = new int[this.sizeOfDeque];		
		

		int oldSize = old.length;
		for(int i = this.front; i < oldSize; i++){
			this.arr[i - this.front] = old[i];
		}		
		
		for(int i = 0; i < this.front; i++){
			this.arr[i] = old[i];
		}
		
		this.front = 0;
		this.back = oldSize;
	}
	
	public DequeWithArray(){
		this.sizeOfDeque = INITIAL_SIZE;
		this.arr = new int[INITIAL_SIZE];
		this.front = 0;
		this.back = 0;
		this.full = false;
		this.empty = true;
	}
	
	public void addFirst(int element){
		
		this.front = (this.front - 1 + this.sizeOfDeque) % this.sizeOfDeque;

		if(this.full == true){
			this.resize();
			this.front = (this.front - 1 + this.sizeOfDeque) % this.sizeOfDeque;
		}

		this.full = this.isFrontEqualToBack();
		this.arr[this.front] = element;
		this.empty = false;
	}
	
	public void addLast(int element){
		this.full = this.isFrontEqualToBack();
		
		if(this.full == true){
			this.resize();
		}
		this.arr[this.back] = element;

		this.back = (this.back + 1) % this.sizeOfDeque;
		this.empty = false;
	}
	
	public int peekFirst(){
		if(this.isEmpty()){
			return 0xBABACECA;
		}
		return this.arr[this.front];
	}
	
	public int peekLast(){
		if(this.isEmpty()){
			return 0xBABACECA;
		}
		return this.arr[this.back];
	}
	
	public int pollFirst(){
		if(this.isEmpty()){
			return 0xBABACECA;
		}
		int res = this.arr[this.front];
		this.front = (this.front + 1) % this.sizeOfDeque;
		this.empty = this.isFrontEqualToBack();
		return res;
	}
	
	public int pollLast(){
		if(this.isEmpty()){
			return 0xBABACECA;
		}
		this.back = (this.back - 1 + this.sizeOfDeque) %  this.sizeOfDeque;
		int res = this.arr[this.back];
		this.empty = this.isFrontEqualToBack();
		return res;
	}
	
	public boolean isEmpty(){
		return this.empty;
	}
	
	public int size(){
		return this.numberOfElements;
	}
}
