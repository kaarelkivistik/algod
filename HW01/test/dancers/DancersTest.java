package dancers;

import org.junit.Test;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by kaarel on 02/10/15.
 */
public class DancersTest {

    @Test
    public void testFindPartnerFor() throws Exception {
        Dancers dancers = new Dancers();

        Dancer male1 = new Dancer(1, true, 190);
        Dancer male2 = new Dancer(2, true, 185);
        Dancer male3 = new Dancer(3, true, 178);

        Dancer female1 = new Dancer(4, false, 180);
        Dancer female2 = new Dancer(5, false, 177);
        Dancer female3 = new Dancer(6, false, 162);

        assertNull("there should be no match for male1", dancers.findPartnerFor(male1));
        assertThat("list should contain male1", dancers.returnWaitingList(), is(Arrays.asList(male1)));

        assertThat("there should be a match for female1 (male1)",
                dancers.findPartnerFor(female1),
                is(new SimpleEntry<>(male1, female1)));
        assertEquals("list should be empty", dancers.returnWaitingList().size(), 0);

        assertNull("there should be no match for male2", dancers.findPartnerFor(male2));
        assertNull("there should be no match for male3", dancers.findPartnerFor(male3));

        assertThat("there should be a match for female2 (male3)",
                dancers.findPartnerFor(female2),
                is(new SimpleEntry<>(male3, female2)));
        assertThat("there should be a match for female3 (male2)",
                dancers.findPartnerFor(female3),
                is(new SimpleEntry<>(male2, female3)));
    }

    @Test
    public void testReturnWaitingList() throws Exception {

    }
}