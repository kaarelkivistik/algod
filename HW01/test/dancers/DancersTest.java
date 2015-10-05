package dancers;

import org.junit.Test;

import java.util.AbstractMap;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by kaarel on 04/10/15.
 */
public class DancersTest {

    @Test
    public void testFindPartnerFor() throws Exception {
        Dancers dancers = new Dancers();

        Dancer male1 = new Dancer(1, true, 190);
        Dancer male2 = new Dancer(2, true, 185);
        Dancer male3 = new Dancer(3, true, 178);
        Dancer male4 = new Dancer(7, true, 195);
        Dancer male5 = new Dancer(8, true, 190);
        Dancer male6 = new Dancer(9, true, 170);

        Dancer female1 = new Dancer(4, false, 180);
        Dancer female2 = new Dancer(5, false, 177);
        Dancer female3 = new Dancer(6, false, 162);
        Dancer female4 = new Dancer(10, false, 155);
        Dancer female5 = new Dancer(11, false, 156);
        Dancer female6 = new Dancer(12, false, 195);

        assertNull(dancers.findPartnerFor(male1));
        assertNull(dancers.findPartnerFor(male2));
        assertNull(dancers.findPartnerFor(male3));
        assertNull(dancers.findPartnerFor(male4));
        assertNull(dancers.findPartnerFor(male5));
        assertNull(dancers.findPartnerFor(male6));

        assertEquals(
                new AbstractMap.SimpleEntry<>(female1, male2),
                dancers.findPartnerFor(female1)
        );
        assertEquals(
                new AbstractMap.SimpleEntry<>(female2, male3),
                dancers.findPartnerFor(female2)
        );
        assertEquals(
                new AbstractMap.SimpleEntry<>(female3, male6),
                dancers.findPartnerFor(female3)
        );
        assertEquals(
                new AbstractMap.SimpleEntry<>(female4, male1),
                dancers.findPartnerFor(female4)
        );
        assertEquals(
                new AbstractMap.SimpleEntry<>(female5, male5),
                dancers.findPartnerFor(female5)
        );
        assertNull(dancers.findPartnerFor(female6));

        assertEquals(2, dancers.returnWaitingList().size());

        assertEquals(
                new AbstractMap.SimpleEntry<>(female1, male4),
                dancers.findPartnerFor(female1)
        );

        /*assertNull("there should be no match for male1", dancers.findPartnerFor(male1));
        assertThat("list should contain male1", dancers.returnWaitingList(), is(Arrays.asList(male1)));

        assertThat("there should be a match for female1 (male1)",
                dancers.findPartnerFor(female1),
                is(new AbstractMap.SimpleEntry<>(female1, male1)));
        assertEquals("list should be empty", dancers.returnWaitingList().size(), 0);

        assertNull("there should be no match for male2", dancers.findPartnerFor(male2));
        assertNull("there should be no match for male3", dancers.findPartnerFor(male3));

        assertThat("there should be a match for female2 (male3)",
                dancers.findPartnerFor(female2),
                is(new AbstractMap.SimpleEntry<>(female2, male3)));
        assertThat("there should be a match for female3 (male2)",
                dancers.findPartnerFor(female3),
                is(new AbstractMap.SimpleEntry<>(female3, male2)));

        assertEquals("list should be empty", dancers.returnWaitingList().size(), 0);

        assertNull("there should be no match for female4", dancers.findPartnerFor(female4));
        assertNull("there should be no match for female5", dancers.findPartnerFor(female5));
        assertNull("there should be no match for female6", dancers.findPartnerFor(female6));

        assertThat("list should contain ...", dancers.returnWaitingList(),
                is(Arrays.asList(female4, female5, female6)));

        assertThat("there should be a match for male4 (female6)",
                dancers.findPartnerFor(male4),
                is(new AbstractMap.SimpleEntry<>(male4, female6)));
        assertThat("there should be a match for male5 (female5)",
                dancers.findPartnerFor(male5),
                is(new AbstractMap.SimpleEntry<>(male5, female5)));
        assertThat("there should be a match for male6 (female4)",
                dancers.findPartnerFor(male6),
                is(new AbstractMap.SimpleEntry<>(male6, female4)));*/
    }

    @Test
    public void testSimplePair() throws Exception {
        Dancers tree = new Dancers();

        Dancer male = new Dancer(1, true, 180);
        Dancer female = new Dancer(2, false, 160);

        assertNull(tree.findPartnerFor(male));
        assertEquals(new AbstractMap.SimpleEntry<>(female, male), tree.findPartnerFor(female));
    }

