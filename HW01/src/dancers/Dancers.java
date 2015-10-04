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
    private Dancer root;

    public Dancer getRoot() {
        return root;
    }

    public Dancer add(Dancer node) {
        if(root == null)
            root = node;
        else
            add(root, node);

        return node;
    }

    public Dancer add(Dancer root, Dancer node) {
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

    public Dancer findPartnerFor(int height, boolean male) {
        return null;
    }

    public Dancer minimum(Dancer node) {
        while(node.getLeft() != null)
            node = node.getLeft();

        return node;
    }

    public Dancer maximum(Dancer node) {
        while(node.getRight() != null)
            node = node.getRight();

        return node;
    }

    public Dancer successor(Dancer node) {
        if(node.getRight() != null)
            return minimum(node.getRight());

        Dancer parent = node.getParent();

        while(parent != null && node == parent.getRight()) {
            node = parent;
            parent = parent.getParent();
        }

        return parent;
    }

    public void delete(Dancer node) {
        if(node.getLeft() == null && node.getRight() == null) {
            replaceSubtree(node, null);
        } else if(node.getLeft() == null) {
            replaceSubtree(node, node.getRight());
        } else if(node.getRight() == null) {
            replaceSubtree(node, node.getLeft());
        } else {
            Dancer successor = successor(node);

            node.replace(successor);
            replaceSubtree(successor, successor.getRight());
        }
    }

    public void replaceSubtree(Dancer node, Dancer replacement) {
        Dancer parent = node.getParent();

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

    public void traverse(Dancer node, Consumer<Dancer> consumer) {
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
}

