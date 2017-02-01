

public class TreeNode<K extends Comparable<K>, V> {
	public K key;
	public V value;
	public TreeNode<K,V> left;
	public TreeNode<K,V> right;
	protected TreeNode<K, V> root;
	protected int n;

	public TreeNode(K key, V value) {
		this.key = key;
		this.value = value;
		this.left = null;
		this.right = null;
	}

	public TreeNode(K key, V value, TreeNode<K,V> left, TreeNode<K,V> right) {
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}
	
}