    @Test
    public void testAdd() throws Exception {
        Dancers tree = new Dancers();

        Dancer node1 = new Dancer(1, true, 160);
        Dancer node2 = new Dancer(2, true, 165);
        Dancer node3 = new Dancer(3, true, 165);
        Dancer node4 = new Dancer(4, true, 163);
        Dancer node5 = new Dancer(5, false, 163);
        Dancer node6 = new Dancer(6, false, 170);

        tree.add(node1);
        tree.add(node2);
        tree.add(node3);
        tree.add(node4);
        tree.add(node5);
        tree.add(node6);

        Dancer expectedRootNode = new Dancer(1, true, 160);
        expectedRootNode.setRight(new Dancer(2, true, 165));
        expectedRootNode.getRight().setRight(new Dancer(3, true, 165));
        expectedRootNode.getRight().setLeft(new Dancer(4, true, 163));
        expectedRootNode.getRight().getLeft().setRight(new Dancer(5, false, 163));
        expectedRootNode.getRight().getRight().setRight(new Dancer(6, false, 170));

        assertEquals(expectedRootNode.toString(), tree.getRoot().toString());

        assertEquals("successor to 160 is 163", node4, tree.successor(node1));
        assertEquals("successor to 163 is 165", node3, tree.successor(node2));
        assertEquals("successor to 165 is 170", node6, tree.successor(node3));
        assertEquals("successor to 170 is null", null, tree.successor(node6));

        assertThat(tree.toList(),
                is(Arrays.asList(
                        node1,
                        node4,
                        node5,
                        node2,
                        node3,
                        node6
                )));
    }

    @Test
    public void testReplace() throws Exception {
        Dancer original = new Dancer(0, false, 1);
        Dancer parent = new Dancer(10, true, 20);
        Dancer child = new Dancer(20, true, 30);
        Dancer replacement = new Dancer(2, true, 3);

        original.setParent(parent);
        original.setLeft(child);
        original.replace(replacement);

        assertEquals(original, replacement);
        assertEquals(original.getParent(), parent);
        assertEquals(original.getLeft(), child);
    }

    @Test
    public void testReplaceSubtree() throws Exception {
        Dancers tree = new Dancers();

        Dancer node1 = new Dancer(1, true, 160);
        Dancer node2 = new Dancer(2, true, 165);
        Dancer node3 = new Dancer(3, true, 165);
        Dancer node4 = new Dancer(4, true, 163);
        Dancer node5 = new Dancer(5, false, 163);
        Dancer node6 = new Dancer(6, false, 170);

        tree.add(node1);
        tree.add(node2);
        tree.add(node3);
        tree.add(node4);
        tree.add(node5);
        tree.add(node6);

        tree.replaceSubtree(node2, node4);

        assertEquals(node4, node1.getRight());
    }

    @Test public void testDelete() throws Exception {
        Dancers tree = new Dancers();

        Dancer node1 = new Dancer(1, true, 160);
        Dancer node2 = new Dancer(2, true, 165);
        Dancer node3 = new Dancer(3, true, 165);
        Dancer node4 = new Dancer(4, true, 163);
        Dancer node5 = new Dancer(5, false, 163);
        Dancer node6 = new Dancer(6, false, 170);

        tree.add(node1);
        tree.add(node2);
        tree.add(node3);
        tree.add(node4);
        tree.add(node5);
        tree.add(node6);

        tree.delete(node6);

        assertNull(node3.getRight());

        tree.delete(node4);

        assertEquals(node5, node2.getLeft());

        tree.delete(node2);

        assertEquals(node2, node3);
    }

    @Test
    public void testDeleteRoot() throws Exception {
        Dancers tree = new Dancers();

        Dancer node1 = new Dancer(1, true, 2);
        Dancer node2 = new Dancer(1, true, 1);
        Dancer node3 = new Dancer(1, true, 3);
        Dancer node4 = new Dancer(1, true, 4);

        tree.add(node1);
        tree.add(node2);
        tree.add(node3);
        tree.add(node4);

        tree.delete(node1);

        assertEquals(node2, tree.getRoot().getLeft());
        assertEquals(node3, tree.getRoot());
        assertEquals(node4, tree.getRoot().getRight());
    }
}