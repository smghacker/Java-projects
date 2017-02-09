
public class DequeWithIterator {
	private Element begin;
	private Element end;
	private int size;
	
	public DequeWithIterator(){
		begin = null;
		end = null;
		size = 0;
	}
	
	public boolean isEmpty(){
		return this.size == 0;
	}
	
	public int getSize(){
		return this.size;
	}
	
	public void offerLast(int data){
		Element newElement = new Element();
		newElement.data = data;
		newElement.nextElement = null;
		newElement.previousElement = null;
		
		if(this.isEmpty()){
			this.begin = newElement;
			this.end = newElement;
		}
		else{
			this.end.nextElement = newElement;
			this.end = newElement;
			this.end.previousElement = newElement;
		}
		size++;
	}
	
	public void offerFirst(int data){
		Element newElement = new Element();
		newElement.data = data;
		newElement.nextElement = null;
		newElement.previousElement = null;
		
		if(this.isEmpty()){
			this.begin = newElement;
			this.end = newElement;
		}
		else{
			newElement.nextElement = this.begin;
			this.begin = newElement;
		}
		size++;
	}
	
	public int pollLast(){
		
		if(this.isEmpty()){
			return 0xBABACECA;
		}
		
		int data = this.end.data;
		this.end = this.end.previousElement;
		size--;
		
		if(this.isEmpty()){
			this.begin = null;
		}
		
		return data;
	}
	
	public int pollFirst(){
		
		if(this.isEmpty()){
			return 0xBABACECA;
		}
		
		int data = this.begin.data;
		this.begin = this.begin.nextElement;
		
		size--;
		
		if(this.isEmpty()){
			this.end = this.begin;
		}
		
		return data;
	}
	
	public int peekLast(){
		if(this.isEmpty()){
			return 0xBABACECA;
		}
		return this.end.data;
	}
	
	public int peekFirst(){
		if(this.isEmpty()){
			return 0xBABACECA;
		}
		return this.begin.data;
	}
	
	public class Iterator{
		private DequeWithIterator deq;
		private Element position;
		
		public Iterator(){
			this.position = null;
			this.deq = null;
		}
		
		public void setPosition(Element position){
			this.position = position;
		}
		
		public void setDeque(DequeWithIterator deq){
			this.deq = deq;
		}
		
		public int getCurrent(){
			return this.position.data;
		}
		
		public void next(){
			if(this.position != null){
				this.position = this.position.nextElement;
			}else{
				this.position = this.deq.begin;
			}
		}
		
		public void previous(){
			if(this.position != null){
				this.position = this.position.previousElement;
			}else{
				this.position = this.deq.end;
			}
		}
		
		public boolean equals(Iterator otherIterator){
			return this.position == otherIterator.position;
		}
	}
	
	Iterator begin(){
		Iterator iter = new Iterator();
		iter.setPosition(this.begin);
		iter.setDeque(this);
		return iter;
	}
	
	Iterator end(){
		Iterator iter = new Iterator();
		iter.setPosition(this.end);
		iter.setDeque(this);
		return iter;
	}
}
