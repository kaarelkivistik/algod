package dancers;

/**
 * Created by kaarel on 02/10/15.
 */
public class Dancer implements IDancer {

    private int ID;
    private boolean male;
    private int height;

    public Dancer(int ID, boolean male, int height) {
        this.ID = ID;
        this.male = male;
        this.height = height;
    }

    @Override
    public int getID() {
        return this.ID;
    }

    @Override
    public boolean isMale() {
        return this.male;
    }

    @Override
    public int getHeight() {
        return this.height;
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

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
