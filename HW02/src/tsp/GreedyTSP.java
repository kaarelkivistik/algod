package tsp;

import java.util.*;

public class GreedyTSP {
	
	/* Greedy search */
	public static int[] greedySolution(int[][] adjacencyMatrix){
		HashSet<Integer> visited = new HashSet<>();
		int[] visitedInOrder = new int[adjacencyMatrix.length + 1];

		visited.add(0);
		visitedInOrder[0] = 0;

		int iterator = 0;
		int nextIndex = 0;

		while(true) {
			nextIndex = findSmallestNotVisited(adjacencyMatrix[nextIndex], visited);

			if(nextIndex == -1)
				break;

			visited.add(nextIndex);
			iterator++;
			visitedInOrder[iterator] = nextIndex;
		}

		return visitedInOrder;
	}

	public static int findSmallestNotVisited(int[] distances, HashSet<Integer> visited) {
		int maxDistance = Integer.MAX_VALUE;
		int maxDistanceIndex = -1;

		for(int i = 0; i < distances.length; i++)
			if(!visited.contains(i) && distances[i] < maxDistance) {
				maxDistance = distances[i];
				maxDistanceIndex = i;
			}

		return maxDistanceIndex;
	}
}