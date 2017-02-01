/**
 * 
 */
package data_structures;

import java.lang.IndexOutOfBoundsException;
import java.util.ArrayList;
import java.util.Arrays; 

/**
 * @author karroje
 *
 */
public class BST<K extends Comparable<K>, V> extends Dictionary<K, V> {
	protected TreeNode<K, V> root;

	/**
	 * 
	 */
	public BST() {
		super();
		root = null;
	}

	/**
	 * Compare two keys and increment numOps.
	 * 
	 * @param k1
	 *            First key
	 * @param k2
	 *            Second key
	 * @return -1: k1 smaller; 0: elements equal; 1: k22 smaller
	 */
	private int compareKeys(K k1, K k2) {
		numOps++;
		return k1.compareTo(k2);
	}

	/**
	 * Get the tree's root node.
	 * 
	 * @return
	 */
	public TreeNode<K, V> getRoot() {
		return root;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see data_structures.Dictionary#insert(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public void insert(K key, V value) {
		if (key == null) {
			throw new IllegalArgumentException("Key cannot be null");
		}
		if (value == null) {
			remove(key);
			return;
		}
		if (root == null) {
			root = new TreeNode<K, V>(key, value);
			n++;
			return; 
		}
		
		TreeNode<K, V> p = getLastNode(key).get(0);
		addNewNode(p, new TreeNode<K, V>(key, value));
		n++;
	}

