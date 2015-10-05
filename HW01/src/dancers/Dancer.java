package dancers;

/**
 * Created by kaarel on 02/10/15.
 */
public class Dancer implements IDancer, Comparable<Dancer> {

    private int ID;
    private boolean male;
    private int height;

    private Dancer left, right, parent;

    public Dancer() {
    }

    public Dancer(int ID, boolean male, int height) {
        this.ID = ID;
        this.male = male;
        this.height = height;
    }

    @Override
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Dancer getLeft() {
        return left;
    }

    public void setLeft(Dancer left) {
        if(left != null)
            left.setParent(this);

        this.left = left;
    }

    public Dancer getRight() {
        return right;
    }

    public void setRight(Dancer right) {
        if(right != null)
            right.setParent(this);

        this.right = right;
    }

    public Dancer getParent() {
        return parent;
    }

    public void setParent(Dancer parent) {
        this.parent = parent;
    }

    public Dancer replace(Dancer replacement) {
        setID(replacement.getID());
        setMale(replacement.isMale());
        setHeight(replacement.getHeight());

        return this;
    }

    @Override
    public int compareTo(Dancer o) {
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
        return "Dancer{" +
                "ID=" + ID +
                ", male=" + male +
                ", height=" + height +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dancer dancer = (Dancer) o;

        if (getID() != dancer.getID()) return false;
        if (isMale() != dancer.isMale()) return false;
        return getHeight() == dancer.getHeight();

    }
}
