package tsp;

public class TSP {
	
	/* Depth-first search */
	public static int[] dfs(int[][] adjacencyMatrix){
        MyTSP myTSP = new MyTSP(adjacencyMatrix);

        return myTSP.findOptimalTourUsingDfs(false).stream().mapToInt(i -> i).toArray();
	}
	
	/* Best first search */
	public static int[] bfs(int[][] adjacencyMatrix) {
        MyTSP myTSP = new MyTSP(adjacencyMatrix);

		return myTSP.findOptimalTourUsingBfs().stream().mapToInt(i -> i).toArray();
	}
}