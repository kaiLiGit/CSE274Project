import java.util.ArrayList;


/**
 * @author karroje
 *
 */
public class MatrixGraph extends Graph {
	double matrix[][];
	
	public MatrixGraph(int n) {
		super(n);
		matrix = new double[n][n];
	}

	/* (non-Javadoc)
	 * @see Graph#weight(int, int)
	 */
	@Override
	Double getWeight(int i, int j) {
		if (i < 0 || j < 0 || i > this.num_nodes || j > this.num_nodes) {
			throw new IndexOutOfBoundsException("Node does not exist");
		}
		if (matrix[i][j] == 0.0) {
			return null;
		}
		return new Double(matrix[i][j]);
	}

	/* (non-Javadoc)
	 * @see Graph#setWeight(int, int, java.lang.Double)
	 */
	@Override
	void setWeight(int i, int j, Double weight) {
		if (i < 0 || j < 0 || i > this.num_nodes || j > this.num_nodes) {
			throw new IndexOutOfBoundsException("Node does not exist");
		}
		matrix[i][j] = weight; 
		matrix[j][i] = weight; 
	}

	/* (non-Javadoc)
	 * @see Graph#adjacentNodes(int)
	 */
	@Override
	ArrayList<Pair<Integer, Double>> adjacentNodes(int i) {
		if (i < 0 || i > this.num_nodes) {
			throw new IndexOutOfBoundsException("Node does not exist");
		}
		ArrayList<Pair<Integer, Double>> ajList = 
				new ArrayList<Pair<Integer,Double>>(); 
		for (int j = 0; j < matrix[i].length; j++) {
			if (matrix[i][j] != 0.0) {
				ajList.add(new Pair<Integer, Double>
				(new Integer(j), new Double(matrix[i][j])));
			}
		}
		return ajList; 
	}

	/* (non-Javadoc)
	 * @see Graph#degree(int)
	 */
	@Override
	int degree(int i) {
		if (i < 0 || i > this.num_nodes) {
			throw new IndexOutOfBoundsException("Node does not exist");
		}
		int count =0; 
		for (int j = 0; j < matrix[i].length; j++) {
			if (matrix[i][j] != 0.0) {
				count++;
			}
		}
		return count; 
	}

}
