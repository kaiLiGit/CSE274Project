/**
 * 
 */


import java.util.ArrayList;
import java.util.Collections;
import java.lang.IllegalArgumentException;

/**
 * @author karroje
 *
 */
public class StringHash<value_type> extends Dictionary<String, value_type> {
	int a;
	int b;
	int m; // size of the table
	final int BASE = 256; 
	
	ArrayList<Pair<String, value_type>> table;

	/**
	 * Hashing function
	 * 
	 * @param key
	 * @return hash value
	 */
	private int hash(String key) {
		int total = 0;
		int charIndex = 0; 
		for(int i = key.length()-1; i>=0; i--) {
			total = (BASE *total + a * key.charAt(charIndex)) % m; // mod ‘m’ each time so that total won’t go over 256  
			charIndex ++; 
		}
		total += b; 
		total = (total % m);
		return total;
	}
	
	/**
	 * resize() the table when n >= m/2
	 * before the table gets full to 
	 * ensure there is space for new key 
	 * and value pairs 
	 */
	private void resize(){
		ArrayList<Pair<String, value_type>> oldTable = table; 
		
		table = new ArrayList<Pair<String,value_type>>(Collections.nCopies(m*2, null));
		m *=2; //change size of hash 
		for (int i = 0; i < oldTable.size() -1; i++) {
			if (oldTable.get(i)!=null) {
				insert(oldTable.get(i).first, oldTable.get(i).second);
			}
		}
	}

	/**
	 * Default constructor
	 */
	public StringHash() {
		this(7, 1, 25014);
	}

	/**
	 * Constructor -- hash values specified.
	 */
	public StringHash(int a, int b, int m) {
		super();
		this.a = a;
		this.b = b;
		this.m = m;
		table = new ArrayList<Pair<String, value_type>>(Collections.nCopies(m,
				null));
	}

	/**
	 * Insert a value/key pair into the dictionary. Do not allow duplicate or
	 * null values.
	 * 
	 * @param key
	 *            key to be inserted
	 * @param value
	 *            value to be inserted // value is data, not hash value
	 * @exception Throw
	 *                IndexOutOfBoundsException if key already present.
	 * @exception Throw
	 *                IllegalArgumentException if value is null.
	 * @exception Throw
	 *                IllegalArgumentException if key < 0. (Makes life easier.
	 */
	@Override
	public void insert(String key, value_type value) {
		if (value == null) {
			throw new IllegalArgumentException("Null values not allowed");
		}
		
		if (n >= (m/2))	{//resize table when n >= (m/2)
			resize();
		}
		
		if (key == null || key.equals("")) {
			throw new IllegalArgumentException("null keys not allowed");
		}	
		
		if (key != null) {
			int i = hash(key);
			Pair<String, value_type> p = table.get(i);
			numOps++;
			while (p != null) {
				if (p.first.equals(key)) {
					throw new IndexOutOfBoundsException("No Duplicate Keys");
				}
				i = (i == table.size() - 1) ? 0 : i + 1;
				p = table.get(i);
				numOps++;
			}
			if (p == null) {
				// inserting pair element into the table
				table.set(i, new Pair<String, value_type>(key, value));
				numOps++;
				n++;
			}
		}
	}

	
	
	public ArrayList<Pair<String, value_type>> getTable() {
		return table;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see data_structures.Dictionary#remove(java.lang.Object)
	 */
	@Override
	public void remove(String key) {
		if (key != null) {
			int i = hash(key);// get the hash code for the key

			Pair<String, value_type> p = table.get(i);
			numOps++;
			while (p != null) {
				// checking if p.first not null
				// checking if p.first (key) in that hash code
				// is the same as the removed key
				// note: hash code can be the same for two keys (collision)
				if (p.first != null && p.first.equals(key)) {
					table.set(i, null);
					numOps++;
					n--;
				}
				i = (i == table.size() - 1) ? 0 : i + 1;
				p = table.get(i);
				numOps++;
				if (p == null) {
					i = (i == table.size() - 1) ? 0 : i + 1;
					p = table.get(i);
					numOps++;
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see data_structures.Container#clear()
	 */
	@Override
	public void clear() {
		table = new ArrayList<Pair<String, value_type>>(Collections.nCopies(m,
				null));
		n = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see data_structures.Dictionary#find(java.lang.Object)
	 */
	@Override
	public value_type find(String key) {
		
		if (key != null) {
			int i = hash(key); // get hash code of the key, index of the table
			Pair<String, value_type> p = table.get(i);
			numOps++;
			while (p != null) {
				if (p.first != null && p.first.equals(key)) {
					return p.second;
				}
				i = (i == table.size() - 1) ? 0 : i + 1;
				p = table.get(i);
				numOps++;
				if (p == null) {
					i = (i == table.size() - 1) ? 0 : i + 1;
					p = table.get(i);
					numOps++;
				}
			}
			return null;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see data_structures.Dictionary#check_structure() This is not useful for
	 * this class -- we will just always pass it.
	 */
	@Override
	public boolean check_structure() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see data_structures.Dictionary#print_structure()
	 */
	@Override
	public void print_structure() {
		for (int i = 0; i < m; i++) {
			Pair<String, value_type> p = table.get(i);
			if (p != null && !p.first.equals(""))
				System.out.println("k, h(k), v = " + p.first + " " + i + " "
						+ p.second);
		}
	}

}
