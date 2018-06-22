package structs;

public abstract class BinaryTree<E>
							implements Tree<E> {
	
	protected Object[] elements;
	
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
	
	/**
	 * 
	 */
	protected void extend() {
		
		Object[] nextArray = new Object[ 2 ^ (height() + 1) - 1 ];
		for (int i = 0; i < elements.length; i++)
			nextArray[i] = elements[i];
		elements = nextArray;
		
	}

	public int height() {
		
		return log2(elements.length + 1) - 1;
		
	}
	
	/**
	 * Calculates the floor of the binary logarithm of an integer
	 * 
	 * @author User x4u on StackOverflow - https://stackoverflow.com/questions/3305059/how-do-you-calculate-log-base-2-in-java-for-integers
	 * @param bits
	 * @return
	 */
	private int log2(int bits) {
		
		int log = 0;
	    if ( ( bits & 0xffff0000 ) != 0 ) { bits >>>= 16; log = 16; }
	    if ( bits >= 256 ) { bits >>>= 8; log += 8; }
	    if ( bits >= 16  ) { bits >>>= 4; log += 4; }
	    if ( bits >= 4   ) { bits >>>= 2; log += 2; }
	    return log + ( bits >>> 1 );
		
	}
	
}
