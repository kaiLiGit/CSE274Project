import java.util.ArrayList;

public class TreeTraversal {
	/** 
	 * Given a TreeNode<Integer,Integer> object representing the root of a binary search tree,
	 * return an ArrayList<Integer> holding the keys in order from a post-order traversal.
	 * RESTRICTION: Use recursion for this implementation.
	 * @param r
	 * @return Array list
	 */
	public static ArrayList<Integer> postOrder(TreeNode<Integer,Integer> r) {
		ArrayList<Integer> keys = new ArrayList<Integer>();
		return postOrderRecur(r, keys); 
	}
	

	private static ArrayList<Integer> postOrderRecur(TreeNode<Integer, Integer> r,
			ArrayList<Integer> k) { 
		if(r == null) {
			return k;
		}
		k = postOrderRecur(r.left, k); 
		k = postOrderRecur(r.right, k);
		k.add(r.key);
		return k; 
	}


	/**
	 * Given a TreeNode<Integer,Integer> object representing the root of a binary tree,
	 * return an ArrayList<Integer> holding the keys in order from a BFS traversal.
	 * RESTRICTION: Do NOT use recursion for this implementation -- use a Queue.  (Or
	 * use your Linked List class as a queue.)
	 * @param root
	 * @return
	 */
	public static ArrayList<Integer> BFS(TreeNode<Integer, Integer> root) {
		ArrayList<Integer> keys = new ArrayList<Integer>();
		LinkedList<TreeNode<Integer, Integer>> queue = new LinkedList<>();
		if (root != null) {
			queue.push_back(root); 
		}
		while (!queue.isEmpty()) {
			TreeNode<Integer, Integer> popNode = queue.remove(0); 
			keys.add(popNode.key);
			if (popNode.left != null) {
				queue.push_back(popNode.left);
			}
			if (popNode.right != null) {
				queue.push_back(popNode.right);
			}
		}
		return keys; 
	}

	/**
	 * Given a TreeNode<Integer,Integer> object representing the root of a binary tree,
	 * return an ArrayList<Integer> holding the keys in order from a pre-order traversal.
	 * RESTRICTION: Do NOT use recursion for this implementation -- use a Stack.  (Or
	 * use your Linked List class as a stack.)
	 * @param root
	 * @return
	 */
	public static ArrayList<Integer> preOrder(TreeNode<Integer, Integer> root) {
		ArrayList<Integer> keys = new ArrayList<Integer>();
		LinkedList<TreeNode<Integer, Integer>> stack = new LinkedList<> ();
		if (root != null) {
			stack.push_back(root);
		}
		while (!stack.isEmpty()) {
			TreeNode<Integer, Integer> popNode = stack.pop_back();
			if (popNode.key!=null) {
				keys.add(popNode.key);
			}
			// stack is LIFO, so push_back right node first 
			// then left node. When popping the top element, 
			// left will comeout first
			if (popNode.right != null) {
				stack.push_back(popNode.right);
			}
			if (popNode.left != null) {
				stack.push_back(popNode.left);
			}
		}
		return keys; 
	}

	/**
	 * Given a TreeNode<Integer,Integer> object representing the root of a binary tree,
	 * return an ArrayList<Integer> holding the keys in order from an in-order traversal.
	 * RESTRICTION: Do NOT use recursion for this implementation -- use a Stack.  (Or
	 * use your Linked List class as a stack.)
	 * @param root
	 * @return
	 */
	public static ArrayList<Integer> inOrder(TreeNode<Integer, Integer> root) {
		ArrayList<Integer> keys = new ArrayList<Integer>();
		LinkedList<TreeNode<Integer, Integer>> stack = new LinkedList<>();
		TreeNode<Integer, Integer> currentNode = root; 
		
		while (currentNode!=null) {
			stack.push_back(currentNode);
			currentNode = currentNode.left; 
		}
		
		while (!stack.isEmpty()) {
			currentNode = stack.pop_back(); 
			keys.add(currentNode.key);
			if (currentNode.right !=null) {
				currentNode = currentNode.right;
				while (currentNode!=null) {
				stack.push_back(currentNode);
				currentNode = currentNode.left; 
				} 
			} 
		}
		return keys;
	} 
}
