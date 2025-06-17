import java.util.EmptyStackException;
import java.util.Iterator;

public class MyStack<T> implements StackInterface<T> {

	private static int STACK_SIZE = 16;

	private T[] arr;
	private int size;

	@SuppressWarnings("unchecked")
	public MyStack() {
		this.arr = (T[]) new Object[STACK_SIZE];
		this.size = 0;
	}

	// Iterator for the class
	private class StackIterator implements Iterator<T> {
		private int count;

		public StackIterator() {
			this.count = 0;
		}

		// Check if there are more elements in the stack
		public boolean hasNext() {
			return this.count < size;
		}

		// Return the next element in the stack
		public T next() {
			T val = arr[count];
			this.count += 1;
			return val;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new StackIterator();
	}

	public int size() {
		return this.size;
	}

	// Get the top element of the stack
	@Override
	public T top() throws EmptyStackException {
		// Throw an exception if the stack is empty
		if (this.size == 0) {
			throw new EmptyStackException();
		}
		return this.arr[this.size - 1];
	}

	// Check if size is zero
	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public void push(T val) {
		// TODO: Add resize functionality

		this.arr[this.size] = val;
		// Update size after push
		this.size += 1;

	}

	@Override
	public T pop() throws EmptyStackException {
		// TODO: Add resize functionality
		if (this.size == 0) {
			throw new EmptyStackException();
		}

		T retVal = this.arr[size - 1];
		// Update size
		this.size = this.size - 1;
		// Set popped entry to null
		this.arr[size] = null;
		// Return the popped value
		return retVal;
	}

	// Return a string represenation of the object
	@Override
	public String toString() {
		// Populate an array of String with elements of stack
		String[] string_arr = new String[this.size];
		for (int i = 0; i < this.size; i++) {
			string_arr[i] = this.arr[i].toString();
		}

		return String.format("[%s]", String.join(", ", string_arr));
	}

	public static void main(String[] args) {
		MyStack<String> s = new MyStack<>();
		s.push("Kalika");
		s.push("shOrkie");
		s.push("Mayu");
		s.push("Calli");
		s.push("Dopa");
		System.out.println(s);
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s);
	}
}
