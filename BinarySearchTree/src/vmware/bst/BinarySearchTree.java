package vmware.bst;

public class BinarySearchTree {
	private Node root;	
	private int size;
	
	/*
	 * Function that return the node with the minimal 
	 * key from the right subtree
	 */
	private Node minOnRight(Node root){
		if(root.getLeft() != null){
			return minOnRight(root.getLeft());
		}else{
			return root;
		}
	}
	private Node insertFromRoot(Node root, int key, String value){
		Node newNode = new Node();
		if(root == null){
			return new Node(key, value);
		}else if(key < root.getKey()){
			newNode = this.insertFromRoot(root.getLeft(), key, value);
			newNode.setParent(root);
			root.setLeft(newNode);
		}else if(key > root.getKey()){
			newNode = this.insertFromRoot(root.getRight(), key, value);
			newNode.setParent(root);
			root.setRight(newNode);
		}

		this.updateHeightAndWeight(root);
		return root;
	}
	private void updateHeightAndWeight(Node root) {
		int leftHeight = 0;
		int leftWeight = 0;		
		Node leftNode = root.getLeft();
		if(leftNode != null){
			leftHeight = leftNode.getHeight();
			leftWeight = leftNode.getWeight();
		}

		int rightHeight = 0;
		int rightWeight = 0;
		Node rightNode = root.getRight();
		if(rightNode != null){
			rightHeight = rightNode.getHeight();
			rightWeight = rightNode.getWeight();
		}
		
		int newHeight = Math.max(leftHeight, rightHeight) + 1;
		int newWeight = leftWeight + rightWeight + 1;
		root.setHeight(newHeight);
		root.setWeight(newWeight);
	}
	private String findFromRoot(Node root, int key){
		String value = null;
		if(root == null){
			return value;
		}
		if(root.getKey() == key){
			return root.getValue();
		}else if(key < root.getKey()){
			value = this.findFromRoot(root.getLeft(), key);
		}else{
			value = this.findFromRoot(root.getRight(), key);
		}
	
		return value;
	}
	/*
	 * We will use this method when we should delete a node which
	 * has exactly one child
	 */
	private void replaceNodeInParent(Node parent, Node child){
		parent.setKey(child.getKey());
		parent.setValue(child.getValue());
		if(parent.getLeft().getKey() == child.getKey()){
			parent.setLeft(null);
		}else{
			parent.setRight(null);
		}
	}
	private Node deleteFromRoot(Node root, int key){
		Node currentNode = root;
		int currentNodeKey = root.getKey();
		
		Node newNode = new Node();
		if(key < currentNodeKey){
			newNode = deleteFromRoot(currentNode.getLeft(), key);
			currentNode.setLeft(newNode);
			this.updateHeightAndWeight(currentNode);
		}else if(key > currentNodeKey){
			newNode = deleteFromRoot(currentNode.getRight(), key);
			currentNode.setRight(newNode);
			this.updateHeightAndWeight(currentNode);
		}		
		
		if(currentNode == null){
			return null;
		}else if(currentNodeKey == key){
			Node leftNode = currentNode.getLeft();
			Node rightNode = currentNode.getRight();
			if(leftNode != null && rightNode == null){
				this.replaceNodeInParent(currentNode, currentNode.getLeft());
			}else if(leftNode == null && rightNode != null){
				this.replaceNodeInParent(currentNode, currentNode.getRight());
			}else if(leftNode == null && rightNode == null){
				currentNode = null;
			}else{
				Node minInRight = this.minOnRight(currentNode.getRight());
				currentNode.setKey(minInRight.getKey());
				currentNode.setValue(minInRight.getValue());
				currentNode.setRight(deleteFromRoot(minInRight, minInRight.getKey()));
			}
		}
		
		return currentNode;
	}
	
	public BinarySearchTree(){
		this.root = null;
		this.size = 0;
	}
	public Node getRoot(){
		return this.root;
	}	
	public int getSize(){
		return this.size;
	}	
	public boolean isEmpty(){
		return this.size == 0;
	}
	public boolean insert(int key, String value){
		if(this.find(key) != null){			
			return false;
		}else{
			this.root = this.insertFromRoot(this.root, key, value);
			this.size++;
			return true;
		}
		
	}
	public String find(int key){
		String result = this.findFromRoot(this.root, key);
		return result;
	}
	public String delete(int key){
		String result = this.find(key);
		if(result != null){
			this.root = deleteFromRoot(this.root, key);
			this.size--;			
		}
		return result;
	}
}
