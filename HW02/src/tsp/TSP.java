package tsp;


import java.util.Arrays;
import java.util.Stack;

public class TSP {
	
	/* Depth-first search */
	public static int[] dfs(int[][] adjacencyMatrix){
        Stack<int[]> stack = new Stack<>();

        int[] node = new int[]{};

        stack.push(node);

        int[] best = node;
        int bestDistance = Integer.MAX_VALUE;

        while(!stack.isEmpty()) {
            node = stack.pop();

            int[][] children = childrenOf(adjacencyMatrix.length, node);

            if(children == null) {
                int distance = distanceOf(adjacencyMatrix, node);
                distance += adjacencyMatrix[node[node.length - 1]][node[0]];

                if(distance < bestDistance) {
                    best = node;
                    bestDistance = distance;
                }
            } else {
                for(int[] child : children)
                    stack.push(child);
            }
        }

        return extendLeafNode(best);
	}
	
	/* Best first search */
	public static int[] bfs(int[][] adjacencyMatrix) {
		return null;
	}

    public static int[][] childrenOf(int numberOfTowns, int[] node) {
        if(node.length == numberOfTowns)
            return null;

        int[][] children = new int[numberOfTowns - node.length][node.length + 1];
        int counter = 0;

        for(int i = 0; i < numberOfTowns; i++)
            if(!arrayContains(i, node)) {
                for(int j = 0; j < node.length; j++)
                    children[counter][j] = node[j];

                children[counter][node.length] = i;

                counter++;
            }


        return children;
    }

    public static boolean arrayContains(int value, int[] array) {
        for(int i = 0; i < array.length; i++)
            if(array[i] == value)
                return true;

        return false;
    }

    public static int bound(int[][] matrix, int[] node) {
        int bound = distanceOf(matrix, node);

        for(int i = 0; i < matrix.length; i++)
            if(node.length < 2 || !arrayContains(i, node))
                bound += minimumDistance(matrix[i]);

        if(node.length > 1)
            bound += minimumDistance(matrix[node[node.length - 1]]);

        return bound;
    }

    public static int distanceOf(int[][] matrix, int[] node) {
        int cost = 0;

        if(node.length > 1) {
            for (int i = 0; i < node.length - 1; i++)
                cost += matrix[node[i]][node[i + 1]];
        }

        return cost;
    }

    public static int minimumDistance(int[] array) {
        return Arrays.stream(array)
                .filter(i -> i > 0)
                .min().getAsInt();
    }

    public static int[] extendLeafNode(int[] node) {
        int[] extendedNode = new int[node.length + 1];

        for(int i = 0; i < node.length; i++)
            extendedNode[i] = node[i];

        extendedNode[node.length] = node[0];

        return extendedNode;
    }
}