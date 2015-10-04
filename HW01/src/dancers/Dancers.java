package dancers;

import dancers.Dancer;
import dancers.IDancer;
import dancers.IDancers;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by kaarel on 04/10/15.
 */
public class Dancers implements IDancers {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node add(Dancer dancer) {
        return add(new Node(dancer));
    }

    public Node add(Node node) {
        if(root == null)
            root = node;
        else
            add(root, node);

        return node;
    }

    public Node add(Node root, Node node) {
        int comparison = node.compareTo(root);

        if(comparison < 0) {
            if(root.getLeft() == null)
                root.setLeft(node);
            else
                add(root.getLeft(), node);
        } else {
            if(root.getRight() == null)
                root.setRight(node);
            else
                add(root.getRight(), node);
        }

        return node;
    }

    public Node findPartnerFor(int height, boolean male) {
        return null;
    }

    public Node minimum(Node node) {
        while(node.getLeft() != null)
            node = node.getLeft();

        return node;
    }

    public Node maximum(Node node) {
        while(node.getRight() != null)
            node = node.getRight();

        return node;
    }

    public Node successor(Node node) {
        if(node.getRight() != null)
            return minimum(node.getRight());

        Node parent = node.getParent();

        while(parent != null && node == parent.getRight()) {
            node = parent;
            parent = parent.getParent();
        }

        return parent;
    }

    public void delete(Node node) {
        if(node.getLeft() == null && node.getRight() == null) {
            replaceSubtree(node, null);
        } else if(node.getLeft() == null) {
            replaceSubtree(node, node.getRight());
        } else if(node.getRight() == null) {
            replaceSubtree(node, node.getLeft());
        } else {
            Node successor = successor(node);

            node.replace(successor);
            replaceSubtree(successor, successor.getRight());
        }
    }

    public void replaceSubtree(Node node, Node replacement) {
        Node parent = node.getParent();

        if(parent == null) {
            root = replacement;

        //   if(replacement != null)
        //        replacement.setParent(null);
        } else if(node == parent.getLeft())
            parent.setLeft(replacement);
        else
            parent.setRight(replacement);

        if(replacement != null)
            replacement.setParent(parent);
    }

    public void traverse(Consumer<Dancer> consumer) {
        traverse(root, consumer);
    }

    public void traverse(Node node, Consumer<Dancer> consumer) {
        if(node == null)
            return;

        traverse(node.getLeft(), consumer);
        consumer.accept(node);
        traverse(node.getRight(), consumer);
    }

    public List<Dancer> toList() {
        ArrayList<Dancer> list = new ArrayList<>();

        traverse(list::add);

        return list;
    }

    @Override
    public AbstractMap.SimpleEntry<IDancer, IDancer> findPartnerFor(IDancer d) throws IllegalArgumentException {
        return null;
    }

    @Override
    public List<IDancer> returnWaitingList() {
        return null;
    }

    public static class Node extends Dancer implements Comparable<Node> {

        private Node left, right, parent;

        public Node(int ID, boolean male, int height) {
            super(ID, male, height);
        }

        public Node(Dancer dancer) {
            this(dancer.getID(), dancer.isMale(), dancer.getHeight());
        }

        public Node replace(Node node) {
            setID(node.getID());
            setMale(node.isMale());
            setHeight(node.getHeight());

            // node.setParent(getParent());
            // node.setLeft(getLeft());
            // node.setRight(getRight());

            return this;
        }

        @Override
        public int compareTo(Node o) {
            if(getHeight() < o.getHeight())
                return -1;
            else if(getHeight() == o.getHeight()) {
                if(isMale() == o.isMale())
                    return 0;
                else if(isMale())
                    return -1;
                else
                    return 1;
            } else {
                return 1;
            }
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            if(left != null)
                left.setParent(this);

            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            if(right != null)
                right.setParent(this);

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
                    "ID=" + getID() +
                    ", height=" + getHeight() +
                    ", male=" + isMale() +
                    ", left=" + left +
                    ", right=" + right +
                    "}";
        }
    }
}

