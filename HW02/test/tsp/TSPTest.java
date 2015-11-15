package tsp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kaarel on 13/11/15.
 */
public class TSPTest {

    static int[][] smallMatrix = {
            {0,   128,  71,  85, 185},
            {128,   0,  91,  62,  58},
            { 71,  91,   0,  29, 149},
            { 85,  62,  29,   0, 120},
            {185,  58, 149, 120,   0}};


    @Test
    public void testDfs() throws Exception {
        assertEquals("Result distance should be 405",
                405, TSP.distanceOf(smallMatrix, TSP.dfs(smallMatrix)));
    }

    @Test
    public void testBfs() throws Exception {

    }

    @Test
    public void testChildrenOf() throws Exception {
        assertNull(null, TSP.childrenOf(3, new int[]{0, 1, 2}));

        assertArrayEquals(new int[][]{{0, 1}, {0, 2}}, TSP.childrenOf(3, new int[]{0}));
        assertArrayEquals(new int[][]{{2, 1, 0}, {2, 1, 3}}, TSP.childrenOf(4, new int[]{2, 1}));
    }

    @Test
    public void testBound() throws Exception {
        assertEquals(71 + 58 + 29 + 29 + 58, TSP.bound(smallMatrix, new int[]{}));
        assertEquals(71 + 58 + 29 + 29 + 58, TSP.bound(smallMatrix, new int[]{0}));
        assertEquals(128 + 58 + 29 + 29 + 58, TSP.bound(smallMatrix, new int[]{0, 1}));
        assertEquals(128 + 91 + 29 + 29 + 58, TSP.bound(smallMatrix, new int[]{0, 1, 2}));
    }

    @Test
    public void testDistanceOf() throws Exception {
        assertEquals(TSP.distanceOf(smallMatrix, new int[]{0}), 0);
        assertEquals(TSP.distanceOf(smallMatrix, new int[]{0, 1, 2}), 128 + 91);
        assertEquals(TSP.distanceOf(smallMatrix, new int[]{0, 1, 2, 3}), 128 + 91 + 29);
    }
}