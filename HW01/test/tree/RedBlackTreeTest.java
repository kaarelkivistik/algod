package tree;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kaarel on 03/10/15.
 */
public class RedBlackTreeTest {

    @Test
    public void testInsert() throws Exception {
        RedBlackTree.Node<Integer, Object> expectedRootNode = new RedBlackTree.Node<>(2);
        expectedRootNode.setBlack(true);
        expectedRootNode.setLeft(new RedBlackTree.Node<>(1));
        expectedRootNode.getLeft().setBlack(true);
        expectedRootNode.setRight(new RedBlackTree.Node<>(4));
        expectedRootNode.getRight().setBlack(true);
        expectedRootNode.getRight().setLeft(new RedBlackTree.Node<>(3));
        expectedRootNode.getRight().setRight(new RedBlackTree.Node<>(5));

        expectedRootNode.getRight().setBlack(false);
        expectedRootNode.getRight().getLeft().setBlack(true);
        expectedRootNode.getRight().getRight().setBlack(true);
        expectedRootNode.getRight().getRight().setRight(new RedBlackTree.Node<>(5));

        RedBlackTree<Integer, Object> tree = new RedBlackTree<>();

        tree.put(1, null);
        tree.put(2, null);
        tree.put(3, null);
        tree.put(4, null);
        tree.put(5, null);
        tree.put(5, null);

        assertEquals(expectedRootNode.toString(), tree.getRoot().toString());
    }

    @Test
    public void testInsertAgain() throws Exception {
        RedBlackTree.Node<Integer, Object> expectedRootNode = new RedBlackTree.Node<>(9);
        expectedRootNode.setBlack(true);
        expectedRootNode.setLeft(new RedBlackTree.Node<>(7));
        expectedRootNode.getLeft().setBlack(true);
        expectedRootNode.setRight(new RedBlackTree.Node<>(10));
        expectedRootNode.getRight().setBlack(true);
        expectedRootNode.getLeft().setLeft(new RedBlackTree.Node<>(6));
        expectedRootNode.getLeft().setRight(new RedBlackTree.Node<>(8));

        RedBlackTree<Integer, Object> tree = new RedBlackTree<>();

        tree.put(10, null);
        tree.put(9, null);
        tree.put(8, null);
        tree.put(7, null);
        tree.put(6, null);

        assertEquals(expectedRootNode.toString(), tree.getRoot().toString());
    }

    @Test
    public void testFind() throws Exception {
        RedBlackTree<Integer, Object> tree = new RedBlackTree<>();

        RedBlackTree.Node<Integer, Object> node15 = new RedBlackTree.Node<>(15);

        tree.put(10, null);
        tree.put(5, null);
        tree.put(20, null);
        tree.put(15, "foobar");
        tree.put(25, null);

        assertNotNull(tree.get(15));
        assertEquals("foobar", tree.get(15).getValue());
    }
}