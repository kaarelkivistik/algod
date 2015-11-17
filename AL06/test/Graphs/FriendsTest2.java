package test;

import org.junit.Test;

import Graphs.Friends;

public class FriendsTest2 {

    int[][] adjacencyMatrix1 = {
            {0,1,1,0,0,0},
            {1,0,0,1,1,0},
            {1,0,0,0,1,1},
            {0,1,0,0,0,1},
            {0,1,1,0,0,1},
            {0,0,1,1,1,0},

    };
    int[] pair1 = {0,5};
    int[][] adjacencyMatrix2 = {
            {0,1,1,0,0,0},
            {1,0,0,1,1,0},
            {1,0,0,0,1,0},
            {0,1,0,0,0,1},
            {0,1,1,0,0,1},
            {0,0,0,1,1,0},

    };
    int[] pair2 = {0,5};

    @Test
    public void testConnectionList1() {
        int[] expected = {0,2,5};
        Friends.bfs(adjacencyMatrix1, pair1);
        int[] actual = Friends.getConnectionList();
        org.junit.Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void testDistance1() {
        int expected = 2;
        Friends.bfs(adjacencyMatrix1, pair1);
        int actual = Friends.getDistance();
        org.junit.Assert.assertEquals(expected, actual);
    }

    @Test
    public void testConnectionList2() {
        int[] expected = {0,1,3,5};
        Friends.bfs(adjacencyMatrix2, pair2);
        int[] actual = Friends.getConnectionList();
        org.junit.Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void testDistance2() {
        int expected = 3;
        Friends.bfs(adjacencyMatrix2, pair2);
        int actual = Friends.getDistance();
        org.junit.Assert.assertEquals(expected, actual);
    }
}