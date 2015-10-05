package dancers;

/**
 * Created by kaarel on 02/10/15.
 */
public class Node implements Comparable<Node> {

    private Node left, right, parent;
    private IDancer dancer;

    public Node(int ID, boolean male, int height) {
        this.dancer = new Dancer(ID, male, height);
    }

    public Node(IDancer dancer) {
        this.dancer = dancer;
    }

    public boolean isMale() {
        return dancer.isMale();
    }

    public int getHeight() {
        return dancer.getHeight();
    }

    public IDancer getDancer() {
        return dancer;
    }

    public void setDancer(IDancer dancer) {
        this.dancer = dancer;
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

    public Node replace(Node replacement) {
        setDancer(replacement.getDancer());

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

    @Override
    public String toString() {
        return "Node{" +
                "dancer=" + dancer +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return !(getDancer() != null ? !getDancer().equals(node.getDancer()) : node.getDancer() != null);

    }
}
