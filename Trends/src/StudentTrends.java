import java.util.*;
/**
 *
 * @author // TODO: Kai Li
 */
public class StudentTrends implements Trends {
	ArrayList<Pair<String, Integer>> rankList = new ArrayList<>();
	StringHash<Integer> trendHashTable = new StringHash<>();  
	private boolean isSorted = false; 
	
	@Override
	public void increaseCount(String s, int amount) {
		if(s== null || s.equals("")){
			throw new IllegalArgumentException(); 
		}
		if(amount <= 0) {
			throw new IllegalArgumentException(); 
		}
		//
		isSorted = false; 
		
		Integer occurrence = trendHashTable.find(s); 
		//Pair <String,Integer> updatedPair = new Pair<> (s, occurrence);
		if (occurrence!=null) {
			occurrence = new Integer(amount + occurrence.intValue());
			//updatedPair = new Pair<> (s, occurrence);
			trendHashTable.remove(s);
			trendHashTable.insert(s, occurrence);
		} else {
			trendHashTable.insert(s, amount);
		}
	}

	@Override
	public int getCount(String s) { 
		if (trendHashTable.find(s) !=null) {
			return trendHashTable.find(s);
		}else {
			return 0; 
		}
	}

	
	
	@Override
	public String getNthPopular(int n) { 
		//check if sorted 
		if (!isSorted) {
			//loading all unique values from trendHashTable in rankList
			for (int i = 0; i < trendHashTable.getTable().size(); i++) {
				if (trendHashTable.getTable().get(i)!= null) {
					rankList.add(trendHashTable.getTable().get(i)); 
				}
			}
			//sort the rankList if not sorted 
			quickSort(0, rankList.size()-1);
			isSorted = true; 
		}
		
		//if get n th value is greater than size of rankList 
		//return null string 
		if (n >= rankList.size()) {
			return ""; 
		}
		
		//return the nth value 
		return rankList.get(n).first;
	}
	
	
	
	
	/**
	 * quickSorting all occurrence values of each pair in ArrayList rankList
	 * @param start starting position of the same occurrence
	 * @param end ending position of the same occurrence
	 * */
	private void quickSort(int start, int end) {
		if (rankList.size() <= 1) {
			return; 
		}
		if (rankList.size() == 2) {
			if (rankList.get(0).second > rankList.get(1).second) {
				swap(rankList, 0, 1); 
				return; 
			}
			if (rankList.get(0).second == rankList.get(1).second) {
				if (rankList.get(0).first.toLowerCase().compareTo
						(rankList.get(0).first.toLowerCase()) > 0) {
					swap(rankList, 0, 1); 
					return; 
				}
			}
		}
		if (start >= end) return; 
		int pivotIndex = partition(rankList,start, end);
		quickSort(start,pivotIndex-1);
		quickSort(pivotIndex+1, end); 
	}
	//return the median Pair value of the three indices 
	private int pickMiddle(int ele1, int ele2, int ele3) {
		if (rankList.get(ele1).second < 
				rankList.get(ele2).second &&
				rankList.get(ele2).second < 
				rankList.get(ele3).second ) {
			return ele2; 
		}
		
		if (rankList.get(ele2).second < 
				rankList.get(ele1).second &&
				rankList.get(ele1).second < 
				rankList.get(ele3).second ) {
			return ele1; 
		}
		return ele3; 
	}
	
	/**
	 * partition occurrence (Integer) values based on pivot chosen in two half
	 * @param pL arrayList of Google Trends pairs 
	 * @param start starting position of the same occurrence
	 * @param end ending position of the same occurrence
	 * @return the pivotIndex value to recursively dividing into subgroups 
	 * */
	private int partition(
			ArrayList<Pair<String, Integer>> pL, int start, int end) {
		//By convention, pick the median of the three 
		//[start ... middle ... end]
		//get the pair with occurrence that is median of the three pairs
		int middle = pickMiddle(start, (start+end)/2, end);
		int occurrencePivot = pL.get(middle).second;
		//swap middle pair with the end pair 
		swap(pL, middle,end); 
		
		int pivotIndex = start; 
		int occur = 0; // for iterating through current occrrence
		String currentStr = ""; // for iterating through current occrrence
		for (int i = start; i < end; i++) {
			occur = pL.get(i).second;
			currentStr = pL.get(i).first;
			//sorting by descending order by using ">=" for occurrence
			if (occur >= occurrencePivot) {
				//sorting by ascending order alphabetically 
				//for the same order in occurrence
				if (occur == occurrencePivot &&
						currentStr.toLowerCase().compareTo
						(pL.get(end).first.toLowerCase()) > 0) {
					pL = swap(pL, i, pivotIndex);
					continue; 
				}
				//replacing ith value with 
				//pivotIndex value 
				//swap(pL(i), pL(pIndex)) ends here
				pL = swap(pL, i, pivotIndex);
				 
				pivotIndex = pivotIndex + 1; 
			}
		}
		//swap(pL(pivotIndex), pL(end)) ends here
		pL = swap(pL, pivotIndex, end);
		
		return pivotIndex;
	}

	/**
	 * swapping pairs in the arrayList of Google Trend pair
	 * @param pL arrayList of Google Trends pairs 
	 * @param index1 position index1 of first pair 
	 * @param index2 position index2 of second pair 
	 * @return the arraylist of Google Trend Pairs after swapped
	 * */
	private static ArrayList<Pair<String, Integer>> swap(
			ArrayList<Pair<String, Integer>> pL, 
			int index1, int index2){
		//Pair<String, Integer> tempPair = pL.get(index1);
		//pL.set(index1, pL.get(index2));
		pL.set(index2, pL.set(index1, pL.get(index2))); 
		return pL;
	}

	@Override
	public int numEntries() { 
		return trendHashTable.size();
	}
}
