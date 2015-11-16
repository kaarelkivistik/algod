package tsp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kaarel on 13/11/15.
 */
public class TSPTest {


    @Test
    public void testBfsSmall() throws Exception {
        long startTime = System.currentTimeMillis();

        MyTSP myTSP = new MyTSP(TestData.smallMatrix);

        assertEquals("Result distance should be 405",
                405, myTSP.length(myTSP.findOptimalTourUsingBfs()));

        long duration = System.currentTimeMillis() - startTime;

        System.out.println("testBfsSmall took " + duration + " milliseconds");
    }

    @Test
    public void testBfsMedium() throws Exception {
        long startTime = System.currentTimeMillis();

        MyTSP myTSP = new MyTSP(TestData.mediumMatrix);

        assertEquals("Result distance should be 772",
                772, myTSP.length(myTSP.findOptimalTourUsingBfs()));

        long duration = System.currentTimeMillis() - startTime;

        System.out.println("testBfsMedium took " + duration + " milliseconds");
    }

    @Test
    public void testBfsBig() throws Exception {
        long startTime = System.currentTimeMillis();

        MyTSP myTSP = new MyTSP(TestData.bigMatrix);

        assertEquals("Result distance should be 893",
                893, myTSP.length(myTSP.findOptimalTourUsingBfs()));

        long duration = System.currentTimeMillis() - startTime;

        System.out.println("testBfsBig took " + duration + " milliseconds");
    }

    @Test
    public void testDfsSmall() throws Exception {
        long startTime = System.currentTimeMillis();

        MyTSP myTSP = new MyTSP(TestData.smallMatrix);

        assertEquals("Result distance should be 405",
                405, myTSP.length(myTSP.findOptimalTourUsingDfs(false)));

        long duration = System.currentTimeMillis() - startTime;

        System.out.println("testDfsSmall took " + duration + " milliseconds");
    }

    @Test
    public void testDfsSmallWithoutBNB() throws Exception {
        long startTime = System.currentTimeMillis();

        MyTSP myTSP = new MyTSP(TestData.smallMatrix);

        assertEquals("Result distance should be 405",
                405, myTSP.length(myTSP.findOptimalTourUsingDfs(true)));

        long duration = System.currentTimeMillis() - startTime;

        System.out.println("testDfsSmallWithoutBNB took " + duration + " milliseconds");
    }

    @Test
    public void testDfsMedium() throws Exception {
        long startTime = System.currentTimeMillis();

        MyTSP myTSP = new MyTSP(TestData.mediumMatrix);

        assertEquals("Result distance should be 772",
                772, myTSP.length(myTSP.findOptimalTourUsingDfs(false)));

        long duration = System.currentTimeMillis() - startTime;

        System.out.println("testDfsMedium took " + duration + " milliseconds");
    }

    @Test
    public void testDfsMediumWithoutBNB() throws Exception {
        long startTime = System.currentTimeMillis();

        MyTSP myTSP = new MyTSP(TestData.mediumMatrix);

        assertEquals("Result distance should be 772",
                772, myTSP.length(myTSP.findOptimalTourUsingDfs(true)));

        long duration = System.currentTimeMillis() - startTime;

        System.out.println("testDfsMediumWithoutBNB took " + duration + " milliseconds");
    }

    @Test
    public void testDfsBig() throws Exception {
        long startTime = System.currentTimeMillis();

        MyTSP myTSP = new MyTSP(TestData.bigMatrix);

        assertEquals("Result distance should be 893",
                893, myTSP.length(myTSP.findOptimalTourUsingDfs(false)));

        long duration = System.currentTimeMillis() - startTime;

        System.out.println("testDfsBig took " + duration + " milliseconds");
    }
}