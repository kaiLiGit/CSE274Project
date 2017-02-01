/**
 * @author:Kai Li 
 * CSE 274
 * Instructor:Dr. Brinkman
 * Sept 24, 2016
 */
import java.lang.IllegalStateException;

public class IntStack {
	/**
	 * Constructor
	 * @param max_size     Maximum size of the queue   
	 */
	
	private int[]stack; 
	private int numOfElement; 
	
	public IntStack(int maxSize) {
		
		stack = new int[maxSize];
	}
	
	/**
	 * Push a value onto the top of a queue.
	 * @param value   Value to be pushed
	 * @exception  IllegalStateException   Thrown if the stack is full
	 */
	public void push(int value) {
		if (numOfElement==stack.length) {
			throw new IllegalStateException();
		}
		stack[numOfElement++] = value; 
	}
	
	/**
	 * Pop a value from the stack: return the value, remove from stack.
	 * @return Top values of stack
	 * @exception IllegalStateException   Thrown if you pop from an empty stack.
	 */
	public int pop() {
		if (numOfElement==0) {
			throw new IllegalStateException();
		}
		int value = stack[--numOfElement];
		stack[numOfElement] = 0;
		return value; 
	}
	
	/**
	 * Return the top value on the stack without removing it.
	 * @return Topf value of stacke.
	 * @exception IllegalStateException()  Thrown if if applied to an empty stack.
	 */
	public int peek() {
		if (numOfElement==0) {
			throw new IllegalStateException();
		}
		int value = stack[numOfElement-1];
		return value; 
	}
	
	/**
	 * Determine if the stack is empty.
	 * @return True if empty.
	 */
	public boolean isEmpty() {
		return numOfElement==0; 
	}
	
	/**
	 * Determine if the stack is at capacity.
	 * @return True if at capacity.
	 */
	public boolean isFull() {
		return numOfElement == stack.length;
	}
	
	/**
	 * Number of elements in the stack.
	 * @return Number of elements in the stack.
	 */
	public int size() {
		return numOfElement; 
	}
}
