package tree;

import dancers.Dancer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by kaarel on 04/10/15.
 */
public class DancersTree {
    private DancerNode root;

    public DancerNode getRoot() {
        return root;
    }

    public void setRoot(DancerNode root) {
        this.root = root;
    }

    public DancerNode add(Dancer dancer) {
        return add(new DancerNode(dancer));
    }

    public DancerNode add(DancerNode node) {
        if(root == null)
            root = node;
        else
            add(root, node);

        return node;
    }

    public DancerNode add(DancerNode root, DancerNode node) {
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

    public DancerNode findPartnerFor(int height, boolean male) {
        return null;
    }

    public DancerNode minimum(DancerNode node) {
        while(node.getLeft() != null)
            node = node.getLeft();

        return node;
    }

    public DancerNode maximum(DancerNode node) {
        while(node.getRight() != null)
            node = node.getRight();

        return node;
    }

    public DancerNode successor(DancerNode node) {
        if(node.getRight() != null)
            return minimum(node.getRight());

        DancerNode parent = node.getParent();

        while(parent != null && node == parent.getRight()) {
            node = parent;
            parent = parent.getParent();
        }

        return parent;
    }

    public void delete(DancerNode node) {
        if(node.getLeft() == null && node.getRight() == null) {
            replaceSubtree(node, null);
        } else if(node.getLeft() == null) {
            replaceSubtree(node, node.getRight());
        } else if(node.getRight() == null) {
            replaceSubtree(node, node.getLeft());
        } else {
            DancerNode successor = successor(node);

            node.replace(successor);
            replaceSubtree(successor, successor.getRight());
        }
    }

    public void replaceSubtree(DancerNode node, DancerNode replacement) {
        DancerNode parent = node.getParent();

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

    public void traverse(DancerNode node, Consumer<Dancer> consumer) {
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
}

class DancerNode extends Dancer implements Comparable<DancerNode> {

    private DancerNode left, right, parent;

    public DancerNode(int ID, boolean male, int height) {
        super(ID, male, height);
    }

    public DancerNode(Dancer dancer) {
        this(dancer.getID(), dancer.isMale(), dancer.getHeight());
    }

    public DancerNode replace(DancerNode node) {
        setID(node.getID());
        setMale(node.isMale());
        setHeight(node.getHeight());

        // node.setParent(getParent());
        // node.setLeft(getLeft());
        // node.setRight(getRight());

        return this;
    }

    @Override
    public int compareTo(DancerNode o) {
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

    public DancerNode getLeft() {
        return left;
    }

    public void setLeft(DancerNode left) {
        if(left != null)
            left.setParent(this);

        this.left = left;
    }

    public DancerNode getRight() {
        return right;
    }

    public void setRight(DancerNode right) {
        if(right != null)
            right.setParent(this);

        this.right = right;
    }

    public DancerNode getParent() {
        return parent;
    }

    public void setParent(DancerNode parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "DancerNode{" +
                "ID=" + getID() +
                ", height=" + getHeight() +
                ", male=" + isMale() +
                ", left=" + left +
                ", right=" + right +
                "}";
    }
}
