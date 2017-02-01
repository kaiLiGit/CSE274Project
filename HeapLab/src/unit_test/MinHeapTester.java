package unit_test;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.Arrays;

import data_structures.MinHeap;
import data_structures.MinPriorityQueue;

import org.junit.Test;

public class MinHeapTester {
	
	
	@Test
	public void test01() {
		MinPriorityQueue<String,Integer> H = new MinHeap<>();
		assertEquals(H.size(), 0);
	}

	@Test 
	public void oneNodePushTest() {
		MinHeap<String, Integer> heap = new MinHeap<String, Integer>();
		heap.push("firstNode", 10);
		assertEquals(heap.size(), 1);
	}
	
	@Test
	public void multipleNodePushTest1() {
		MinHeap<String, Integer> heap = new MinHeap<String, Integer>();
		heap.push("life", 9);
		heap.push("yoga", 6);
		heap.push("yawn", 26);
		heap.push("learn", 8);
		heap.push("look", 16);
		heap.push("phew", 4);
		heap.push("see", 17);
		assertEquals("phew", heap.peek());
	}
	
	@Test
	public void multipleNodePushTest2() {
		MinHeap<String, Integer> H = new MinHeap<String, Integer>();
		ArrayList<Integer> intList = new ArrayList<> (
				Arrays.asList(4, 8, 6, 9, 16, 26, 17));
		
		ArrayList<String> strList = new ArrayList<> (
				Arrays.asList("phew", "learn", "yoga",
						"life", "look", "yawn", "see"));
		
		H.push("life", 9);
		H.push("yoga", 6);
		H.push("yawn", 26);
		H.push("learn", 8);
		H.push("look", 16);
		H.push("phew", 4);
		H.push("see", 17);
		
		for (int i = 0; i < intList.size(); i++){
			assertEquals(intList.get(i), H.heap.get(i).getSecond());
		} 
		
		for (int i = 0; i < strList.size(); i++){
			assertEquals(strList.get(i), H.heap.get(i).getFirst());
		} 
	}
	
	@Test
	public void tiesPushTest() {
		MinHeap<String, Integer> heap = new MinHeap<String, Integer>();
		heap.push("life", 9);
		heap.push("yoga", 9);
		//minHeap: if child node is not smaller than parent
		//Pair<"life", 9> should be the parent, or the root
		assertEquals("life", heap.peek());
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void emptyPop() {
		MinHeap<String, Integer> heap = new MinHeap<String, Integer>();
		heap.pop();
	}
	
	@Test
	public void popOneTime() {
		MinHeap<String, Integer> H = new MinHeap<String, Integer>();
		H.push("life", 9);
		H.pop(); 
		assertTrue(H.size() == 0);
	}
	
	@Test
	public void popMultipleTimes() {
		MinHeap<String, Integer> H = new MinHeap<String, Integer>();
		ArrayList<Integer> intList = new ArrayList<> (
				Arrays.asList(8, 9, 17, 26, 16));
		
		ArrayList<String> strList = new ArrayList<> (
				Arrays.asList("learn", "life", "see",
						"yawn", "look"));
		
		H.push("life", 9);
		H.push("yoga", 6);
		H.push("yawn", 26);
		H.push("learn", 8);
		H.push("look", 16);
		H.push("phew", 4);
		H.push("see", 17);
		
		// pop two times
		H.pop();
		H.pop();
		for (int i = 0; i < intList.size(); i++){
			assertEquals(intList.get(i), H.heap.get(i).getSecond());
		} 
		for (int i = 0; i < strList.size(); i++){
			assertEquals(strList.get(i), H.heap.get(i).getFirst());
		}
	}
	
	@Test
	public void popWithTiesTest() {
		MinHeap<String, Integer> H = new MinHeap<String, Integer>();
		ArrayList<Integer> intList = new ArrayList<> (
				Arrays.asList(8, 9, 8, 17, 16, 26));
		
		ArrayList<String> strList = new ArrayList<> (
				Arrays.asList("learn", "life", "yoga",
						"see", "look", "yawn"));
		
		// <"yoga", 8> and <"learn", 8> have the same priority
		H.push("life", 9);
		H.push("yoga", 8); 
		H.push("yawn", 26);
		H.push("learn", 8);
		H.push("look", 16);
		H.push("phew", 4);
		H.push("see", 17);
		
		// In trickleDown(), when ties is encountered while 
		// traversing down, go left direction is being implemented
		H.pop();
		
		for (int i = 0; i < intList.size(); i++){
			assertEquals(intList.get(i), H.heap.get(i).getSecond());
		} 
		
		for (int i = 0; i < strList.size(); i++){
			assertEquals(strList.get(i), H.heap.get(i).getFirst());
		}
	}
	
	@Test 
	public void peekTest() {
		MinHeap<String, Integer> H = new MinHeap<String, Integer>();
		H.push("yawn", 26);
		H.push("learn", 8);
		H.push("look", 16);
		
		assertEquals("learn", H.peek());
	}
	
	@Test 
	public void sizeTest() {
		MinHeap<String, Integer> H = new MinHeap<String, Integer>();
		H.push("life", 9);
		H.push("yoga", 8); 
		H.push("yawn", 26);
		H.push("learn", 8);
		H.push("look", 16);
		H.push("phew", 4);
		H.push("see", 17);
		
		assertTrue(H.size() == 7);
	}
	
	@Test
	public void isEmptyTest() {
		MinHeap<String, Integer> H = new MinHeap<String, Integer>();
		H.push("life", 9);
		H.push("yoga", 8); 
		H.push("yawn", 26);
		H.push("learn", 8);
		
		assertFalse(H.empty());
		
		H.pop();
		H.pop();
		H.pop();
		H.pop();
		
		assertTrue(H.empty());
	}
	
	@Test
	public void clearTest() {
		MinHeap<String, Integer> H = new MinHeap<String, Integer>();
		H.push("life", 9);
		H.push("yoga", 8); 
		H.push("yawn", 26);
		H.push("learn", 8);
		H.push("look", 16);
		H.push("phew", 4);
		H.push("see", 17);
		H.clear();
		assertTrue(H.size() == 0);
		assertTrue(H.empty());
	}
}
