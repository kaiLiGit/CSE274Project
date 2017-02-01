package unit_test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import data_structures.IntHash;

public class TestingClass {
	public static void main(String[] args) {
		System.out.println("test3");
		int m = 11;
		int s = 2;
		IntHash<String> S = new IntHash<String>(1,0,m);
		S.insert(new Integer(s),  "a");
		S.insert(new Integer(s+m), "b");
		S.insert(new Integer(s+2*m), "c");
		System.out.println("11111111111111111111");
		assertEquals(S.find(new Integer(s)), "a");
		System.out.println("11111111111111111111");
		assertEquals(S.find(new Integer(s+m)), "b");
		System.out.println("11111111111111111111");
		assertEquals(S.find(new Integer(s+2*m)), "c");
		System.out.println("111111remove1111111");
		S.remove(new Integer(s+m));
		System.out.println("11111111111111111111");
		assertEquals(S.find(new Integer(s)), "a");
		System.out.println("11111111111111111111");
		System.out.println(S.find(new Integer(s)));
		assertEquals(S.find(new Integer(s+2*m)), "c");
		System.out.println("11111111111111111111");
		System.out.println(S.find(new Integer(s+2*m)));
	}
}
