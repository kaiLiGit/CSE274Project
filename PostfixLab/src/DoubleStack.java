/**
 * @author:Kai Li 
 * CSE 274
 * Instructor:Dr. Brinkman
 * Sept 24, 2016
 */
import java.lang.IllegalStateException;

public class DoubleStack {
	/**
	 * Constructor
	 * @param max_size     Maximum size of the queue   
	 */
	
	private double[]stack; 
	private int numOfElement; 
	
	public DoubleStack(int maxSize) {
		
		stack = new double[maxSize];
	}
	
	/**
	 * Push a value onto the top of a queue.
	 * @param value   Value to be pushed
	 * @exception  IllegalStateException   Thrown if the stack is full
	 */
	public void push(double value) {
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
	public double pop() {
		if (numOfElement==0) {
			throw new IllegalStateException();
		}
		double value = stack[--numOfElement];
		stack[numOfElement] = 0;
		return value; 
	}
	
	/**
	 * Return the top value on the stack without removing it.
	 * @return Topf value of stacke.
	 * @exception IllegalStateException()  Thrown if if applied to an empty stack.
	 */
	public double peek() {
		if (numOfElement==0) {
			throw new IllegalStateException();
		}
		double value = stack[numOfElement-1];
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

