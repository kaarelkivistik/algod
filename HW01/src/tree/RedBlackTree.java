package tree;

import java.util.function.BiConsumer;

/**
 * Created by kaarel on 03/10/15.
 */
public class RedBlackTree<K extends Comparable<? super K>, V> {

    private Node<K, V> root;
    private int size = 0;

    public Node<K, V> grandparent(Node<K, V> node) {
        if(node != null && node.getParent() != null)
            return node.getParent().getParent();
        else
            return null;
    }

    public Node<K, V> uncle(Node<K, V> node) {
        Node<K, V> grandparent = grandparent(node);

        if(grandparent == null)
            return null;

        if(node.getParent() == grandparent.getLeft())
            return grandparent.getRight();
        else
            return grandparent.getLeft();
    }

    public Node<K, V> sibling(Node<K, V> node) {
        if(node.getParent() == null)
            return null;

        if(node.getParent().getLeft() == node)
            return node.getParent().getRight();
        else
            return node.getParent().getLeft();
    }

    public void replaceNode(Node<K, V> oldNode, Node<K, V> newNode) {
        if(oldNode.getParent() == null)
            this.root = newNode;
        else if(oldNode.getParent().getLeft() == oldNode)
            oldNode.getParent().setLeft(newNode);
        else
            oldNode.getParent().setRight(newNode);

        if(newNode != null)
            newNode.setParent(oldNode.getParent());
    }

    public void rotateLeft(Node<K, V> node) {
        Node<K, V> savedLeft = node.getRight().getLeft();

        replaceNode(node, node.getRight());
        node.getRight().setLeft(node);
        node.setRight(savedLeft);
    }

    public void rotateRight(Node<K, V> node) {
        Node<K, V> savedRight = node.getLeft().getRight();

        replaceNode(node, node.getLeft());
        node.getLeft().setRight(node);
        node.setLeft(savedRight);
    }

    public Node<K, V> get(K key) {
        return find(key, this.root);
    }

    private Node<K, V> find(K key, Node<K, V> node) {
        if(node == null)
            return null;

        int comparison = key.compareTo(node.getKey());

        if(comparison == 0)
            return node;
        else if(comparison == -1)
            return find(key, node.getLeft());
        else
            return find(key, node.getRight());
    }

    public void put(K key, V value) {
        insert(new Node<>(key, value));
    }

    private void insert(Node<K, V> node) {
        insert(this.root, node);
        size++;
    }

    private void insert(Node<K, V> root, Node<K, V> node) {
        if(this.root == null) {
            this.root = node;
            insertFirstCase(node);
        } else if(node.getKey().compareTo(root.getKey()) == -1) {
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
    }

    public void insertFirstCase(Node<K, V> node) {
        if(node.getParent() == null)
            node.setBlack(true);
        else
            insertSecondCase(node);
    }

    public void insertSecondCase(Node<K, V> node) {
        if(node.getParent().isBlack())
            return;
        else
            insertThirdCase(node);
    }

    public void insertThirdCase(Node<K, V> node) {
        Node<K, V> uncle = uncle(node);
        Node<K, V> grandparent;

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

    public void insertFourthCase(Node<K, V> node) {
        Node<K, V> grandparent = grandparent(node);

        if(node == node.getParent().getRight() && node.getParent() == grandparent.getLeft()) {
            rotateLeft(node.getParent());
        } else if(node == node.getParent().getLeft() && node.getParent() == grandparent.getRight()){
            rotateRight(node.getParent());
        }

        insertFifthCase(node);
    }

    public void insertFifthCase(Node<K, V> node) {
        Node<K, V> grandparent = grandparent(node);

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

    public Node<K, V> getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }

    public void traverse(BiConsumer<K, V> consumer) {
        traverse(this.root, consumer);
    }

    private void traverse(Node<K, V> node, BiConsumer<K, V> consumer) {
        if(node == null)
            return;

        traverse(node.getLeft(), consumer);

        consumer.accept(node.getKey(), node.getValue());

        traverse(node.getRight(), consumer);
    }

    /**
     * Created by kaarel on 02/10/15.
     */
    public static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;
        private Node<K, V> parent;
        private boolean black = false;

        public Node(K key) {
            this.key = key;
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node<K, V> getLeft() {
            return left;
        }

        public void setLeft(Node<K, V> left) {
            this.left = left;
        }

        public Node<K, V> getRight() {
            return right;
        }

        public void setRight(Node<K, V> right) {
            this.right = right;
        }

        public Node<K, V> getParent() {
            return parent;
        }

        public void setParent(Node<K, V> parent) {
            this.parent = parent;
        }

        public boolean isBlack() {
            return black;
        }

        public void setBlack(boolean black) {
            this.black = black;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node<?, ?> node = (Node<?, ?>) o;

            return getKey().equals(node.getKey());

        }

        @Override
        public int hashCode() {
            return getKey().hashCode();
        }
    }
}
