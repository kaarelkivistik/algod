package tsp;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by kaarel on 16/11/15.
 */
public class Node {

    private int value;
    private int bound;
    private List<Integer> tour;

    public Node(List<Integer> tour) {
        this.tour = tour;
    }

    public int getBound() {
        return bound;
    }

    public void setBound(int bound) {
        this.bound = bound;
    }

    public List<Integer> getTour() {
        return tour;
    }

    public void setTour(List<Integer> tour) {
        this.tour = tour;
    }

    public static final Comparator<Node> BOUND_COMPARATOR = new Comparator<Node>() {
        @Override
        public int compare(Node o1, Node o2) {
            return Integer.compare(o1.bound, o2.bound);
        }
    };
}
