package data_structures;

import java.util.ArrayList;

public class MinHeap<E, P extends Comparable<P>> implements MinPriorityQueue<E, P> {
    int numOps;
    
    public ArrayList<Pair<E, P>> heap = new ArrayList<Pair<E,P>>();
	
    private int parent(int i) {
    	return (i -1) /2; 
    }
    
    private int left(int i) {
    	return 2 * i + 1;
    }
    
    private int right(int i) {
    	return 2 * i + 2; 
    }
    
    private boolean compare(P p1, P p2) {
    	return (p1.compareTo(p2) < 0);
    }
    
    private void swap(int e1, int e2){
    	Pair<E, P> tempPair = heap.get(e1);
    	numOps++;
    	heap.set(e1, heap.get(e2));
    	numOps++;
    	numOps++;
    	heap.set(e2, tempPair);
    	numOps++;
    }
    
	@Override
	public void push(E e, P v) {
		heap.add(new Pair<E, P>(e, v)); 
		numOps++;
		bubbleUp(heap.size()-1);
	}

	private void bubbleUp(int i) {
		int parentIndex = parent(i);
		while (i > 0 && compare(heap.get(i).second, 
				heap.get(parentIndex).second)) {
			numOps++;
			numOps++;
			swap(i, parentIndex);
			i = parentIndex; 
			parentIndex = parent(i);
		}
	}

	@Override
	public E pop() {
		if (heap.isEmpty()) {
			throw new ArrayIndexOutOfBoundsException("Empty Heap!");
		} 
		E e = heap.get(0).first;
		numOps++;
		heap.set(0, heap.get(size()-1));
		numOps++;
		numOps++;
		heap.remove(size()-1);
		trickleDown(0); // method to maintain heap-ordered
		return e;
	}
	
	

	private void trickleDown(int i) {
		do {
			int j = -1; //index for tracking minimum priority
			int r = right(i);
			if (r < size() && 
					compare(heap.get(r).second, heap.get(i).second)) {
				numOps++;
				numOps++;
				int l = left(i);
				//if left node happens to be smaller, then j (minIndex) 
				// is l; Or if left node is equal to right node, then 
				// pick left node as minNode anyways, ties broken arbitrarily 
				if (heap.get(l).second.compareTo(heap.get(r).second) <= 0) {
					numOps++;
					numOps++;
					j = l; 
				} else { // right node is the lesser value
					j = r; 
				}
			} else { // right node is greater than left node
					 // check left node
				int l = left(i);
				if (l < size() && compare(heap.get(l).second, heap.get(i).second)) {
					numOps++;
					numOps++;
					j = l; 
				}
			}
			//now swap the jth (min) node with ith node
			if (j >= 0) {
				swap(i, j);
			}
			i = j; 
		} while (i >= 0);
	}

	@Override
	public E peek() {
		return heap.get(0).first;
	}

	@Override
	public int size() {
		return heap.size();
	}

	@Override
	public boolean empty() { 
		return heap.isEmpty();
	}

	@Override
	public void clear() {
		heap.clear(); 
	}

	@Override
	public void resetOps() {
		numOps = 0;
	}

	@Override
	public int numOps() {
		return numOps;
	}
	 
}
