package structs;

import java.util.InputMismatchException;

public abstract class BinarySearchTree<E extends Comparable<E>> {

	/*
	 * Fields
	 */
	protected Node root;		// The root node of the tree
	protected int size;			// The amount of nodes currently in the tree
	
	/**
	 * Wrapper function to insert the given value into the tree
	 * starting at the root
	 * 
	 * @param value
	 */
	public void insert(E value) {
		root = insert(root, value);
		size += 1;
	}
	
	/**
	 * Insert <code>value</code> into the subtree rooted at <code>node</code>
	 * 
	 * @param node The root of the subtree for an insertion
	 * @param value The value to insert into the subtree
	 * @return The updated root of the subtree with the element inserted
	 */
	protected abstract Node insert(Node node, E value);
	
	/**
	 * Wrapper function to delete the given value from the tree
	 * starting at the root
	 * 
	 * @param value The value to search and remove
	 * @throws IllegalStateException The tree is empty when the method is called to remove an element
	 */
	public void delete(E value) {
		
		if (size == 0)
			throw new IllegalStateException("Cannot delete an element from an empty tree.");
		
		root = delete(root, value);
		size -= 1;
		
	}
	
	/**
	 * Deletes the given value from the subtree rooted at <code>code</code>
	 * Updates heights on the way back up and rotates as needed
	 * to maintain the tree in AVL form
	 * 
	 * @param node The root of the subtree to remove the value from
	 * @param value The value to remove from the tree
	 * @return The updated root of the subtree with the given value removed
	 */
	protected abstract Node delete(Node node, E value);
	
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
	
	/**
	 * Utility function to obtain a usable height 
	 * of the subtree rooted at the parameter node
	 * 
	 * @param n The root node of the subtree in question
	 * @return The height of the subtree, or 0 if the root is null
	 */
	protected int height(Node node) {
		
		if (node == null) return 0;
		return node.height;
		
	}
	
	/**
	 * Utility function to obtain a usable balance factor 
	 * of the subtree rooted at the parameter node
	 * 
	 * @param n The root node of the subtree in question
	 * @return The balance factor of the subtree (height of left - height of right),
	 * 		   or 0 if the root is null
	 */
	protected int balance(Node node) {
		
		if (node == null) return 0;
		return height(node.left) - height(node.right);
		
	}
	
	/**
	 * Accessor method for the size of the tree
	 * 
	 * @return The size of the tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Whether or not the tree is empty
	 * 
	 * @return A boolean value signifying if the tree is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Displays the entire tree to the terminal in the
	 * natural ordering of the elements
	 */
	public void display() {
		display(root);
	}
	
	/**
	 * Displays the elements of the tree rooted at <code>node</code>
	 * to the terminal in the natural ordering of the elements
	 * 
	 * @param node The root node of the subtree being displayed
	 */
	private void display(Node node) {
		
		if (node == null) return;
		
		display(node.left);
		System.out.println(node.value);
		display(node.right);
		
	}
	
	/**
	 * Used to store relevant information
	 * to a single node within a binary search tree
	 */
	protected class Node {
		
		E value;
		int height;
		Node left, right;
		
		Node(E val) {
			value = val;
			height = 1;
			left = right = null;
		}
		
	}
	
}
