

/**
 * @author Kai Li
 * CSE 274 OCT 3, 2016
 */

/**
 * The ListNode<value_type> is a helper class for your LinkedList<value_type>
 * class. As its not intended for use outside the LinkeList class, we are
 * keeping it simple -- the two properties will be access directly, instead of
 * going through inspectors and mutators.
 * 
 * DO NOT MODIFY THIS CLASS.
 * 
 * @param <value_type>
 *            The type of object to be stored in the list.
 */
class ListNode<value_type> {
	public value_type value;
	public ListNode<value_type> next;

	public ListNode(value_type v) {
		value = v;
		next = null;
	}

	public ListNode(value_type v, ListNode<value_type> n) {
		value = v;
		next = n;
	}
}

/*
 * We will implement this as a singly linked list.
 */
public class LinkedList<value_type> extends Sequence<value_type> {

	/**
	 * head will be the first node of the list -- or null if the list is empty
	 */
	private ListNode<value_type> head;

	/**
	 * tail will be the last node of the list -- or null if the list is empty
	 */
	private ListNode<value_type> tail;

	/**
	 * List constructor: must call the superclass constructor.
	 */
	public LinkedList() {
		super();
		head = null;
		tail = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see data_structures.Sequence#get(int)
	 */
	@Override
	public value_type get(int i) {
		if (i < 0 || i >= size())
			throw new IndexOutOfBoundsException();

		value_type value = null;
		ListNode<value_type> traverseNode = head;

		if (this.size() != 0 && head != null) {
			if (i == 0) {
				// use for performance testing
				this.numOps++;

				return head.value;
			}
			// traversing the LinkList node(s) until
			// the requested i position
			for (int j = 0; j < i; j++) {
				traverseNode = traverseNode.next;
				// use for performance testing
				this.numOps++;
			}
			value = traverseNode.value;
			// use for performance testing
			this.numOps++;
		}
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see data_structures.Sequence#set(int, java.lang.String)
	 */
	@Override
	public value_type set(int i, value_type value) {
		if (i < 0 || i >= size())
			throw new IndexOutOfBoundsException();

		ListNode<value_type> traverseNode = head;
		value_type oldVal = null;

		if (this.size() != 0 && head != null) {
			for (int j = 0; j < i; j++) {
				traverseNode = traverseNode.next;
				// use for performance testing
				this.numOps++;
			}
			if (traverseNode != null) {
				oldVal = traverseNode.value;

				// use for performance testing
				this.numOps++;

				traverseNode.value = value;

				// use for performance testing
				this.numOps++;
			}
		}
		return oldVal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see data_structures.Sequence#add(int, java.lang.String)
	 */
	@Override
	public void add(int i, value_type value) {
		if (i < 0 || i > size())
			throw new IndexOutOfBoundsException();

		ListNode<value_type> insertNode = new ListNode<value_type>(value);
		ListNode<value_type> traverseNode = head;

		if (this.size() != 0) {
			if (i == 0 && head != null) {
				head = insertNode;
				insertNode.next = traverseNode;

				// use for performance testing
				this.numOps++;

				this.n += 1;
				return;
			}
			// Traverse Until previous node of Node(i)
			for (int j = 1; j < i; j++) {
				traverseNode = traverseNode.next;
				// use for performance testing
				this.numOps++;
			}
			if (i == this.size() - 1) {
				tail = insertNode;
			}
			if (traverseNode.next != null) {
				// chaining node(i) with insertNode.next
				insertNode.next = traverseNode.next;
				// node(i) references insertNode
				traverseNode.next = insertNode;

				// use for performance testing
				this.numOps += 3;

				// use for performance testing
				this.numOps++;
			}
		} else {// i and size() both are zero
			head = insertNode;
			tail = head;
		}
		this.n += 1; // increment the size after addition of new node
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see data_structures.Sequence#push_back(element_type value)
	 */

	@Override
	public void push_back(value_type value) {
		ListNode<value_type> newNode = new ListNode<value_type>(value);
		if (head == null) {
			head = newNode;
			this.n += 1;
			tail = head;
		} else {
			if (tail != null) {
				tail.next = newNode;
				tail = newNode;
				this.n += 1;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see data_structures.Sequence#remove(int)
	 */

	@Override
	public value_type remove(int i) {
		if (i < 0 || i >= size())
			throw new IndexOutOfBoundsException();
		
		ListNode<value_type> removeNode = head;
		
		if(removeNode!= null) {
			//here removeNode only goes up to previous node 
			//of remove node 
			for (int j = 1; j < i; j++) {
			removeNode = removeNode.next; 
			numOps ++;
			}
		}
		
		value_type val = null; 
		if (i == 0) {
			val = removeNode.value; 
			numOps++; 
			head = removeNode.next; 
			if (i == size()-1) {
				tail = head; 
			}
			numOps++;
		} else {
			val = removeNode.next.value; 
			numOps++;
			//removeNode.next is the removeNode
			//break the reference by setting 
			//removeNode.next to removeNode.next.next
			//that is removeNode depends on if 
			//next element has a value or is null
			//if it has a value, it means removeNode is in the middle
			removeNode.next = removeNode.next.next; 
			numOps++;
			numOps++;
			numOps++;
			if (i == size()-1) {
				tail = removeNode; //removeNode.next got removed
									//removeNode is the new tail
			}
		}
		this.n --; 
		return val;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see data_structures.Sequence#in(element_type query)
	 */

	@Override
	public boolean in(value_type query) {
		// traverse through to find matching node
		ListNode<value_type> traverseNode = head;
		for (int i = 0; i < this.size(); i++) {
			traverseNode = traverseNode.next;
			// use for performance testing
			this.numOps++;
			if (traverseNode != null) {
				if (traverseNode.value.equals(query)) {
					// use for performance testing
					this.numOps++;
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see data_structures.Sequence#clear()
	 */
	@Override
	public void clear() {
		head = null;
		n = 0;
	}
}
