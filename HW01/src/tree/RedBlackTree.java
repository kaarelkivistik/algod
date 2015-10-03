package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by kaarel on 03/10/15.
 */
public class RedBlackTree {

    private Node root;
    private int size = 0;

    public Node grandparent(Node node) {
        if(node != null && node.getParent() != null)
            return node.getParent().getParent();
        else
            return null;
    }

    public Node uncle(Node node) {
        Node grandparent = grandparent(node);

        if(grandparent == null)
            return null;

        if(node.getParent() == grandparent.getLeft())
            return grandparent.getRight();
        else
            return grandparent.getLeft();
    }

    public Node sibling(Node node) {
        if(node.getParent() == null)
            return null;

        if(node.getParent().getLeft() == node)
            return node.getParent().getRight();
        else
            return node.getParent().getLeft();
    }

    public void replaceNode(Node oldNode, Node newNode) {
        if(oldNode.getParent() == null)
            this.root = newNode;
        else if(oldNode.getParent().getLeft() == oldNode)
            oldNode.getParent().setLeft(newNode);
        else
            oldNode.getParent().setRight(newNode);

        if(newNode != null)
            newNode.setParent(oldNode.getParent());
    }

    public void rotateLeft(Node node) {
        Node savedLeft = node.getRight().getLeft();

        replaceNode(node, node.getRight());
        node.getRight().setLeft(node);
        node.setRight(savedLeft);
    }

    public void rotateRight(Node node) {
        Node savedRight = node.getLeft().getRight();

        replaceNode(node, node.getLeft());
        node.getLeft().setRight(node);
        node.setLeft(savedRight);
    }

    public Node find(int key) {
        return find(key, this.root);
    }

    public Node find(int key, Node node) {
        if(node == null)
            return null;

        if(key == node.getKey())
            return node;
        if(key <= node.getKey())
            return find(key, node.getLeft());
        else
            return find(key, node.getRight());
    }

    public void insert(Node node) {
        // System.out.println("Insert " + node);

        insert(this.root, node);
    }

    public void insert(Node root, Node node) {
        if(this.root == null) {
            this.root = node;
            insertFirstCase(node);
        } else if(node.getKey() < root.getKey()) {
            if(root.getLeft() == null) {
                node.setParent(root);
                root.setLeft(node);
                insertFirstCase(node);
            } else
                insert(root.getLeft(), node);
        } else {
            if(root.getRight() == null) {
                node.setParent(root);
                root.setRight(node);
                insertFirstCase(node);
            } else
                insert(root.getRight(), node);
        }

        size++;
    }

    public void insertFirstCase(Node node) {
        if(node.getParent() == null)
            node.setBlack(true);
        else
            insertSecondCase(node);
    }

    public void insertSecondCase(Node node) {
        if(node.getParent().isBlack())
            return;
        else
            insertThirdCase(node);
    }

    public void insertThirdCase(Node node) {
        Node uncle = uncle(node);
        Node grandparent;

        if(uncle != null && !uncle.isBlack()) {
            node.getParent().setBlack(true);
            uncle.setBlack(true);
            grandparent = grandparent(node);
            grandparent.setBlack(false);
            insertFirstCase(grandparent);
        } else {
            insertFourthCase(node);
        }
    }

    public void insertFourthCase(Node node) {
        Node grandparent = grandparent(node);

        if(node == node.getParent().getRight() && node.getParent() == grandparent.getLeft()) {
            rotateLeft(node.getParent());
        } else if(node == node.getParent().getLeft() && node.getParent() == grandparent.getRight()){
            rotateRight(node.getParent());
        }

        insertFifthCase(node);
    }

    public void insertFifthCase(Node node) {
        Node grandparent = grandparent(node);

        node.getParent().setBlack(true);
        grandparent.setBlack(false);

        if(node == node.getParent().getLeft())
            rotateRight(grandparent);
        else
            rotateLeft(grandparent);
    }

    public void delete(Node node) {

    }

    public void deleteSecondCase(Node node) {

    }

    public void deleteThirdCase(Node node) {

    }

    public void deleteFourthCase(Node node) {

    }

    public void deleteFifthCase(Node node) {

    }

    public void deleteSixthCase(Node node) {

    }

    public Node getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }

    public List<Node> toList() {
        List<Node> list = new ArrayList<>(getSize());

        traverse(node -> list.add(node));

        return list;
    }

    private void traverse(Consumer<Node> consumer) {
        traverse(this.root, consumer);
    }

    private void traverse(Node node, Consumer<Node> consumer) {
        if(node == null)
            return;

        traverse(node.getLeft(), consumer);

        consumer.accept(node);

        traverse(node.getRight(), consumer);
    }
}
