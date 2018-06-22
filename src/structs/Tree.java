package structs;

/***
 * 
 * 
 * @author Austin O'Donnell
 * @param <E> The type of elements stored in the tree
 */
public interface Tree<E> {
	
	/**
	 * Insert a value into the tree
	 * 
	 * @param value The value to insert into the tree
	 * @return A root node of an updated tree with the value inserted
	 */
	public void insert(E value);
	
	/**
	 * Delete a value from the tree
	 * 
	 * @param value The value to delete from the tree
	 * @return A root node of an updated tree with the value deleted
	 */
	public void delete(E value);
	
	/**
	 * 
	 * @return
	 */
	public int height();
	
	/**
	 * 
	 * @return
	 */
	public int size();
	
	/**
	 * 
	 * @param other
	 * @return
	 */
	public Tree<E> merge(Tree<E> other);
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	public boolean contains(E value);
	
}
