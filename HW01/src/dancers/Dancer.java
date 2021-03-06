package dancers;

/**
 * Created by kaarel on 05/10/15.
 */
public class Dancer implements IDancer {
    private int ID;
    private boolean male;
    private int height;

    public Dancer(int height, boolean male) {
        this.ID = height;
        this.height = height;
        this.male = male;
    }

    public Dancer(int ID, boolean male, int height) {
        this.ID = ID;
        this.male = male;
        this.height = height;
    }

    public Dancer(int ID, int height, boolean male) {
        this.ID = ID;
        this.height = height;
        this.male = male;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public boolean isMale() {
        return male;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Dancer{" +
                "ID=" + ID +
                ", male=" + male +
                ", height=" + height +
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
