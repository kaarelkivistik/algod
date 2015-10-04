package dancers;

import dancers.Dancer;
import dancers.Dancers;
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

        Dancer female1 = new Dancer(4, false, 180);
        Dancer female2 = new Dancer(5, false, 177);
        Dancer female3 = new Dancer(6, false, 162);

        assertNull("there should be no match for male1", dancers.findPartnerFor(male1));
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
    }

    @Test
    public void testReturnWaitingList() throws Exception {

    }

    @Test
    public void testAdd() throws Exception {
        Dancers tree = new Dancers();

        Dancers.Node node1 = new Dancers.Node(1, true, 160);
        Dancers.Node node2 = new Dancers.Node(2, true, 165);
        Dancers.Node node3 = new Dancers.Node(3, true, 165);
        Dancers.Node node4 = new Dancers.Node(4, true, 163);
        Dancers.Node node5 = new Dancers.Node(5, false, 163);
        Dancers.Node node6 = new Dancers.Node(6, false, 170);

        tree.add(node1);
        tree.add(node2);
        tree.add(node3);
        tree.add(node4);
        tree.add(node5);
        tree.add(node6);

        Dancers.Node expectedRootNode = new Dancers.Node(1, true, 160);
        expectedRootNode.setRight(new Dancers.Node(2, true, 165));
        expectedRootNode.getRight().setRight(new Dancers.Node(3, true, 165));
        expectedRootNode.getRight().setLeft(new Dancers.Node(4, true, 163));
        expectedRootNode.getRight().getLeft().setRight(new Dancers.Node(5, false, 163));
        expectedRootNode.getRight().getRight().setRight(new Dancers.Node(6, false, 170));

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
        Dancers.Node original = new Dancers.Node(0, false, 1);
        Dancers.Node parent = new Dancers.Node(10, true, 20);
        Dancers.Node child = new Dancers.Node(20, true, 30);
        Dancers.Node replacement = new Dancers.Node(2, true, 3);

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

        Dancers.Node node1 = new Dancers.Node(1, true, 160);
        Dancers.Node node2 = new Dancers.Node(2, true, 165);
        Dancers.Node node3 = new Dancers.Node(3, true, 165);
        Dancers.Node node4 = new Dancers.Node(4, true, 163);
        Dancers.Node node5 = new Dancers.Node(5, false, 163);
        Dancers.Node node6 = new Dancers.Node(6, false, 170);

        tree.add(node1);
        tree.add(node2);
        tree.add(node3);
        tree.add(node4);
        tree.add(node5);
        tree.add(node6);

        tree.replaceSubtree(node2, node4);

        assertEquals(node1.getRight(), node4);
    }

    @Test public void testDelete() throws Exception {
        Dancers tree = new Dancers();

        Dancers.Node node1 = new Dancers.Node(1, true, 160);
        Dancers.Node node2 = new Dancers.Node(2, true, 165);
        Dancers.Node node3 = new Dancers.Node(3, true, 165);
        Dancers.Node node4 = new Dancers.Node(4, true, 163);
        Dancers.Node node5 = new Dancers.Node(5, false, 163);
        Dancers.Node node6 = new Dancers.Node(6, false, 170);

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

        Dancers.Node node1 = new Dancers.Node(1, true, 2);
        Dancers.Node node2 = new Dancers.Node(1, true, 1);
        Dancers.Node node3 = new Dancers.Node(1, true, 3);
        Dancers.Node node4 = new Dancers.Node(1, true, 4);

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