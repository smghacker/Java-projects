package vmware.bst;

public class Node {
	private int key;
	private String value;
	private Node left;
	private Node right;
	private Node parent;
	private int height;
	private int weight;
	
	public Node(){
		this.key = 0;
		this.value = null;
		this.left = null;
		this.right = null;
		this.parent = null;
		this.height = 0;
		this.weight = 1;
	}
	public Node(int key, String value){
		this.key = key;
		this.value = value;
		this.left = null;
		this.right = null;
		this.parent = null;
		this.height = 0;
		this.weight = 1;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
}
