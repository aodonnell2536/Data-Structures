package structs;

public abstract class BinaryTree<E>
							extends Tree<E> {
	
	/**
	 * 
	 * 
	 * @author Austin O'Donnell
	 */
	protected class Node {
		
		E value;
		Node left, right;
		Node parent;
		int height;
		
		public Node(E value) {
			this.value = value;
			height = 1;
		}
		
	}

	/**
	 * 
	 */
	protected Node root;
	
	/**
	 * 
	 */
	protected int size;
	
	/**
	 * 
	 * 
	 * @param node
	 * @param value
	 * @return
	 */
	protected abstract Node insert(Node node, E value);
	
	/**
	 * 
	 * 
	 * @param value
	 */
	public abstract void delete(E value);
	
	/**
	 * 
	 * 
	 * @param node
	 * @param value
	 * @return
	 */
	protected abstract Node delete(Node node, E value);
	
	/**
	 * 
	 */
	protected void display() {
		display(root);
	}
	
	/**
	 * 
	 * @param node
	 */
	private void display(Node node) {
		if (node == null) return;
		display(node.left);
		System.out.println(node.value);
		display(node.right);
	}
	
	/**
	 * 
	 * @param node
	 * @return
	 */
	protected int height(Node node) {
		return (node == null) ? 0 : node.height;
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	public boolean contains(E value) {
		return contains(root, value);
	}
	
	/**
	 * 
	 * @param node
	 * @param value
	 * @return
	 */
	private boolean contains(Node node, E value) {
		
		if (node == null)
			return false;
		
		if (node.value == value) 
			return true;
		
		return contains(node.left, value) || contains(node.right, value);
		
	}
	
}
