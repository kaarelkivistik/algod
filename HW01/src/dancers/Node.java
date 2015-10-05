package dancers;

/**
 * Created by kaarel on 02/10/15.
 */
public class Node implements Comparable<Node> {

    private boolean male;
    private int height;
    private Node left, right, parent;
    private Dancer dancer;

    public Node() {
    }

    public Node(boolean male, int height) {
        this.male = male;
        this.height = height;
    }

    public Node(int ID, boolean male, int height) {
        this(male, height);
        this.dancer = new Dancer(ID, male, height);
    }

    public Node(Dancer dancer) {
        this(dancer.isMale(), dancer.getHeight());
        this.dancer = dancer;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Dancer getDancer() {
        return dancer;
    }

    public void setDancer(Dancer dancer) {
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
        setMale(replacement.isMale());
        setHeight(replacement.getHeight());
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
