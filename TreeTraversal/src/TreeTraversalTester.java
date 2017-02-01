import java.util.ArrayList;
import java.util.Arrays; 

import static org.junit.Assert.*; 

import org.junit.Test; 

public class TreeTraversalTester { 
	
	private static void create_helper(Dictionary<Integer,Integer> D, int a, int b) {
		if (a == b-1) {
			D.insert(new Integer(2*a), new Integer(2*a+1));
		}
		else if (a < b-1) {
			int m = (a+b)/2;
			D.insert(new Integer(2*m), new Integer(2*m+1));
			create_helper(D, a, m);
			create_helper(D, m+1, b);
		}
	}
	
	private static BST<Integer,Integer> create_dictionary(int n) {
		BST<Integer,Integer> D = new BST<Integer,Integer>();
		create_helper(D, 0, n);
		return D;
	}
	
	/**
	 * InOrder empty Tree tester*/
	@Test
	public void inOrderEmptyTree() {
		TreeNode<Integer, Integer> tNode = null;
		ArrayList<Integer> inOrderList = TreeTraversal.inOrder(tNode); 
		assertTrue(inOrderList.isEmpty());
	}
	
	/**
	 * preOrder empty Tree tester*/
	@Test
	public void preOrderEmptyTree() {
		TreeNode<Integer, Integer> tNode = null;
		ArrayList<Integer> preOrderList = TreeTraversal.preOrder(tNode); 
		assertTrue(preOrderList.isEmpty());
	}
	
	/**
	 * postOrder empty Tree tester*/
	@Test
	public void postOrderEmptyTree() {
		TreeNode<Integer, Integer> tNode = null;
		ArrayList<Integer> postOrderList = TreeTraversal.postOrder(tNode); 
		assertTrue(postOrderList.isEmpty());
	}
	
	/**
	 * breathFirst empty Tree tester*/
	@Test
	public void breathFirstEmptyTree() {
		TreeNode<Integer, Integer> tNode = null;
		ArrayList<Integer> breathFirstList = TreeTraversal.BFS(tNode); 
		assertTrue(breathFirstList.isEmpty());
	}
	
	/**
	 * leftlinearTree generator*/
	private BST<Integer, Integer> leftLinearTree(){
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		for (int i = 10; i >0; i--) {
			int j = 1; 
			bst.insert(i, j++);
		} 
		return bst;
	}
	
	/**
	 * rightlinearTree generator*/
	private BST<Integer, Integer> rightLinearTree(){
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		for (int i = 1; i <=10; i++) {
			int j = 10; 
			bst.insert(i, j--);
		} 
		return bst;
	}
	
	@Test
	public void postOrderLeftLinearTreeTest() {
		BST<Integer, Integer> bst = leftLinearTree();
		assertNotNull("bst Should Not be Null", bst);
		ArrayList<Integer> postOrderList = TreeTraversal.postOrder(bst.getRoot()); 
		System.out.println("postOrderLeftLinear"+postOrderList);
		ArrayList<Integer> testList = new ArrayList<Integer>(
				Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)); 
		int size = postOrderList.size(); 
		assertTrue("size not equal",testList.size() == postOrderList.size());
		
