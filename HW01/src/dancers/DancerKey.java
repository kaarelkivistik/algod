package dancers;

/**
 * Created by kaarel on 04/10/15.
 */
public class DancerKey implements Comparable<DancerKey> {
    private int height;
    private boolean male;

    @Override
    public int compareTo(DancerKey o) {
        if(height < o.getHeight())
            return -1;
        else if(height == o.getHeight()) {
            if(male == o.isMale())
                return 0;
            else if(male)
                return -1;
            else
                return 1;
        } else {
            return 1;
        }
    }

    public DancerKey(int height, boolean male) {
        this.height = height;
        this.male = male;
    }

    public int getHeight() {
        return height;
    }

    public boolean isMale() {
        return male;
    }
}
