package vmware.bst;

public class BSTMain {

	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree();
		
		int n = 10;
		for(int i = n/2; i < n; i++){
			String value = "" + i;
			bst.insert(i, value);
		}
		
		for(int i = 0; i < n/2; i++){
			String value = "" + i;
			bst.insert(i, value);
		}
		
		bst.delete(n/2 - 1);
		
		Node root = bst.getRoot();
		System.out.println(root.getHeight() + " " + root.getWeight());
		
		for(int i = 0; i < n; i++){
			System.out.println(bst.find(i));
		}
	}

}