		for (int i = 0; i < size; i++) {
			assertEquals(postOrderList.get(i), testList.get(i));
		}
	}
	
	@Test
	public void postOrderRightLinearTreeTest() {
		BST<Integer, Integer> bst = rightLinearTree();
		assertNotNull("bst Should Not be Null", bst);
		ArrayList<Integer> postOrderList = TreeTraversal.postOrder(bst.getRoot()); 
		System.out.println("postOrderRightLinear"+postOrderList);
		ArrayList<Integer> testList = new ArrayList<Integer>(
				Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1));
		int size = postOrderList.size(); 
		assertTrue("size not equal", testList.size() == postOrderList.size());
		
		for (int i = 0; i < size; i++) {
			assertEquals(postOrderList.get(i), testList.get(i));
		}
	}
	
	@Test
	public void bfsLeftLinearTreeTest() {
		BST<Integer, Integer> bst = leftLinearTree();
		assertNotNull("bst Should Not be Null", bst);
		ArrayList<Integer> bfsList = TreeTraversal.BFS(bst.getRoot()); 
		System.out.println("bfsLeftLinear"+ bfsList);
		ArrayList<Integer> testList = new ArrayList<Integer>(
				Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1));
		int size = bfsList.size(); 
		assertTrue("size not equal", testList.size() == bfsList.size());
		
		for (int i = 0; i < size; i++) {
			assertEquals(bfsList.get(i), testList.get(i));
		}
	}
	
	@Test
	public void bfsRightLinearTreeTest() {
		BST<Integer, Integer> bst = rightLinearTree();
		assertNotNull("bst Should Not be Null", bst);
		ArrayList<Integer> bfsList = TreeTraversal.BFS(bst.getRoot()); 
		System.out.println("bfsListRightLinear"+ bfsList);
		ArrayList<Integer> testList = new ArrayList<Integer>(
				Arrays.asList(1, 2, 3, 4, 5, 6,7, 8, 9,10));
		int size = bfsList.size(); 
		assertTrue("size not equal", testList.size() == bfsList.size());
		
		for (int i = 0; i < size; i++) {
			assertEquals(bfsList.get(i), testList.get(i));
		}
	}
	
	@Test
	public void preOrderLeftLinearTreeTest() {
		BST<Integer, Integer> bst = leftLinearTree();
		assertNotNull("bst Should Not be Null", bst);
		ArrayList<Integer> preOrderList = TreeTraversal.preOrder(bst.getRoot()); 
		System.out.println("preOrderLeftLinear"+preOrderList);
		ArrayList<Integer> testList = new ArrayList<Integer>(
				Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1));
		int size = preOrderList.size(); 
		assertTrue("size not equal", testList.size() == preOrderList.size());
		
		for (int i = 0; i < size; i++) {
			assertEquals(preOrderList.get(i), testList.get(i));
		}
	}
	
	@Test
	public void preOrderRightLinearTreeTest() {
		BST<Integer, Integer> bst = rightLinearTree();
		assertNotNull("bst Should Not be Null", bst);
		ArrayList<Integer> preOrderList = TreeTraversal.preOrder(bst.getRoot()); 
		System.out.println("preOrderRightLinear"+preOrderList);
		ArrayList<Integer> testList = new ArrayList<Integer>(
				Arrays.asList(1, 2, 3, 4, 5, 6,7, 8, 9,10));
		int size = preOrderList.size(); 
		assertTrue("size not equal", testList.size() == preOrderList.size());
		
		for (int i = 0; i < size; i++) {
			assertEquals(preOrderList.get(i), testList.get(i));
		}
	}
	
	@Test
	public void inOrderLeftLinearTreeTest() {
		BST<Integer, Integer> bst = leftLinearTree();
		assertNotNull("bst Should Not be Null", bst);
		ArrayList<Integer> inOrderList = TreeTraversal.inOrder(bst.getRoot()); 
		System.out.println("inOrderLeftList" + inOrderList);
		ArrayList<Integer> testList = new ArrayList<Integer>(
				Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
		int size = inOrderList.size(); 
		assertTrue("size not equal", testList.size() == inOrderList.size());
		
		for (int i = 0; i < size; i++) {
			assertEquals(inOrderList.get(i), testList.get(i));
		}
	}
	
	@Test
	public void inOrderRightLinearTreeTest() {
		BST<Integer, Integer> bst = rightLinearTree();
		assertNotNull("bst Should Not be Null", bst);
		ArrayList<Integer> inOrderList = TreeTraversal.inOrder(bst.getRoot()); 
		System.out.println("inOrderRightLinear"+inOrderList);
		ArrayList<Integer> testList = new ArrayList<Integer>(
				Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9,10));
		int size = inOrderList.size(); 
		assertTrue("size not equal", testList.size() == inOrderList.size());
		
		for (int i = 0; i < size; i++) {
			assertEquals(inOrderList.get(i), testList.get(i));
		}
	}
	
	@Test
	public void inOrderTraversalBaseTest() { 
		
		BST<Integer,Integer> B = create_dictionary(10);
		
		ArrayList<Integer> inOrderList = TreeTraversal.inOrder(B.getRoot()); 
		
		ArrayList<Integer> testList = new ArrayList<>
		(Arrays.asList(0, 2, 4, 6, 8, 10, 12, 14, 16, 18));
		
		int size = testList.size();
		
		assertTrue("size not equal", inOrderList.size()==testList.size());
		for (int i = 0; i < size; i++) { 
			assertEquals(testList.get(i), inOrderList.get(i));
		}
	}
	
	@Test
	public void postOrderTraversalTest() {  
		
		BST<Integer,Integer> B = create_dictionary(10);
		
		ArrayList<Integer> postOrderList = TreeTraversal.postOrder(B.getRoot());
		
		ArrayList<Integer> testList = new ArrayList<>
		(Arrays.asList(0,2,6,8,4,12,14,18,16,10));
		
		//System.out.println(postOrderList);
		//System.out.println(testList);
		
		assertEquals(testList.size(), postOrderList.size());
		int size = testList.size(); 
		
		for (int i = 0; i < size; i++) { 
			assertEquals(testList.get(i), postOrderList.get(i));
		}
	} 
	
	@Test
	public void preOrderBaseTest() { 
		BST<Integer,Integer> B = create_dictionary(10);
		
		ArrayList<Integer> preOrderList = TreeTraversal.preOrder(B.getRoot());
		
		ArrayList<Integer> testList = new ArrayList<>
		(Arrays.asList(10, 4, 2, 0, 8, 6, 16, 14, 12, 18));
		
		assertEquals(testList.size(), preOrderList.size());
		int size = testList.size(); 
		
		for (int i = 0; i < size; i++) { 
			assertEquals(testList.get(i), preOrderList.get(i));
		}
	}
	
	@Test
	public void breathFirstBastTest() { 
		BST<Integer,Integer> B = create_dictionary(10);
		
		ArrayList<Integer> bfList = TreeTraversal.BFS(B.getRoot());
		
		ArrayList<Integer> testList = new ArrayList<>
		(Arrays.asList(10, 4, 16, 2, 8, 14, 18, 0, 6, 12));
		
		assertEquals(testList.size(), bfList.size());
		int size = testList.size();  
		
		for (int i = 0; i < size; i++) { 
			assertEquals(testList.get(i), bfList.get(i));
		}
	}
}
