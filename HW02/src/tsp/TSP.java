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

    /*
        Sügavuti otsingu ajad:
            testDfsSmall took 1 milliseconds
            testDfsSmallWithoutBNB took 0 milliseconds
            testDfsMedium took 89 milliseconds
            testDfsMediumWithoutBNB took 32579 milliseconds
            testDfsBig took 4898 milliseconds
            ("big" maatriksit ei hakanud mõõtma, arvestades "keskmisele" kulunud aega)

        Parim-enne otsingu ajad:
            testBfsSmall took 0 milliseconds
            testBfsMedium took 198 milliseconds
            testBfsBig took 4097 milliseconds
     */
}