	private void addNewNode(TreeNode<K, V> p, TreeNode<K, V> newNode) {
		if (p == null) {
			root = newNode;
		} else {
			int comp = compareKeys(newNode.key, p.key);
			if (comp > 0) {
				if (p.right!=null) {
					if (compareKeys(p.right.key, newNode.key)==0) {
						throw new IndexOutOfBoundsException("key already present"); 
					}
				}
				p.right = newNode;
			} else if (comp < 0) {
				if (p.left!=null) {
					if (compareKeys(p.left.key, newNode.key)==0) {
						throw new IndexOutOfBoundsException("key already present"); 
					}
				}
				p.left = newNode;
			} else {
				throw new IndexOutOfBoundsException("key already present"); 
			}
		}
	}

	
	private ArrayList<TreeNode<K, V>> getLastNode(K key) {
		
		TreeNode<K, V> walkingNode = root; //prev = null;
		TreeNode<K, V> parent = root; 
		
		while (walkingNode != null) {
			//prev = walkingNode;
			int comp = compareKeys(key, walkingNode.key);
			if (comp < 0) {
				walkingNode = walkingNode.left;
			} else if (comp > 0) {
				walkingNode = walkingNode.right;
			}  
			
			if (walkingNode!= null && (comp =compareKeys(walkingNode.key, key)) !=0) {
				parent = walkingNode; 
			}
			if (comp ==0) { 
			//key already present 
			return new ArrayList<TreeNode<K, V>>(Arrays.asList(parent, walkingNode)); 	
			} 
		}
		return new ArrayList<TreeNode<K, V>>(Arrays.asList(parent, walkingNode)); 	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see data_structures.Dictionary#remove(java.lang.Object)
	 */
	@Override
	public void remove(K key) {
		if (key == null) {
			throw new IllegalArgumentException("Key cannot be null");
		}
		ArrayList<TreeNode<K, V>> parentNChild = getLastNode(key);
		
		TreeNode<K, V> parentNode = parentNChild.get(0); 
		TreeNode<K, V> removeNode = parentNChild.get(1); 
		
		if (removeNode == null) {
			return; 
		}
		
		if (compareKeys(key, removeNode.key) !=0) {
			  return; //cannot found key 
		}

		// cases: one child OR no children
		if (removeNode.left == null || removeNode.right == null) {
			oneChildOrNoChildren(parentNode,removeNode);
		} else {
			twoChildrenCase(removeNode);
		}
		
		n--;
	}

	private void oneChildOrNoChildren(TreeNode<K, V> parentNode, TreeNode<K, V> removeNode) {
		TreeNode<K, V> replaceNode = null;
		
		if (removeNode.left == null && removeNode.right == null) {
			if (compareKeys(removeNode.key, parentNode.key) < 0) {
				parentNode.left = null; 
			} 
			if (compareKeys(removeNode.key, parentNode.key) > 0) {
				parentNode.right = null; 
			}
			
			if (parentNode.left != null){
				if (compareKeys(removeNode.key, parentNode.left.key)>0) {
					parentNode.right = null; 
				}
			} 
			
		}else if (removeNode.left != null) {
			// One Child case
			// right sub-tree is null, but left sub-tree is not
			
			replaceNode = removeNode.left;
			if (compareKeys(removeNode.key, parentNode.key) <0) {
				parentNode.left = replaceNode; 
			} else if (compareKeys(removeNode.key, parentNode.key)>0) {
				parentNode.right = replaceNode; 
			} 
		} else {
			// left sub-tree is null, but right sub-tree is not
			replaceNode = removeNode.right;
			if (compareKeys(removeNode.key, parentNode.key) <0) {
				parentNode.left = replaceNode; 
			} else if (compareKeys(removeNode.key, parentNode.key)>0) {
				parentNode.right = replaceNode; 
			} 
		}
		// if removeNode occurs to be the root
		if (removeNode == root) {
			root = replaceNode;
		} 
	}
	
	private void twoChildrenCase(TreeNode<K, V> removeNode) {
		// two children case
		TreeNode<K, V> walkingNode = removeNode.right;

		// In the case of left subtree is null
		// the right-subtree minimum is the node of right subtree
		// replace node with right subtree node
		if (walkingNode.left == null) {
			// deleting or replacing <K,V> values in
			// removeNode
			removeNode.key = walkingNode.key;
			removeNode.value = walkingNode.value;
			//removeNode is the parent in this case 
			//walkingNode is the child 
			oneChildOrNoChildren(removeNode, walkingNode);
		} else {
			// find the minimum of the right subtree
			while (walkingNode.left != null) {
				walkingNode = walkingNode.left;
			}
			// replacing <K,V> values in
			// removeNode
			removeNode.key = walkingNode.key;
			removeNode.value = walkingNode.value;
			//temp is the parent node of the minimum of right subtree
			TreeNode<K, V> temp = removeNode.right; 
			//walkingNode is the minimum found from last while loop
			//loop until parent of minimum is found 
 			while (temp.left != walkingNode) {
				temp = temp.left;  
			}
 			//delete the minimum node from right subtree from temp 
			oneChildOrNoChildren(temp, walkingNode);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see data_structures.Dictionary#find(java.lang.Object)
	 */
	@Override
	public V find(K key) {
		if (key == null) {
			throw new IllegalArgumentException("Key cannot be null");
		}
		TreeNode<K, V> walkingNode = root;
		int comp = 0;

		while (walkingNode != null) {
			comp = compareKeys(key, walkingNode.key);
			if (comp == 0) {
				return walkingNode.value;
			} else if (comp > 0) {
				walkingNode = walkingNode.right; 
			}else {
				walkingNode = walkingNode.left; 
			}
		}
		return null; 
	}
	
	

	/**
	 * Return the smallest value in the tree. (Return null if empty)
	 * 
	 * @return key
	 */
	public K min() {
		TreeNode<K, V> walkingNode = root;
	    if (walkingNode == null) {
			return null; 
		}
	    
		while (walkingNode.left != null) {
			walkingNode = walkingNode.left;
		}
		
		return walkingNode.key; 
	}

	/**
	 * Return the largest value in the tree. (Return null if empty)
	 * 
	 * @return key
	 */
	public K max() {
		TreeNode<K, V> walkingNode = root;
		if (walkingNode == null) {
			return null; 
		}
		
		while (walkingNode.right != null) {
			walkingNode = walkingNode.right;
		}
		
		return walkingNode.key; 
	}

	
	/**
	 * Return the height of the tree. Definition: The *depth* of a node is
	 * number of edges from the root to that node. The *height* of a tree is
	 * equal to the depth of the node with the greatest depth of all the nodes.
	 * 
	 * @return int
	 */
	public int height() {
		return findHeight(root);
	}

	private int findHeight(TreeNode<K, V> walkingNode) {
		if (walkingNode == null) {
			return -1;
		}
		return 1 + Math.max(findHeight(walkingNode.left),
				findHeight(walkingNode.right));
	}

	boolean isBSTHelper(TreeNode<K, V> root, K min_value, K max_value) {
		if (root == null)
			return true;

		if ((min_value != null && root.key.compareTo(min_value) <= 0)
				|| (max_value != null && root.key.compareTo(max_value) >= 0))
			return false;

		return isBSTHelper(root.left, min_value, root.key)
				&& isBSTHelper(root.right, root.key, max_value);
	}

	/**
	 * Check that the tree is a BST.
	 * 
	 * @param root
	 *            Root of tree being checked.
	 * @return
	 */
	boolean isBST(TreeNode<K, V> root) {
		return isBSTHelper(root, null, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see data_structures.Dictionary#check_structure()
	 */
	@Override
	public boolean check_structure() {
		return isBST(root);
	}

	void print_structure_helper(TreeNode<K, V> root, int indent) {
		for (int i = 0; i < indent; i++)
			System.out.print("\t");
		if (root == null) {
			System.out.println("LEAF");
			return;
		}
		System.out.println(root.key + ": " + root.value);
		print_structure_helper(root.left, indent + 1);
		print_structure_helper(root.right, indent + 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see data_structures.Dictionary#print_structure()
	 */
	@Override
	public void print_structure() {
		print_structure_helper(root, 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see data_structures.Container#clear()
	 */
	@Override
	public void clear() {
		n = 0;
		root = null;
	}

}
