package tree;

import dancers.Dancer;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by kaarel on 04/10/15.
 */
public class DancersTreeTest {

    @Test
    public void testAdd() throws Exception {
        DancersTree tree = new DancersTree();

        DancerNode dancerNode1 = new DancerNode(1, true, 160);
        DancerNode dancerNode2 = new DancerNode(2, true, 165);
        DancerNode dancerNode3 = new DancerNode(3, true, 165);
        DancerNode dancerNode4 = new DancerNode(4, true, 163);
        DancerNode dancerNode5 = new DancerNode(5, false, 163);
        DancerNode dancerNode6 = new DancerNode(6, false, 170);

        tree.add(dancerNode1);
        tree.add(dancerNode2);
        tree.add(dancerNode3);
        tree.add(dancerNode4);
        tree.add(dancerNode5);
        tree.add(dancerNode6);

        DancerNode expectedRootNode = new DancerNode(1, true, 160);
        expectedRootNode.setRight(new DancerNode(2, true, 165));
        expectedRootNode.getRight().setRight(new DancerNode(3, true, 165));
        expectedRootNode.getRight().setLeft(new DancerNode(4, true, 163));
        expectedRootNode.getRight().getLeft().setRight(new DancerNode(5, false, 163));
        expectedRootNode.getRight().getRight().setRight(new DancerNode(6, false, 170));

        assertEquals(expectedRootNode.toString(), tree.getRoot().toString());

        assertEquals("successor to 160 is 163", dancerNode4, tree.successor(dancerNode1));
        assertEquals("successor to 163 is 165", dancerNode3, tree.successor(dancerNode2));
        assertEquals("successor to 165 is 170", dancerNode6, tree.successor(dancerNode3));
        assertEquals("successor to 170 is null", null, tree.successor(dancerNode6));

        assertThat(tree.toList(),
                is(Arrays.asList(
                        dancerNode1,
                        dancerNode4,
                        dancerNode5,
                        dancerNode2,
                        dancerNode3,
                        dancerNode6
                )));
    }

    @Test
    public void testReplace() throws Exception {
        DancerNode original = new DancerNode(0, false, 1);
        DancerNode parent = new DancerNode(10, true, 20);
        DancerNode child = new DancerNode(20, true, 30);
        DancerNode replacement = new DancerNode(2, true, 3);

        original.setParent(parent);
        original.setLeft(child);
        original.replace(replacement);

        assertEquals(original, replacement);
        assertEquals(original.getParent(), parent);
        assertEquals(original.getLeft(), child);
    }

    @Test
    public void testReplaceSubtree() throws Exception {
        DancersTree tree = new DancersTree();

        DancerNode dancerNode1 = new DancerNode(1, true, 160);
        DancerNode dancerNode2 = new DancerNode(2, true, 165);
        DancerNode dancerNode3 = new DancerNode(3, true, 165);
        DancerNode dancerNode4 = new DancerNode(4, true, 163);
        DancerNode dancerNode5 = new DancerNode(5, false, 163);
        DancerNode dancerNode6 = new DancerNode(6, false, 170);

        tree.add(dancerNode1);
        tree.add(dancerNode2);
        tree.add(dancerNode3);
        tree.add(dancerNode4);
        tree.add(dancerNode5);
        tree.add(dancerNode6);

        tree.replaceSubtree(dancerNode2, dancerNode4);

        assertEquals(dancerNode1.getRight(), dancerNode4);
    }

    @Test public void testDelete() throws Exception {
        DancersTree tree = new DancersTree();

        DancerNode dancerNode1 = new DancerNode(1, true, 160);
        DancerNode dancerNode2 = new DancerNode(2, true, 165);
        DancerNode dancerNode3 = new DancerNode(3, true, 165);
        DancerNode dancerNode4 = new DancerNode(4, true, 163);
        DancerNode dancerNode5 = new DancerNode(5, false, 163);
        DancerNode dancerNode6 = new DancerNode(6, false, 170);

        tree.add(dancerNode1);
        tree.add(dancerNode2);
        tree.add(dancerNode3);
        tree.add(dancerNode4);
        tree.add(dancerNode5);
        tree.add(dancerNode6);

        tree.delete(dancerNode6);

        assertNull(dancerNode3.getRight());

        tree.delete(dancerNode4);

        assertEquals(dancerNode5, dancerNode2.getLeft());

        tree.delete(dancerNode2);

        assertEquals(dancerNode2, dancerNode3);
    }

    @Test
    public void testDeleteRoot() throws Exception {
        DancersTree tree = new DancersTree();

        DancerNode dancerNode1 = new DancerNode(1, true, 2);
        DancerNode dancerNode2 = new DancerNode(1, true, 1);
        DancerNode dancerNode3 = new DancerNode(1, true, 3);
        DancerNode dancerNode4 = new DancerNode(1, true, 4);

        tree.add(dancerNode1);
        tree.add(dancerNode2);
        tree.add(dancerNode3);
        tree.add(dancerNode4);

        tree.delete(dancerNode1);

        assertEquals(dancerNode2, tree.getRoot().getLeft());
        assertEquals(dancerNode3, tree.getRoot());
        assertEquals(dancerNode4, tree.getRoot().getRight());
    }
}