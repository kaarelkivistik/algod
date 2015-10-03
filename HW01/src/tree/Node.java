package tree;

/**
 * Created by kaarel on 02/10/15.
 */
public class Node<T> {
    private int key;
    private T data;
    private Node left;
    private Node right;
    private Node parent;
    private boolean black = false;

    public Node(int key) {
        this.key = key;
    }

    public Node(int key, T data, Node parent) {
        this.key = key;
        this.data = data;
        this.parent = parent;
    }

    public boolean isBlack() {
        return black;
    }

    public void setBlack(boolean black) {
        this.black = black;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", black=" + black +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return getKey() == node.getKey();

    }

    @Override
    public int hashCode() {
        return getKey();
    }
}
