package structs;

/***
 * Simple AVL Tree implementation in Java.
 * 
 * @author Austin O'Donnell
 *
 * @param <E> The type of element held by the tree.
 * 			  Must implement java.util.Comparable<E>.
 */
public class AVLTree<E extends Comparable<E>> extends BinarySearchTree<E> {
	
	/*
	 * Constructor
	 */
	public AVLTree() { 
		this.root = null; 
		size = 0; 
	}
	
	/**
	 * Inner function to insert a value into the tree recursively. Updates the
	 * heights on the way back up and rotates nodes if needed to
	 * maintain the tree in AVL form
	 * 
	 * @param node The current node in the recursive sequence. Originally passed as the root node.
	 * @param value The value to be added to the tree
	 * @return The updated (sub)tree after the value has been added, heights have
	 * been updated, and nodes have been rotated
	 */
	protected Node insert(Node node, E value) {
		
		if (node == null)
			return new Node(value);
		
		if (value.compareTo(node.value) < 0) 
			node.left = insert(node.left, value);
		else if (value.compareTo(node.value) > 0)
			node.right = insert(node.right, value);
		else // Duplicate value
			return node;
		
		node.height = 1 + Math.max(height(node.left), height(node.right));
		
		int balanceFactor = balance(node);
		
		// Left heavy
		if (balanceFactor > 1) {
			if (value.compareTo(node.left.value) < 0)	// Left-Left
				return rightRotate(node);
			else {	// Left-Right
				node.left = leftRotate(node.left);
				return rightRotate(node);
			}	
		}
		else if (balanceFactor < -1) {
			if (value.compareTo(node.right.value) > 0) // Right-Right
				return leftRotate(node);
			else {
				node.right = rightRotate(node.right);
				return leftRotate(node);
			}
		}
		
		return node;
	}
	
	/**
	 * 
	 * @param node
	 * @param value
	 * @return
	 */
	protected Node delete(Node node, 
						E value) {
		
		if (value.compareTo(node.value) < 0)
			node.left = delete(node.left, value);
		else if (value.compareTo(node.value) > 0)
			node.right = delete(node.right, value);
		else if (node.left != null && node.right != null) {
			
			Node succ = node.right;
			while (succ.left != null)
				succ = succ.left;
			
			node.value = succ.value;
			node.right = delete(node.right, succ.value);
			
		}
		else if (node.left != null)
			return node.left;
		else
			return null;
		
		node.height = 1 + Math.max(height(node.left), height(node.right));
		
		int balanceFactor = balance(node);
		
		// Left heavy
		if (balanceFactor > 1) {
			if (value.compareTo(node.left.value) < 0)	// Left-Left
				return rightRotate(node);
			else {	// Left-Right
				node.left = leftRotate(node.left);
				return rightRotate(node);
			}	
		}
		else if (balanceFactor < -1) {
			if (value.compareTo(node.right.value) > 0) // Right-Right
				return leftRotate(node);
			else {
				node.right = rightRotate(node.right);
				return leftRotate(node);
			}
		}
		
		return node;
		
	}
	
}
