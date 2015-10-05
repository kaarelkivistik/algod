package dancers;

/**
 * Created by kaarel on 05/10/15.
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
}
