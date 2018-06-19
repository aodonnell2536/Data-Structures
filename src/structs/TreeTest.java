package structs;

public class TreeTest {
	
	public static void main(String[] args) {
		
		AVLTree<Integer> tree = new AVLTree<>();
		tree.insert(5);
		tree.insert(16);
		tree.insert(8);
		tree.insert(1);
		tree.insert(7);
		
		tree.display();
		
		System.out.println("\n" + tree.size());
		
		
	}

}
