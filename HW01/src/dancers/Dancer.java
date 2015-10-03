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
    public String toString() {
        return "Dancer{" +
                "ID=" + ID +
                ", male=" + male +
                ", height=" + height +
                '}';
    }
}
