
class Stacky{
			int [] arr;
			public int top;
			public int size;
			public Stacky(int size){
				this.arr = new int[size];
				this.top = -1;
				this.size = size;
			}
			
			public int getTop(){
				return this.arr[this.top];
			}
			
			public int getSize(){
				return this.size;
			}
			
			public boolean isEmpty(){
				return (this.top == -1);
			}
			
			public void push(int newElem){
				//check if it is at the end
				this.top++;
				if(this.top == size){
					int[] old = new int[size];
					for(int i = 0; i < size; i++){
						old[i] = this.arr[i];
					}
					
					this.arr = new int[2*size];
					for(int i = 0; i < size; i++){
						this.arr[i] = old[i];
					}
					
				}					
				
				this.arr[this.top] = newElem;
			}
			
			public int pop(){
				if(this.isEmpty()){
					return 0xBABACECA;
				}
				else{
					int ans = this.arr[this.top];
					this.top--;
					return ans;
				}
			}
	}
