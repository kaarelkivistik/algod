package tsp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kaarel on 13/11/15.
 */
public class TSPTest {


    @Test
    public void testBfsSmall() throws Exception {
        BFS bfs = new BFS(TestData.smallMatrix);

        assertEquals("Result distance should be 405",
                405, bfs.length(bfs.findOptimalTour()));
    }

    @Test
    public void testBfsMedium() throws Exception {
        BFS bfs = new BFS(TestData.mediumMatrix);

        assertEquals("Result distance should be 772",
                772, bfs.length(bfs.findOptimalTour()));
    }

    @Test
    public void testBfsBig() throws Exception {
        BFS bfs = new BFS(TestData.bigMatrix);

        assertEquals("Result distance should be 1455",
                1455, bfs.length(bfs.findOptimalTour()));
    }

    @Test
    public void testDfsSmall() throws Exception {
        assertEquals("Result distance should be 405",
                405, TSP.distanceOf(TestData.smallMatrix, TSP.dfs(TestData.smallMatrix)));
    }

    @Test
    public void testDfsMedium() throws Exception {
        assertEquals("Result distance should be 772",
                772, TSP.distanceOf(TestData.mediumMatrix, TSP.dfs(TestData.mediumMatrix)));
    }

    @Test
    public void testDfsBig() throws Exception {
        assertEquals("Result distance should be 1455",
                1455, TSP.distanceOf(TestData.bigMatrix, TSP.dfs(TestData.bigMatrix)));
    }

    @Test
    public void testChildrenOf() throws Exception {
        assertNull(null, TSP.childrenOf(3, new int[]{0, 1, 2}));

        assertArrayEquals(new int[][]{{0, 1}, {0, 2}}, TSP.childrenOf(3, new int[]{0}));
        assertArrayEquals(new int[][]{{2, 1, 0}, {2, 1, 3}}, TSP.childrenOf(4, new int[]{2, 1}));
    }

    @Test
    public void testBound() throws Exception {
        assertEquals(71 + 58 + 29 + 29 + 58, TSP.bound(TestData.smallMatrix, new int[]{}));
        assertEquals(71 + 58 + 29 + 29 + 58, TSP.bound(TestData.smallMatrix, new int[]{0}));
        assertEquals(128 + 58 + 29 + 29 + 58, TSP.bound(TestData.smallMatrix, new int[]{0, 1}));
        assertEquals(128 + 91 + 29 + 29 + 58, TSP.bound(TestData.smallMatrix, new int[]{0, 1, 2}));
    }

    @Test
    public void testDistanceOf() throws Exception {
        assertEquals(TSP.distanceOf(TestData.smallMatrix, new int[]{0}), 0);
        assertEquals(TSP.distanceOf(TestData.smallMatrix, new int[]{0, 1, 2}), 128 + 91);
        assertEquals(TSP.distanceOf(TestData.smallMatrix, new int[]{0, 1, 2, 3}), 128 + 91 + 29);
    }
}