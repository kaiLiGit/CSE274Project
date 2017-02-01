package data_structures;

import java.lang.IndexOutOfBoundsException;

/**
 * CSE 274
 * StringArray: An implementation of a dynamic-sized string array.
 * 
 * @author : Kai Li
 * 
 * September 14, 2016
 *
 */

public class StringVector extends Sequence<String>{

	/**
	 * A: Contains the actual array.
	 */
	private String[] A;

	/**
	 * Basic constructor: Creates an "empty" list.
	 */
	public StringVector() {
		A = new String[1];
		n = 0;
	}

	
	/**
	 * Get the string i.
	 * 
	 * @param i
	 *            Number of accessed element (from 0).
	 * @return string
	 * @throws IndexOutOfBoundsException
	 *             if index out of bounds
	 */
	public String get(int i) {
		if (i < 0 || i >= size())
			throw new IndexOutOfBoundsException();
		numOps++;
		return A[i];
	}

	/**
	 * Set the string at element i to value.
	 * 
	 * @param i
	 *            Number of accessed element (from 0).
	 * @param value
	 *            String value being put in the array
	 * @return string
	 * @throws IndexOutOfBoundsException
	 *             if index out of bounds
	 */
	public String set(int i, String value) {
		if (i < 0 || i >= size())
			throw new IndexOutOfBoundsException();
		String y = A[i];
		numOps++;
		A[i] = value;
		numOps++;
		return y;
	}

	/**
	 * Insert a new value at index i (bumping elements i - size() up one).
	 * 
	 * @param i
	 *            Number of inserted element
	 * @param value
	 *            String valueb eing inserted
	 * @throws IndexOutOfBoundsException
	 *             if index out of bounds
	 */
	public void add(int i, String value) {
		if (i < 0 || i > size())
			throw new IndexOutOfBoundsException();
		
		//String oldArr[] = A; 
		resize(); // This will make sure A is large enough to hold the new
					// element
		if (A[i]==null) {
			A[i] = value; 
			numOps++;
		}else{
			int movingIndex =0; 
			for (int j = n; j >= i; j--) {
				movingIndex = j+1; 
				if (movingIndex<A.length) {
					A[movingIndex] = A[j];
					numOps++;
				}
			}
			A[i] = value; 
			numOps++;
		}
		n += 1; // change n (array size) after adding
	}


	/**
	 * Remove element i from the array (renumbering everything past it)
	 * 
	 * @param i
	 *            element being removed
	 * @return value that was removed
	 * @throws IndexOutOfBoundsException
	 *             if index out of bounds
	 */
	public String remove(int i) {
		if (i < 0 || i >= size())
			throw new IndexOutOfBoundsException();
		String value = A[i];
		numOps++;
		int shiftIndex = 0;
		if (i != A.length - 1) { //if i is NOT the last element,
							     //otherwise leave the value in there
								 //assuming it has been "remove"
			for (int j = i; j < A.length; j++) {
				shiftIndex = j + 1;
				if (shiftIndex < A.length) {
					A[j] = A[shiftIndex];// shifting elements
					numOps++;
				}
			}
		}
		n -= 1;
		return value;
	}


	/**
	 * If the array is full (that is: A.length == n), resize it so it can hold
	 * one more element.
	 */
	private void resize() {
		if (n < A.length) {
			return; //return when n < A.length
		}
		//otherwise resizing A[] by adding one more space in there
		
		String oldArr[] = A;
		
		
		if (n ==0) {
			A = new String[1];
		}else{
			A = new String[n*2];
		}
		for (int i = 0; i < oldArr.length; i++) {
				A[i] = oldArr[i];
				numOps++;
		}
	}
	
	/**
	 * Remove all elements.
	 */
	public void clear() {
		A = new String[1];
		n = 0;
	}
}
