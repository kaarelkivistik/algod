package dancers;

import java.util.AbstractMap.SimpleEntry;
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

    public Node add(Node node) {
        node.setLeft(null);
        node.setRight(null);
        node.setParent(null);

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

    public Node maleSuccessor(Node node) {
        Node successor = successor(node);

        //while(successor != null && !successor.isMale())
        //    successor = successor(successor);

        while(true) {
            if(successor == null || successor.isMale())
                break;

            successor = successor(successor);
        }

        return successor;
    }

    public Node predecessor(Node node) {
        if(node.getLeft() != null)
            return maximum(node.getLeft());

        Node parent = node.getParent();

        while(parent != null && node == parent.getLeft()) {
            node = parent;
            parent = parent.getParent();
        }

        return parent;
    }

    public Node femalePredecessor(Node node) {
        Node predecessor = predecessor(node);

        while(predecessor != null && (predecessor.isMale() || predecessor.getHeight() > node.getHeight()))
            predecessor = predecessor(predecessor);

        return predecessor;
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

        if(parent == null)
            root = replacement;
        else if(node == parent.getLeft())
            parent.setLeft(replacement);
        else
            parent.setRight(replacement);

        if(replacement != null)
            replacement.setParent(parent);
    }

    public void traverse(Consumer<Node> consumer) {
        traverse(root, consumer);
    }

    public void traverse(Node node, Consumer<Node> consumer) {
        if(node == null)
            return;

        traverse(node.getLeft(), consumer);
        consumer.accept(node);
        traverse(node.getRight(), consumer);
    }

    public List<Node> toList() {
        ArrayList<Node> list = new ArrayList<>();

        traverse(list::add);

        return list;
    }

    @Override
    public SimpleEntry<IDancer, IDancer> findPartnerFor(IDancer iDancer) throws IllegalArgumentException {
        if(iDancer == null)
            throw new IllegalArgumentException();

        Node node = new Node(iDancer);

        add(node);

        Node match = iDancer.isMale() ? femalePredecessor(node) : maleSuccessor(node);

        if(match == null)
            return null;
        else {
            SimpleEntry<IDancer, IDancer> couple = new SimpleEntry<>(
                    node.getDancer(),
                    match.getDancer()
            );

            delete(node);
            delete(match);

            return couple;
        }
    }

    @Override
    public List<IDancer> returnWaitingList() {
        ArrayList<IDancer> list = new ArrayList<>();

        traverse(node -> list.add(node.getDancer()));

        return list;
    }
}

