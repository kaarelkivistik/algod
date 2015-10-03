package tree;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by kaarel on 03/10/15.
 */
public class RedBlackTreeTest {

    @Test
    public void testInsert() throws Exception {
        Node expectedRootNode = new Node(2);
        expectedRootNode.setBlack(true);
        expectedRootNode.setLeft(new Node(1));
        expectedRootNode.getLeft().setBlack(true);
        expectedRootNode.setRight(new Node(4));
        expectedRootNode.getRight().setBlack(true);
        expectedRootNode.getRight().setLeft(new Node(3));
        expectedRootNode.getRight().setRight(new Node(5));

        expectedRootNode.getRight().setBlack(false);
        expectedRootNode.getRight().getLeft().setBlack(true);
        expectedRootNode.getRight().getRight().setBlack(true);
        expectedRootNode.getRight().getRight().setRight(new Node(5));

        RedBlackTree tree = new RedBlackTree();

        tree.insert(new Node(1));
        tree.insert(new Node(2));
        tree.insert(new Node(3));
        tree.insert(new Node(4));
        tree.insert(new Node(5));
        tree.insert(new Node(5));

        assertEquals(expectedRootNode.toString(), tree.getRoot().toString());
    }

    @Test
    public void testInsertAgain() throws Exception {
        Node expectedRootNode = new Node(9);
        expectedRootNode.setBlack(true);
        expectedRootNode.setLeft(new Node(7));
        expectedRootNode.getLeft().setBlack(true);
        expectedRootNode.setRight(new Node(10));
        expectedRootNode.getRight().setBlack(true);
        expectedRootNode.getLeft().setLeft(new Node(6));
        expectedRootNode.getLeft().setRight(new Node(8));

        RedBlackTree tree = new RedBlackTree();

        tree.insert(new Node(10));
        tree.insert(new Node(9));
        tree.insert(new Node(8));
        tree.insert(new Node(7));
        tree.insert(new Node(6));

        assertEquals(expectedRootNode.toString(), tree.getRoot().toString());
    }

    @Test
    public void testFind() throws Exception {
        RedBlackTree tree = new RedBlackTree();

        Node node15 = new Node(15);

        tree.insert(new Node(10));
        tree.insert(new Node(5));
        tree.insert(new Node(20));
        tree.insert(node15);
        tree.insert(new Node(25));

        assertEquals(node15, tree.find(15));
    }
}