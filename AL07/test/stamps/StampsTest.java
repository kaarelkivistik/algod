package stamps;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by kaarel on 30/11/15.
 */
public class StampsTest {

    public static final int[] STAMPS = {1, 10, 24, 30, 33, 36};

    @Test
    public void testFindstamps() throws Exception {
        Stamps stamps = new Stamps();

        System.out.println(stamps.findstamps(100, STAMPS));
    }
}