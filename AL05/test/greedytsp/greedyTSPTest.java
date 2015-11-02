package greedytsp;

import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Created by kaarel on 02/11/15.
 */
public class GreedyTSPTest {

    @org.junit.Test
    public void testGreedySolution() throws Exception {
        int[][] adjacencyMatrix = new int[][] {
                {0, 1, 4, 7, 8},
                {3, 0, 9, 6, 2},
                {2, 4, 0, 5, 3},
                {8, 5, 9, 0, 1},
                {9, 6, 7, 3, 0}
        };

        GreedyTSP.greedySolution(adjacencyMatrix);
    }

    @org.junit.Test
    public void testFindSmallestNotVisited() throws Exception {
        HashSet<Integer> visited = new HashSet<>();

        visited.add(0);
        visited.add(2);

        assertEquals(1, GreedyTSP.findSmallestNotVisited(new int[]{10, 9, 0, 103, 94}, visited));

        visited.clear();
        visited.add(0);
        visited.add(4);

        assertEquals(5, GreedyTSP.findSmallestNotVisited(new int[]{0, 900, 222, 439, 19, 28}, visited));
    }
}