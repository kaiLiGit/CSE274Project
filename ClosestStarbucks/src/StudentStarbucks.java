/**
 * 1. Which type of data structure did you choose?
 * Explain the main ideas behind your data structure.
 * 		I picked K-D tree. K indicates the number of dimension.
 * 		K = 2 in this case. Since we are only dealing with 
 * 		longitude and latitude. The main idea of this data structure 
 * 		is to take in a randomized list of locations and insert them 
 * 		into the tree by starting with the first location as the root 
 * 		node, and split that based on longitudes(pick longitude as the 
 * 		initial splitting dimension). Then insert the 
 * 		remaining locations as nodes depending on their split dimensions.
 * 		Each node has an attribute, split, which determines if its 
 * 		split is based on longitude axis or latitude axis. That way, the 
 * 		tree structure will be partitioned by longitude axis and latitude 
 * 		axis. And each node location longitude and latitude values determine 
 * 		which subtree it falls into. 
 * 
 * 2. Explain the worst-case running time of each of your methods
 * 		along with a justification of your claims
 * 
 * 		The worst-case running time of both build and getNearest 
 * 		can both be linear O(n), in situations like: 
 * 		non-balanced tree with terribly arranged input data
 * 		In that case, k-d tree would recede to linkedList 
 * 		Hence, getNearest will be the same as if using bruteForce 
 * 		or even slower 
 * 	
 * 		To solve these issues, notice that the input data set 
 * 		populated with locations has to be randomized to help 
 * 		building a somewhat balanced tree. In the Controller Class, 
 * 		after reading all locations from the csv file, individual 
 * 		location data were already being scrambled in main method. 
 * 		That way, the array of locations passed in the build method 
 * 		can be used directly for building a somewhat balanced k-d tree
 * 
 * 		With a somewhat balanced tree, that also reduce the time of 
 * 		traversing the tree. Shorter tree means less comparisons need 
 * 		to be done in terms of traversing or finding the nearest neighbor. 
 * 
 * */

public class StudentStarbucks extends Starbucks{
	private StarbucksLocation[] allLoc; 
	private KDTree kdt; 
	
	@Override
	public void build(StarbucksLocation[] allLocations) {
		this.allLoc = new StarbucksLocation[allLocations.length];
		for (int i = 0; i < allLocations.length; i++) {
			Starbucks.StarbucksLocation loc = 
					new Starbucks.StarbucksLocation(); 
			loc.city = allLocations[i].city; 
			loc.address = allLocations[i].address; 
			loc.lng = allLocations[i].lng; 
			loc.lat = allLocations[i].lat; 
			allLoc[i] = loc;
		} 
		

	    kdt = new KDTree(allLoc[0]);
		
		for (int i = 1; i < allLoc.length; i++) {
			kdt.insert(kdt.getRoot(), allLoc[i], 0);
		} 
	}
	
	

	@Override
	public StarbucksLocation getNearest(double lng, double lat) {
		
		Starbucks.StarbucksLocation loc = 
				new StarbucksLocation("",
						"", lng, lat); 
		// nearest for deep copy of the location l 
		StarbucksLocation nearest = 
				new StarbucksLocation();
		StarbucksLocation l = 
				kdt.searchNearest(kdt.getRoot(), loc);
		nearest.lng = l.lng;
		nearest.lat = l.lat;
		nearest.address = l.address;
		nearest.city = l.city;
		return nearest;
	}
}
