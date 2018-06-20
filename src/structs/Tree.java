package structs;

/***
 * 
 * @author Austin O'Donnell
 * @param <E> The type of elements stored in the tree
 */
public abstract class Tree<E> {

	protected int degree;
	
	/**
	 * Insert a value into the tree
	 * 
	 * @param value The value to insert into the tree
	 * @return A root node of an updated tree with the value inserted
	 */
	public abstract Node insert(E value);
	
	/**
	 * Delete a value from the tree
	 * 
	 * @param value The value to delete from the tree
	 * @return A root node of an updated tree with the value deleted
	 */
	public abstract Node delete(E value);
	
	/**
	 * A data type holding both a value, and a list of references to the
	 * children nodes of the Node.
	 * 
	 * List of children is implemented using weak typing.
	 */
	protected class Node {
		
		E value;
		Object[] children;
		
		public Node(E value) {
			this.value = value;
			children = new Object[degree];
		}
		
		/**
		 * Retrieves the ith child of the tree
		 * 
		 * @param i The number child to retrieve
		 * @return The root of the subtree that is the ith child of the tree
		 */
		@SuppressWarnings("unchecked")
		public Node get(int i) {
			return (Node) children[i];
		}
		
	}
	
}
