package Graphs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by kaarel on 16/11/15.
 */
public class FriendsTest {

    @org.junit.Test
    public void testFriendsOf() throws Exception {
        assertThat(Friends.friendsOf(TestData.SMALL_MATRIX, Arrays.asList(0)),
                is(Arrays.asList(Arrays.asList(0, 1), Arrays.asList(0, 3))));

        assertThat(Friends.friendsOf(TestData.SMALL_MATRIX, Arrays.asList(0, 1)),
                is(Arrays.asList(Arrays.asList(0, 1, 2))));

        assertThat(Friends.friendsOf(TestData.SMALL_MATRIX, Arrays.asList(0, 1, 2)),
                is(Arrays.asList(Arrays.asList(0, 1, 2, 4))));

        assertThat(Friends.friendsOf(TestData.SMALL_MATRIX, Arrays.asList(0, 1, 2, 4)),
                is(new ArrayList<>()));
    }

    @Test
    public void testBfs() throws Exception {
        Friends.bfs(TestData.SMALL_MATRIX, new int[]{0, 4});

        assertArrayEquals(new int[]{0, 1, 2, 4}, Friends.getConnectionList());
        assertEquals(3, Friends.getDistance());
    }
}