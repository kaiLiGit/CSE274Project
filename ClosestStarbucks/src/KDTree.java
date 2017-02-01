
public class KDTree {  
	private Tnode<Starbucks.StarbucksLocation> root; 
	
	public KDTree(Starbucks.StarbucksLocation loc) {
		this.root = new Tnode<Starbucks.StarbucksLocation>(loc);
	}
	
	/***
	 * 
	 * @author Kai
	 *	Node class consists of data, starbucksLocation, 
	 *  left, left subtree 
	 *  right, right subtree 
	 *  split, split dimension 
	 * @param <T>
	 */
	private class Tnode<T> { 
		private T data;  
		Tnode<T> left; 
		Tnode<T> right; 
		private int split; 
		
		public Tnode(T data) {
			this.setData(data); 
		}
		
		public void setData(T data) {
			this.data = data;
		}
		 
	}
	
	/***
	 * compare starbucksLocation based on split dimension (lng or lat)
	 * @param loc1
	 * @param loc2
	 * @param split
	 * @return 0 same, -1 less than, 1 greater 
	 */
	public int compare(Starbucks.StarbucksLocation loc1, 
					   Starbucks.StarbucksLocation loc2,
					   int split) {
		if(split == 0) {
			if(loc1.lng < loc2.lng ) {
				return -1;
			} else if (loc1.lng > loc2.lng) {
				return 1; 
			} else {
				return 0; 
			}
		} else {
			if(loc1.lat < loc2.lat) {
				return -1; 
			} else if (loc1.lat > loc2.lat) {
				return 1; 
			} else {
				return 0; 
			}
		}
	}
	
	/***
	 * finding the height of the tree 
	 * @param aNode
	 * @return
	 */
	public int findHeight(Tnode<Starbucks.StarbucksLocation> aNode) {
	    if (aNode == null) {
	        return -1;
	    }

	    int lefth = findHeight(aNode.left);
	    int righth = findHeight(aNode.right);
	    
	    if (lefth > righth) {
	        return lefth + 1;
	    } else {
	        return righth + 1;
	    }
	}
	
	/***
	 * insert method for insert new nodes into tree 
	 * @param nodeP root node 
	 * @param loc insert location 
	 * @param split split dimension
	 * @return
	 */
	public Tnode<Starbucks.StarbucksLocation> 
	insert(Tnode<Starbucks.StarbucksLocation> nodeP, 
			Starbucks.StarbucksLocation loc, 
			int split) {
		if(nodeP == null) {
			Tnode<Starbucks.StarbucksLocation> Tn = 
					new Tnode<Starbucks.StarbucksLocation>(loc);
			Tn.split = split;  
			return Tn; 
		}
		
		//same value (lng or lat) depending on split 
		if(compare( loc, nodeP.data, split) == 0) {
			return nodeP;

		} else if(compare(loc, nodeP.data, split) < 0) { // less than 
			// alternate split dimension 
			split = (split == 0) ? 1 : 0; 
			nodeP.left = insert(nodeP.left, loc, split);
			
		} else if(compare(loc, nodeP.data, split) > 0) { // greater than
			// alternate split dimension
			split = (split == 0) ? 1 : 0; 
			nodeP.right = insert(nodeP.right, loc, split);
		}
		return nodeP;
	} 
	
	public Tnode<Starbucks.StarbucksLocation> getRoot() {
		return root;
	} 
	 
	/***
	 * find the nearest Starbucks location to 
	 * searched location, loc
	 * @param n root node of the searched tree 
	 * @param loc search location 
	 * @return nearest location
	 */
	public Starbucks.StarbucksLocation searchNearest
	(Tnode<Starbucks.StarbucksLocation> n, 
			Starbucks.StarbucksLocation loc) { 
		// best guess location 
		Starbucks.StarbucksLocation guess = null;
		// closest distance 
		double closest = Double.MAX_VALUE; 
		
		// node is null, return null 
		if(n == null) {
			return null;
		}
		
		//dist, distance from current node to loc 
		double dist = Starbucks.distance(n.data.lng, n.data.lat,
				loc.lng, loc.lat);
		
		if(dist < closest) {
			closest = dist;
			// switch out guess value if dist is closer 
			guess = n.data; 
		}
		
		//tempBest, temporary best location 
		Starbucks.StarbucksLocation tempBest = null;
		int lr = 0; // left, 0 or right, 1 subtree
		if (compare(loc, n.data, n.split) < 0) {
			tempBest = searchNearest(n.left, loc); 
			lr = 0; 
		} else {
			tempBest = searchNearest(n.right, loc);
			lr = 1; 
		}
		
		
		if(tempBest!= null) {
			dist = Starbucks.distance(tempBest.lng, tempBest.lat,
				loc.lng, loc.lat);
			if(dist < closest) {
				// switch current guess with tempBest 
				// after finding a closer location from 
				// previous recursive search 
				guess = tempBest; 
				closest = dist;
			}
		}
		
		//trace-back search starts
		
		double k = 0; // distance to split line 
		double r = closest; // r, distance range covered by current 
							// closest distance 
		
		if(n.split == 1) { //lat 
			k = 
			Starbucks.distance(loc.lng,
					loc.lat,
					loc.lng, 
					n.data.lat);  
		} else { //lng 
			k =
			Starbucks.distance(loc.lng,
							loc.lat,
							n.data.lng, 
							loc.lat); 
		}
		
		//radius range spans longer regions than 
		//k, distance to axis line, search other subtree
		if(k < r) {
			//0 indicates left subtree, search into right subtree recursively
			//else, search into left recursively
			if(lr == 0) {
				tempBest = searchNearest(n.right, loc);
			} else {
				tempBest = searchNearest(n.left, loc);
			}
			
			if(tempBest != null) {
				dist = Starbucks.distance(tempBest.lng, tempBest.lat,
					loc.lng, loc.lat);
				if(dist < closest) {
				guess = tempBest; 
				closest = dist;
				}
			} 
		}   
		
		// trace-back search ends 
		
		return guess;
	}
}
