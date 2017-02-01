package unit_test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import data_structures.BST;
import data_structures.Dictionary;
import data_structures.IntHash;

public class TestingClass {
//	private static void create_helper(BST<Integer,Integer> D, int a, int b) {
//		if (a == b-1) {
//			D.insert(new Integer(2*a), new Integer(2*a+1));
//		}
//		else if (a < b-1) {
//			int m = (a+b)/2;
//			D.insert(new Integer(2*m), new Integer(2*m+1));
//			create_helper(D, a, m);
//			create_helper(D, m+1, b);
//		}
//	}
//	
//	private static BST<Integer,Integer> create_BST(int n) {
//		BST<Integer,Integer> D = new BST<Integer,Integer>();
//		create_helper(D, 0, n);
//		return D;
//	}
	
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
	
	private static Dictionary<Integer,Integer> create_dictionary(int n) {
		Dictionary<Integer,Integer> D = new BST<Integer,Integer>();
		create_helper(D, 0, n);
		return D;
	}
	@Test(expected = IndexOutOfBoundsException.class) 
	public static void main(String[] args) {
	Dictionary<Integer,Integer> D = create_dictionary(10);
	D.insert(new Integer(6), new Integer(6));
	}
}
