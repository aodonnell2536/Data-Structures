package structs;

import java.util.InputMismatchException;

public class AVLTree<T extends Comparable<T>> {

	/**
	 * 
	 * @param <T> The type of 
	 */
	private class Node<T> {
		
		T value;
		int height;
		Node<T> left, right;
		
		Node(T val) {
			value = val;
			height = 1;
			left = right = null;
		}
		
	}
	
	/*
	 * Fields
	 */
	private Node<T> root;
	private int size;
	
	/*
	 * Constructor
	 */
	public AVLTree() { 
		this.root = null; 
		size = 0; 
	}
	
	/**
	 * Wrapper function to insert a value into the tree
	 * @param value The value to be added to the tree
	 */
	public void insert(T value) { 
		root = insert(root, value); 
		size += 1;
	}
	
	/**
	 * Inner function to insert a value into the tree recursively. Updates the
	 * heights on the way back up and rotates nodes if needed to
	 * maintain the tree in AVL form
	 * @param node The current node in the recursive sequence. Originally passed as the root node.
	 * @param value The value to be added to the tree
	 * @return The updated (sub)tree after the value has been added, heights have
	 * been updated, and nodes have been rotated
	 */
	private Node<T> insert(Node<T> node, T value) {
		
		if (node == null)
			return new Node<>(value);
		
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
	
	private void delete(T value) {
	
		delete(root, value);
		
	}
	
	private Node<T> delete(Node<T> node, T value) {
		
		if (value.compareTo(node.value) < 0)
			node.left = delete(node.left, value);
		else if (value.compareTo(node.value) > 0)
			node.right = delete(node.right, value);
		else
			removeAndReplace(node);
		
	}
	
	private Node<T> removeAndReplace(Node<T> node) {
		
		if (node.left == null && node.right == null)		// No children
			return null;
		
		// Check for successor node switch first, then predecessor
		else if (node.right != null) {
			
			// Finding the successor node
			Node<T> successor = node.right;
			while (successor.left != null)
				successor = successor.left;
			
			
			
		}
		
	}
	
	/**
	 * Performs a right rotation with the parameter node as the pivot
	 * @param node The pivot node of the rotation
	 * @return The updated node at the same position of the pivot node
	 */
	private Node<T> rightRotate(Node<T> node) {
		
		if (node.left == null)
			throw new InputMismatchException("Cannot right rotate node when it has no left child.");
		
		Node<T> temp1 = node.left;
		Node<T> temp2 = temp1.right;
		
		node.left = temp2;
		temp1.right = node;
		
		node.height = 1 + Math.max(height(node.left), height(node.right));
		temp1.height = 1 + Math.max(height(temp1.left), height(temp1.right));
		
		return temp1;
		
	}
	
	/**
	 * Performs a left rotation with the parameter node as the pivot
	 * @param node The pivot node of the rotation
	 * @return The updated node at the same position of the pivot node
	 */
	private Node<T> leftRotate(Node<T> node) {
		
		if (node.right == null)
			throw new InputMismatchException("Cannot left rotate node when it has no right child.");
		
		Node<T> temp1 = node.right;
		Node<T> temp2 = temp1.left;
		
		node.right = temp2;
		temp1.left = node;
		
		node.height = Math.max(height(node.left), height(node.right));
		temp1.height = 1 + Math.max(height(temp1.left), height(temp1.right));
		
		return temp1;
		
	}

	/**
	 * Checks to see if the tree is empty
	 * @return Whether or not the tree is empty
	 */
	public boolean isEmpty() {
		return root == null;
	}
	
	/**
	 * Wrapper function to display the elements of the tree in order
	 */
	public void display() {
		display(root);
	}
	
	/**
	 * Inner function to display the elements of the tree in order
	 * @param node The current node in the recursive sequence. Originally passed as the root node.
	 */
	private void display(Node<T> node) {
		
		if (node == null) return;
		
		display(node.left);
		System.out.println(node.value);
		display(node.right);
		
	}
	
	/**
	 * Obtains the height of the subtree rooted at the parameter node
	 * @param n The root node of the subtree in question
	 * @return The height of the subtree, or 0 if the root is null
	 */
	private int height(Node<T> n) {
		if (n == null) return 0;
		return n.height;
	}
	
	/**
	 * Obtains the balance factor of the subtree rooted at the parameter node
	 * @param n The root node of the subtree in question
	 * @return The balance factor of the subtree (height of left - height of right), or 0 if the root is null
	 */
	private int balance(Node<T> n) {
		if (n == null) return 0;
		return height(n.left) - height(n.right);
	}
	
	public int size() { return size; }
	
	
}
