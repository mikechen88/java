import java.util.*;

/**
 * A class to represent a LIFO data structure
 */

public class Stack<E> {

	// Store the stack items in an array list
	private ArrayList<E> items;

	/**
	 * Constructs an empty stack
	 */
	public Stack() {
		items = new ArrayList<E>();

	}

	/**
	 * Is this stack empty?
	 * 
	 * @return true if this stack is empty, or false otherwise
	 */
	public boolean isEmpty() {
		return items.isEmpty();

	}

	/**
	 * Pushes an object on top of this stack
	 * 
	 * @param item
	 *            the object to push on this stack
	 * @throws NullPointerException
	 *             if the object to push on this stack is null
	 */
	public void push(E item) {
		if (item == null) {
			throw new NullPointerException();
		}
		items.add(item);
	}

	/**
	 * Gets and removes the object at the top of this stack
	 * 
	 * @returns the object at the top of this stack
	 * @throws EmptyStackException
	 *             if this stack is empty
	 */
	public E pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return items.remove(items.size() - 1);
	}

	/**
	 * Gets the object at the top of this stack
	 * 
	 * @throws EmptyStackException
	 *             if this stack is empty
	 */
	public E top() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return items.get(items.size() - 1);
	}
}
