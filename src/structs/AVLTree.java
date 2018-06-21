package structs;

import java.util.InputMismatchException;

/***
 * Simple AVL Tree implementation in Java.
 * 
 * @author Austin O'Donnell
 *
 * @param <E> The type of element held by the tree.
 * 			  Must implement java.util.Comparable<E>.
 */
public class AVLTree<E extends Comparable<E>> 
			 					extends BinarySearchTree<E> {
	
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
	protected Node insert(Node node, 
						E value) {
		
		node = super.insert(node, value);
		return updateNode(node, value);
				
	}
	
	/**
	 * 
	 * 
	 * @param node
	 * @param value
	 * @return
	 */
	protected Node delete(Node node, 
						E value) {
		
		node = super.delete(node, value);
		return (node == null) ? null : updateNode(node, value);
		
	}
	
	/**
	 * Updates the node in question by changing the height
	 * and rotating as needed to stay in AVL form
	 * 
	 * @param node The node to update
	 * @param value The value of the node recently changed in the tree
	 * @return The updated node
	 */
	private Node updateNode(Node node, 
						E value) {
		
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
	 * Performs a right rotation with the parameter node as the pivot
	 * 
	 * @param node The pivot node of the rotation
	 * @return The updated node at the same position of the pivot node
	 */
	protected Node rightRotate(Node node) {
		
		if (node.left == null)
			throw new InputMismatchException("Cannot right rotate node when it has no left child.");
		
		Node temp1 = node.left;
		Node temp2 = temp1.right;
		
		node.left = temp2;
		temp1.right = node;
		
		node.height = 1 + Math.max(height(node.left), height(node.right));
		temp1.height = 1 + Math.max(height(temp1.left), height(temp1.right));
		
		return temp1;
		
	}
	
	/**
	 * Performs a left rotation with the parameter node as the pivot
	 * 
	 * @param node The pivot node of the rotation
	 * @return The updated node at the same position of the pivot node
	 */
	protected Node leftRotate(Node node) {
		
		if (node.right == null)
			throw new InputMismatchException("Cannot left rotate node when it has no right child.");
		
		Node temp1 = node.right;
		Node temp2 = temp1.left;
		
		node.right = temp2;
		temp1.left = node;
		
		node.height = Math.max(height(node.left), height(node.right));
		temp1.height = 1 + Math.max(height(temp1.left), height(temp1.right));
		
		return temp1;
		
	}
	
	private int balance(Node node) {
		return (node == null) ? 0 : height(node.left) - height(node.right);
	}
	
	public static void main(String[] args) {
		
		AVLTree<Integer> tree = new AVLTree<>();
		tree.insert(5);
		tree.insert(3);
		tree.insert(18);
		tree.delete(5);
		tree.display();
		
	}
	
}
