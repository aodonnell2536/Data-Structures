package structs;

import java.util.Comparator;
import java.util.List;

public class BinarySearchTree<E extends Comparable<E>> 
								extends BinaryTree<E> {

	/**
	 * 
	 */
	protected Comparator<E> comparator;
	
	/**
	 * 
	 */
	public BinarySearchTree() {
		size = 0;
		root = null;
		this.comparator = (o1, o2) -> o1.compareTo(o2);
	}
	
	/**
	 * 
	 * @param comparator
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		size = 0;
		root = null;
		this.comparator = comparator;
	}
	
	/**
	 * 
	 * @param elements
	 */
	public BinarySearchTree(List<E> elements) {
		
		size = 0;
		root = null;
		this.comparator = (o1, o2) -> o1.compareTo(o2);
		
		for (E val : elements)
			root = insert(root, val);
		
	}
	
	/**
	 * 
	 * @param elements
	 * @param comparator
	 */
	public BinarySearchTree(List<E> elements, Comparator<E> comparator) {
		
		size = 0;
		root = null;
		this.comparator = comparator;
		
		for (E val : elements)
			insert(val);
		
	}
	
	/**
	 * 
	 */
	@Override
	public void insert(E value) {
		root = insert(root, value);
		size += 1;
	}
	
	/**
	 * 
	 */
	@Override
	protected Node insert(Node node, E value) {
		
		if (node == null) 
			return new Node(value);
		
		if (comparator.compare(value, node.value) < 0)
			node.left =  insert(node.left, value);
		else if (comparator.compare(value, node.value) > 0)
			node.right = insert(node.right, value);
		
		return node;
		
	}

	/**
	 * 
	 */
	@Override
	public void delete(E value) {
		root = delete(root, value);
		size -= 1;
	}
	
	/**
	 * 
	 */
	@Override
	protected Node delete(Node node, E value) {

		if (value.compareTo(node.value) < 0)
			node.left = delete(node.left, value);
		else if (value.compareTo(node.value) > 0)
			node.right = delete(node.right, value);
		else if (node.right == null && node.left == null)
			return null;
		else if (node.right != null) {
			
			Node succ = node.right;
			while (succ.left != null)
				succ = succ.left;
			
			node.value = succ.value;
			node.right = delete(node.right, succ.value);
			
		}
		else if (node.left != null) {
			
			Node succ = node.left;
			while (succ.right != null)
				succ = succ.right;
			
			node.value = succ.value;
			node.left = delete(node.left, succ.value);
			
		}
		
		return node;
		
	}
	
}
