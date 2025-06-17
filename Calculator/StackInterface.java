import java.util.EmptyStackException;

public interface StackInterface<T> extends Iterable<T> {
	/**
	 * Push an element to the top of the Stack
	 */
	public void push(T val);

	/**
	 * Pop an element from the top of the stack
	 * 
	 * @return the element popped
	 * @throws EmptyStackException if the stack is empty
	 */
	public T pop() throws EmptyStackException;

	/**
	 * 
	 * @return the element at the top of the stack
	 * @throws EmptyStackException if the stack is empty
	 */
	public T top() throws EmptyStackException;

	/**
	 * Check if the stack is empty
	 * 
	 * @return true if stack is empty, false otherwise
	 */
	public boolean isEmpty();

	/**
	 * String representation of the stack
	 * 
	 * @return a string of elements in the stack
	 */
	public String toString();
}
