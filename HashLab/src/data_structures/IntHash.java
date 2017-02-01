/**
 * 
 */
package data_structures;

import java.util.ArrayList;
import java.util.Collections;
import java.lang.IllegalArgumentException;



/**
 * @author karroje
 *
 */
public class IntHash<value_type> extends Dictionary<Integer, value_type> {
	int a;
	int b;
	int m; // size of the table

	ArrayList<Pair<Integer,value_type>> table;
	
	/**
	 * Hashing function
	 * @param key
	 * @return hash value
	 */
	private int hash(Integer key) {
		return (((int)key*a) + b) % m;
	}
	
	/**
	 * Default constructor
	 */
	public IntHash() {
		this(7, 1, 25014);
	}
	
	
	/**
	 * Constructor -- hash values specified.
	 */
	public IntHash(int a, int b, int m) {
		super();
		this.a = a;
		this.b = b;
		this.m = m;
		table = new ArrayList<Pair<Integer,value_type>>(Collections.nCopies(m, null));
	}

	/**
	 * Insert a value/key pair into the dictionary.  Do not allow duplicate
	 * or null values.
	 * @param key        key to be inserted
	 * @param value      value to be inserted // value is data, not hash value
	 * @exception   Throw IndexOutOfBoundsException if key already present.
	 * @exception   Throw IllegalArgumentException if value is null.
	 * @exception   Throw IllegalArgumentException if key < 0.  (Makes life easier.
	 */
	@Override
	public void insert(Integer key, value_type value) {
		if (value == null)
			throw new IllegalArgumentException("Null values not allowed");
		if (n == m)
			throw new ArrayIndexOutOfBoundsException("Table's full");
		if (key < 0)
			throw new IllegalArgumentException("Negative keys not allowed");
		
		if (key!=null) {
			int i = hash(key);
			Pair<Integer, value_type> p = table.get(i);
			numOps++;
			while (p!=null) {
				if (p.first.equals(key)) {
					throw new IndexOutOfBoundsException("No Duplicate Keys");
				}
				i = (i == table.size()-1)? 0:i+1;
				p = table.get(i);
				numOps++;
			}
			if (p == null) {
				// inserting pair element into the table
				table.set(i, new Pair<Integer, value_type>(key, value));
				numOps++;
				n++;
			}
		}
	}

	/* (non-Javadoc)
	 * @see data_structures.Dictionary#remove(java.lang.Object)
	 */
	@Override
	public void remove(Integer key) {
		if (key!=null){
			int i = hash(key);//get the hash code for the key
		 
			Pair<Integer, value_type> p = table.get(i);
			numOps++;
			while (p!=null) {
				//checking if p.first not null
				//checking if p.first (key) in that hash code
				//is the same as the removed key
				//note: hash code can be the same for two keys (collision)
				if (p.first!=null && p.first.equals(key)) {
					table.set(i, null);
					numOps++;
					n--;
				}
				i = (i == table.size()-1)? 0:i+1;
				p = table.get(i);
				numOps++;
				if (p==null) {
					i = (i == table.size()-1)? 0:i+1;
					p = table.get(i);
					numOps++;
				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see data_structures.Container#clear()
	 */
	@Override
	public void clear() {
		table = new ArrayList<Pair<Integer,value_type>>(Collections.nCopies(m, null));
		n=0;
	}

	/* (non-Javadoc)
	 * @see data_structures.Dictionary#find(java.lang.Object)
	 */
	@Override
	public value_type find(Integer key) {
		if (key!=null) {
			int i = hash(key); //get hash code of the key, index of the table
			Pair<Integer, value_type> p = table.get(i); 
			numOps++;
			while(p!=null) {
				if (p.first!=null && p.first.equals(key)) {
					 return p.second;
				}
				i = (i == table.size()-1)? 0:i+1;
				p = table.get(i);
				numOps++;
				if (p==null) {
					i = (i == table.size()-1)? 0:i+1;
					p = table.get(i);
					numOps++;
				}
			}
			return null;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see data_structures.Dictionary#check_structure()
	 * This is not useful for this class -- we will just always pass it.
	 */
	@Override
	public boolean check_structure() {
		return true;
	}

	/* (non-Javadoc)
	 * @see data_structures.Dictionary#print_structure()
	 */
	@Override
	public void print_structure() {
		for (int i=0; i < m; i++) {
			Pair<Integer,value_type> p = table.get(i);
			if (p != null && p.first >= 0)
				System.out.println("k, h(k), v = " + p.first + " " + i + " " + p.second);
		}
	}

}
