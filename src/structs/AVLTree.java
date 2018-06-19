package structs;

import java.util.InputMismatchException;

/***
 * Simple AVL Tree implementation in Java.
 * 
 * @author Austin O'Donnell
 *
 * @param <E> The type of element held by the tree.
 * 			  Must implement <code>java.util.Comparable<E>.
 */
public class AVLTree<E extends Comparable<E>> {

	private class Node {
		
		E value;
		int height;
		Node left, right;
		
		Node(E val) {
			value = val;
			height = 1;
			left = right = null;
		}
		
	}
	
	/*
	 * Fields
	 */
	private Node root;
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
	public void insert(E value) { 
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
	private Node insert(Node node, E value) {
		
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
	 * @param value The value to be deleted from the tree
	 */
	public void delete(E value) {
		root = delete(root, value);
		size -= 1;
	}
	
	private Node delete(Node node, 
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
	
	/**
	 * Performs a right rotation with the parameter node as the pivot
	 * @param node The pivot node of the rotation
	 * @return The updated node at the same position of the pivot node
	 */
	private Node rightRotate(Node node) {
		
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
	 * @param node The pivot node of the rotation
	 * @return The updated node at the same position of the pivot node
	 */
	private Node leftRotate(Node node) {
		
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
	private void display(Node node) {
		
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
	private int height(Node n) {
		if (n == null) return 0;
		return n.height;
	}
	
	/**
	 * Obtains the balance factor of the subtree rooted at the parameter node
	 * @param n The root node of the subtree in question
	 * @return The balance factor of the subtree (height of left - height of right), or 0 if the root is null
	 */
	private int balance(Node n) {
		if (n == null) return 0;
		return height(n.left) - height(n.right);
	}
	
	public void preorder() {
		preorder(root);
	}
	
	private void preorder(Node node) {
		
		if (node == null) return;
		
		System.out.println(node.value);
		preorder(node.left);
		preorder(node.right);
		
	}
	
	public int size() { return size; }
	
}
