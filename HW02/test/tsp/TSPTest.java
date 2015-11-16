package tsp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kaarel on 13/11/15.
 */
public class TSPTest {


    @Test
    public void testBfsSmall() throws Exception {
        MyTSP myTSP = new MyTSP(TestData.smallMatrix);

        assertEquals("Result distance should be 405",
                405, myTSP.length(myTSP.findOptimalTourUsingBfs()));
    }

    @Test
    public void testBfsMedium() throws Exception {
        MyTSP myTSP = new MyTSP(TestData.mediumMatrix);

        assertEquals("Result distance should be 772",
                772, myTSP.length(myTSP.findOptimalTourUsingBfs()));
    }

    @Test
    public void testBfsBig() throws Exception {
        MyTSP myTSP = new MyTSP(TestData.bigMatrix);

        assertEquals("Result distance should be 893",
                893, myTSP.length(myTSP.findOptimalTourUsingBfs()));
    }

    @Test
    public void testDfsSmall() throws Exception {
        MyTSP myTSP = new MyTSP(TestData.smallMatrix);

        assertEquals("Result distance should be 405",
                405, myTSP.length(myTSP.findOptimalTourUsingDfs(false)));
    }

    @Test
    public void testDfsSmallWithoutBNB() throws Exception {
        MyTSP myTSP = new MyTSP(TestData.smallMatrix);

        assertEquals("Result distance should be 405",
                405, myTSP.length(myTSP.findOptimalTourUsingDfs(true)));
    }

    @Test
    public void testDfsMedium() throws Exception {
        MyTSP myTSP = new MyTSP(TestData.mediumMatrix);

        assertEquals("Result distance should be 772",
                772, myTSP.length(myTSP.findOptimalTourUsingDfs(false)));
    }

    @Test
    public void testDfsMediumWithoutBNB() throws Exception {
        MyTSP myTSP = new MyTSP(TestData.mediumMatrix);

        assertEquals("Result distance should be 772",
                772, myTSP.length(myTSP.findOptimalTourUsingDfs(true)));
    }

    @Test
    public void testDfsBig() throws Exception {
        MyTSP myTSP = new MyTSP(TestData.bigMatrix);

        assertEquals("Result distance should be 893",
                893, myTSP.length(myTSP.findOptimalTourUsingDfs(false)));
    }
}