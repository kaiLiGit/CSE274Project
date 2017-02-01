import java.util.ArrayList;

/**
 * @author Kai Li
 *
 */
public class ListGraph extends Graph {
	ArrayList<ArrayList<Pair<Integer, Double>>> list;

	public ListGraph(int n) {
		super(n);
		list = new ArrayList<ArrayList<Pair<Integer, Double>>>();
		for (int i = 0; i < n; i++) {
			list.add(new ArrayList<Pair<Integer, Double>>());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Graph#weight(int, int)
	 */
	@Override
	Double getWeight(int i, int j) {
		if (i < 0 || j < 0 || i > this.num_nodes || j > this.num_nodes) {
			throw new IndexOutOfBoundsException("Node does not exist");
		}
		ArrayList<Pair<Integer, Double>> iList = list.get(i);
		for (Pair<Integer, Double> p : iList) {
			if (p.first.equals(j)) {
				return p.second;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Graph#addEdge(int, int)
	 */
	@Override
	void setWeight(int i, int j, Double weight) {
		if (i < 0 || j < 0 || i > this.num_nodes || j > this.num_nodes) {
			throw new IndexOutOfBoundsException("Node does not exist");
		}
		ArrayList<Pair<Integer, Double>> iList = list.get(i);
		Pair<Integer, Double> p1 = null;
		for (int k = 0; k < iList.size(); k++) {
				if (iList.get(k).first.equals(j)) {
					p1 = iList.get(k);
					break;
				}
		}

		if (p1 == null) {
			// adding new node j to i 
			iList.add(new Pair<Integer, Double>(new Integer(j), new Double(
					weight)));
			ArrayList<Pair<Integer, Double>> jList = list.get(j);
			// adding new node i  to j 
			jList.add(new Pair<Integer, Double>(new Integer(i), new Double(
					weight)));
		} else {
			p1.second = weight;
		}
	}

	/*
	 * (non-javadoc)
	 * 
	 * @see Graph#AdjacentNodes
	 */
	ArrayList<Pair<Integer, Double>> adjacentNodes(int i) {
		if (i < 0 || i > this.num_nodes) {
			throw new IndexOutOfBoundsException("Node does not exist");
		}
		return list.get(i);
	}

	/*
	 * (non-javadoc)
	 * 
	 * @see Graph#degree
	 */
	int degree(int i) {
		if (i < 0 || i > this.num_nodes) {
			throw new IndexOutOfBoundsException("Node does not exist");
		}
		return list.get(i).size();
	}

}
